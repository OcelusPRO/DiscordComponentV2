@file:OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)

import io.github.gradlenexus.publishplugin.NexusPublishExtension
import org.apache.tools.ant.taskdefs.condition.Os
import org.gradle.kotlin.dsl.support.serviceOf
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.process.ExecOperations

plugins {
    // Déclaration des plugins sans application immédiate
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.dokka) apply false
    alias(libs.plugins.nexus.publish)
    id("org.gradle.maven-publish")
    id("signing")
}

allprojects {
    group = "fr.ftnl.tools"
    version = "1.0.0"
    
    repositories {
        mavenCentral()
    }
}

// Configuration globale pour la publication sur Sonatype/Maven Central
extensions.configure<NexusPublishExtension> {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))
            username.set(System.getenv("OSSRH_USERNAME") ?: findProperty("ossrhUsername")?.toString())
            password.set(System.getenv("OSSRH_PASSWORD") ?: findProperty("ossrhPassword")?.toString())
        }
    }
}

subprojects {
    // On ignore le module de test s'il existe
    if (project.name == "test") return@subprojects
    
    // Application des plugins de base pour la publication et documentation
    apply(plugin = "org.gradle.maven-publish")
    apply(plugin = "signing")
    apply(plugin = "org.jetbrains.dokka")
    
    // --- 1. CONFIGURATION DU COMPILATEUR KOTLIN ---
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    
    // --- 2. CONFIGURATION AUTOMATIQUE DES CIBLES KMP ---
    // S'applique uniquement aux modules utilisant le plugin multiplatform
    plugins.withId("org.jetbrains.kotlin.multiplatform") {
        extensions.configure<KotlinMultiplatformExtension> {
            jvm()
            iosArm64()
            iosSimulatorArm64()
            macosArm64()
            linuxX64()
            js(IR) {
                browser()
                nodejs()
                binaries.library()
                generateTypeScriptDefinitions()
            }
            wasmJs {
                browser()
                nodejs()
                d8()
                generateTypeScriptDefinitions()
            }
        }
    }
    
    // --- 3. CONFIGURATION JAVA STANDARD (JVM) ---
    plugins.withId("java-library") {
        extensions.configure<JavaPluginExtension> {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
            withSourcesJar()
        }
    }
    
    // --- 4. LOGIQUE DE PUBLICATION UNIFIÉE ---
    
    // Génération du JAR Javadoc (obligatoire pour Maven Central)
    val javadocJar by tasks.registering(Jar::class) {
        archiveClassifier.set("javadoc")
        // V2 : On utilise dokkaGeneratePublicationHtml au lieu de dokkaHtml
        val dokkaTask = tasks.named("dokkaGeneratePublicationHtml")
        dependsOn(dokkaTask)
        
        // V2 : Le répertoire de sortie par défaut a également changé
        from(layout.buildDirectory.dir("dokka/html"))
        destinationDirectory.set(layout.buildDirectory.dir("javadoc-jars"))
    }
    
    extensions.configure<PublishingExtension> {
        // Pour les modules Java classiques, on s'assure que la publication existe
        plugins.withId("java-library") {
            publications {
                maybeCreate<MavenPublication>("mavenJava").apply {
                    from(components["java"])
                }
            }
        }
        
        // Configuration commune pour TOUTES les publications (KMP, JVM, et BOM)
        publications.withType<MavenPublication>().all {
            val pub = this
            if (!project.plugins.hasPlugin("java-platform")) {
                val javadocTask = tasks.register<Jar>("javadocJarFor${pub.name.replaceFirstChar { it.uppercase() }}") {
                    archiveClassifier.set("javadoc")
                    val dokkaTask = tasks.named("dokkaGeneratePublicationHtml")
                    dependsOn(dokkaTask)
                    from(layout.buildDirectory.dir("dokka/html"))
                    
                    // On isole le fichier dans un dossier spécifique à la publication
                    destinationDirectory.set(layout.buildDirectory.dir("javadoc-jars/${pub.name}"))
                    archiveBaseName.set("${project.name}-${pub.name}")
                }
                artifact(javadocTask)
            }
            
            val projectName = rootProject.name // "DiscordMessageBuilder"
            artifactId = when {
                name == "kotlinMultiplatform" || name == "mavenJava" -> "$projectName-${project.name}"
                else -> "$projectName-${pub.artifactId}"
            }
            
            pom {
                name.set("${rootProject.name} ${project.name}")
                description.set("A Kotlin library to build Discord message components v2 structures.")
                url.set("https://github.com/OcelusPRO/DiscordComponentV2")
                
                licenses {
                    license {
                        name.set("Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                
                developers {
                    developer {
                        id.set("oceluspro")
                        name.set("ocelus_ftnl")
                        email.set("contact@ftnl.fr")
                    }
                }
                
                scm {
                    connection.set("scm:git:github.com/OcelusPRO/DiscordComponentV2.git")
                    developerConnection.set("scm:git:ssh://github.com/OcelusPRO/DiscordComponentV2.git")
                    url.set("https://github.com/OcelusPRO/DiscordComponentV2")
                }
            }
        }
    }
    
    // --- 5. SIGNATURE DES ARTEFACTS (GPG) ---
    extensions.configure<SigningExtension> {
        // Utilise la commande gpg locale ou les propriétés Gradle
        useGpgCmd()
        sign(extensions.getByType<PublishingExtension>().publications)
    }
}


tasks.register("publishEverywhere") {
    group = "publishing"
    description = "Publie sur Maven Central et NPM."
    
    val execOperations = project.serviceOf<org.gradle.process.ExecOperations>()
    
    // On s'assure que les tâches Maven s'exécutent avant NPM
    dependsOn("publishToSonatype")
    
    subprojects {
        val subproject = this
        plugins.withId("org.jetbrains.kotlin.multiplatform") {
            val jsDistTaskName = "jsBrowserProductionLibraryDistribution"
            if (tasks.names.contains(jsDistTaskName)) {
                this@register.dependsOn("${subproject.path}:$jsDistTaskName")
            }
        }
    }
    
    doLast {
        subprojects {
            val subproject = this
            plugins.withId("org.jetbrains.kotlin.multiplatform") {
                val jsDistDir = subproject.layout.buildDirectory.dir("dist/js/productionLibrary").get().asFile
                
                if (jsDistDir.exists()) {
                    println("--- Préparation NPM : ${subproject.name} ---")
                    val token = project.findProperty("npmToken")?.toString() ?: System.getenv("NPM_TOKEN")
                    if (token != null) {
                        val npmrc = file("${jsDistDir}/.npmrc")
                        npmrc.writeText("//registry.npmjs.org/:_authToken=$token\n")
                        println("--- Token NPM configuré pour ${subproject.name} ---")
                    }
                    val packageJson = file("${jsDistDir}/package.json")
                    packageJson.writeText("""
                        {
                            "name": "@ocelus/${rootProject.name.lowercase()}-${subproject.name.lowercase()}",
                            "version": "${project.version}",
                            "main": "${subproject.name}.js",
                            "types": "${subproject.name}.d.ts",
                            "author": "ocelus_ftnl",
                            "license": "Apache-2.0",
                            "publishConfig": { "access": "public" }
                        }
                    """.trimIndent())
                    
                    execOperations.exec {
                        workingDir = jsDistDir
                        executable = if (System.getProperty("os.name").lowercase().contains("win")) "npm.cmd" else "npm"
                        args = mutableListOf("publish", "--access", "public")
                        isIgnoreExitValue = true
                    }
                }
            }
        }
    }
}

// Sécurisation de la clôture du dépôt Sonatype
tasks.named("publishEverywhere") {
    // On ne tente de fermer que si publishToSonatype n'était pas déjà UP-TO-DATE
    if (tasks.findByName("closeAndReleaseSonatypeStagingRepository") != null) {
        finalizedBy("closeAndReleaseSonatypeStagingRepository")
    }
}