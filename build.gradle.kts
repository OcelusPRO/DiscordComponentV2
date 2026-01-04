@file:OptIn(ExperimentalWasmDsl::class)

import io.github.gradlenexus.publishplugin.NexusPublishExtension
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.nexus.publish)
    alias(libs.plugins.dokka) apply false
    id("org.gradle.maven-publish") apply false
    id("signing") apply false
}

allprojects {
    group = "fr.ftnl.tools.messageBuilder"
    version = "1.0.0"
    
    repositories {
        mavenCentral()
    }
}

// Configuration de Nexus (Sonatype) pour la publication globale
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
    if (project.name == "test") return@subprojects
    
    // Application des plugins communs
    apply(plugin = "org.gradle.maven-publish")
    apply(plugin = "signing")
    apply(plugin = "org.jetbrains.dokka")
    
    // --- 1. CONFIGURATION DU COMPILATEUR (Pour modules JVM et KMP) ---
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    
    // --- 2. CONFIGURATION SPÉCIFIQUE KMP ---
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
            }
            wasmJs()
        }
    }
    
    // --- 3. CONFIGURATION SPÉCIFIQUE JAVA (Pour vos modules non-KMP) ---
    plugins.withId("java-library") {
        extensions.configure<JavaPluginExtension> {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        
        // Pour les modules Java, on crée manuellement la publication
        extensions.configure<PublishingExtension> {
            publications {
                create<MavenPublication>("mavenJava") {
                    from(components["java"])
                    artifactId = "${rootProject.name}-${project.name}"
                }
            }
        }
    }
    
    // --- 4. LOGIQUE DE PUBLICATION UNIFIÉE (KMP & JVM) ---
    
    // Création du JAR Javadoc via Dokka pour tout le monde
    val javadocJar by tasks.registering(Jar::class) {
        archiveClassifier.set("javadoc")
        val dokkaTask = tasks.named<DokkaTask>("dokkaHtml")
        dependsOn(dokkaTask)
        from(dokkaTask.get().outputDirectory)
    }
    
    extensions.configure<PublishingExtension> {
        // On configure TOUTES les publications (celles auto-générées par KMP + mavenJava)
        publications.withType<MavenPublication>().all {
            // Ajout du Javadoc
            artifact(javadocJar)
            artifactId =
                if (name != "kotlinMultiplatform" && name != "mavenJava")
                    "${rootProject.name}-${project.name}-${name.lowercase()}"
                else
                    "${rootProject.name}-${project.name}"
            
            
            pom {
                name.set("${rootProject.name} ${project.name}")
                description.set("A Kotlin library for Discord components")
                developers {
                    developer {
                        id.set("oceluspro")
                        name.set("ocelus_ftnl")
                        email.set("contact@ftnl.fr")
                    }
                }
                scm {
                    // Optionnel: Ajoutez votre URL Git ici
                }
            }
        }
    }
    
    // --- 5. SIGNATURE ---
    extensions.configure<SigningExtension> {
        useGpgCmd()
        sign(extensions.getByType<PublishingExtension>().publications)
    }
}