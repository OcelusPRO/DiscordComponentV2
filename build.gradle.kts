import io.github.gradlenexus.publishplugin.NexusPublishExtension
import org.gradle.kotlin.dsl.support.serviceOf
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.process.ExecOperations
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import java.security.MessageDigest

plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.dokka) apply false
    alias(libs.plugins.nexus.publish)
    id("org.gradle.maven-publish")
    id("signing")
}

allprojects {
    group = "fr.ftnl.tools"
    version = "1.0.1"
    
    repositories {
        mavenCentral()
    }
}

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
    apply(plugin = "org.gradle.maven-publish")
    apply(plugin = "signing")
    apply(plugin = "org.jetbrains.dokka")
    
    base.archivesName.set("${rootProject.name}-${project.name}")
    
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    
    plugins.withId("org.jetbrains.kotlin.multiplatform") {
        extensions.configure<KotlinMultiplatformExtension> {
            jvm()
            iosArm64()
            iosSimulatorArm64()
            macosArm64()
            linuxX64()
            js(IR) {
                compilerOptions {
                    freeCompilerArgs.add("-Xes-long-as-bigint")
                }
                browser()
                nodejs()
                binaries.library()
                generateTypeScriptDefinitions()
            }
        }
    }
    
    plugins.withId("java-library") {
        extensions.configure<JavaPluginExtension> {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
            withSourcesJar()
        }
    }
    
    
    val javadocJar by tasks.registering(Jar::class) {
        archiveClassifier.set("javadoc")
        val dokkaTask = tasks.named("dokkaGeneratePublicationHtml")
        dependsOn(dokkaTask)
        from(layout.buildDirectory.dir("dokka/html"))
        destinationDirectory.set(layout.buildDirectory.dir("javadoc-jars"))
    }
    
    extensions.configure<PublishingExtension> {
        plugins.withId("java-library") {
            publications {
                maybeCreate<MavenPublication>("mavenJava").apply {
                    from(components["java"])
                }
            }
        }
        
        publications.withType<MavenPublication>().all {
            val pub = this
            
            if (!project.plugins.hasPlugin("java-platform")) {
                val javadocTaskName = "javadocJarFor${pub.name.replaceFirstChar { it.uppercase() }}"
                if (tasks.findByName(javadocTaskName) == null) {
                    tasks.register<Jar>(javadocTaskName) {
                        archiveClassifier.set("javadoc")
                        val dokkaTask = tasks.named("dokkaGeneratePublicationHtml")
                        dependsOn(dokkaTask)
                        from(layout.buildDirectory.dir("dokka/html"))
                        destinationDirectory.set(layout.buildDirectory.dir("javadoc-jars/${pub.name}"))
                        archiveBaseName.set("${project.name}-${pub.name}")
                    }
                }
                artifact(tasks.named(javadocTaskName))
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
    
    
    
    extensions.configure<SigningExtension> {
        useGpgCmd()
        sign(extensions.getByType<PublishingExtension>().publications)
    }
}

tasks.register("publishToNpm") {
    group = "publishing"
    description = "Génère le package.json et publie sur NPM."
    val execOperations = project.serviceOf<ExecOperations>()
    
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
                        println("Token NPM configuré.")
                    } else {
                        println("ATTENTION: Aucun token NPM trouvé (NPM_TOKEN ou prop npmToken).")
                    }
                    val packageJson = file("${jsDistDir}/package.json")
                    packageJson.writeText("""
                        {
                            "name": "@ocelus/${subproject.name.lowercase()}",
                            "version": "${project.version}",
                            "main": "${subproject.name}.js",
                            "types": "${subproject.name}.d.ts",
                            "author": "ocelus_ftnl",
                            "license": "Apache-2.0",
                            "publishConfig": { "access": "public" }
                        }
                    """.trimIndent())
                    println("Publication de ${subproject.name} sur NPM...")
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


data class SwiftPackageInfo(
    val moduleName: String,
    val url: String,
    val checksum: String
)
val collectedSwiftPackages = mutableListOf<SwiftPackageInfo>()

val generateGlobalPackageSwift = tasks.register("generateGlobalPackageSwift") {
    group = "publishing"
    description = "Génère un seul Package.swift pour TOUS les modules."
    
    val outputDir = layout.buildDirectory.dir("dist/swift")
    outputs.dir(outputDir)
    
    doLast {
        if (collectedSwiftPackages.isEmpty()) {
            println("Aucun module Swift détecté.")
            return@doLast
        }
        
        val products = collectedSwiftPackages.joinToString(",\n        ") {
            """.library(name: "${it.moduleName}", targets: ["${it.moduleName}"])"""
        }
        
        val targets = collectedSwiftPackages.joinToString(",\n        ") {
            """
            .binaryTarget(
                name: "${it.moduleName}",
                url: "${it.url}",
                checksum: "${it.checksum}"
            )
            """.trimIndent()
        }
        
        val content = """
            // swift-tools-version:5.3
            import PackageDescription

            let package = Package(
                name: "EasyDiscordComponentV2",
                platforms: [ .iOS(.v14), .macOS(.v11) ],
                products: [
                    $products
                ],
                targets: [
                    $targets
                ]
            )
        """.trimIndent()
        
        val file = outputDir.get().file("Package.swift").asFile
        file.parentFile.mkdirs()
        file.writeText(content)
        println("Package.swift global généré : ${file.path}")
    }
}

tasks.register("publishToSwift") {
    group = "publishing"
    
    dependsOn(generateGlobalPackageSwift)
    subprojects {
        val sub = this
        sub.afterEvaluate {
            val collectTask = sub.tasks.findByName("collectSwiftInfo")
            if (collectTask != null) {
                generateGlobalPackageSwift.get().dependsOn(collectTask)
            }
        }
    }
}


val token = System.getenv("GITHUB_TOKEN_EDC") ?: throw IllegalStateException("Le token GitHub pour le repo Swift dédié n'est pas défini (GITHUB_TOKEN_EDC).")
val swiftRepoUrl = "https://oauth2:$token@github.com/OcelusPRO/EasyDiscordComponentV2-Swift.git"
tasks.register("pushToSwiftRepo") {
    group = "publishing"
    description = "Clone le repo Swift dédié, met à jour le Package.swift et push."
    dependsOn("generateGlobalPackageSwift")
    
    val execOps = project.serviceOf<ExecOperations>()
    val tempDir = layout.buildDirectory.dir("swift-repo-temp").get().asFile
    val generatedPackageFile = layout.buildDirectory.file("dist/swift/Package.swift").get().asFile
    
    doLast {
        if (tempDir.exists()) tempDir.deleteRecursively()
        tempDir.mkdirs()
        println("--- GIT: Clonage du repo Swift ---")
        println("Repo: $swiftRepoUrl")
        
        execOps.exec {
            commandLine("git", "clone", swiftRepoUrl, tempDir.absolutePath)
        }
        
        if (generatedPackageFile.exists()) {
            generatedPackageFile.copyTo(File(tempDir, "Package.swift"), overwrite = true)
            println("Package.swift mis à jour.")
        } else error("Le fichier généré Package.swift est introuvable !")
        
        execOps.exec {
            workingDir = tempDir
            commandLine("git", "config", "user.email", "bot@ftnl.fr")
            isIgnoreExitValue = true
        }
        execOps.exec {
            workingDir = tempDir
            commandLine("git", "config", "user.name", "Gradle Bot")
            isIgnoreExitValue = true
        }
        
        println("--- GIT: Commit & Push ---")
        
        execOps.exec {
            workingDir = tempDir
            commandLine("git", "add", ".")
        }
        
        val commitResult = execOps.exec {
            workingDir = tempDir
            commandLine("git", "commit", "-m", "Release version ${project.version}")
            isIgnoreExitValue = true
        }
        
        if (commitResult.exitValue == 0) {
            execOps.exec {
                workingDir = tempDir
                commandLine("git", "push")
            }
            
            execOps.exec {
                workingDir = tempDir
                commandLine("git", "tag", "${project.version}")
                isIgnoreExitValue = true
            }
            execOps.exec {
                workingDir = tempDir
                commandLine("git", "push", "--tags")
                isIgnoreExitValue = true
            }
            
            println("✅ Push vers le repo Swift terminé avec succès !")
        }
        else println("⚠️ Rien à commiter (le fichier n'a pas changé).")
    }
}



val mavenClose = tasks.named("closeAndReleaseSonatypeStagingRepository")
val npmPublish = tasks.named("publishToNpm")
val swiftPublish = tasks.named("publishToSwift")

tasks.register("publishAll") {
    group = "publishing"
    description = "Release complète : Maven, NPM, et Repo Swift dédié."
    
    dependsOn(npmPublish, mavenClose, tasks.named("pushToSwiftRepo"))
    
    tasks.named("pushToSwiftRepo").configure {
        mustRunAfter(tasks.named("publishToSwift"))
    }
}

subprojects {
    val subproject = this
    subproject.afterEvaluate {
        val pubTask = subproject.tasks.findByName("publishToSonatype")
        if (pubTask != null) {
            rootProject.tasks.named("publishAll").configure {
                dependsOn(pubTask)
            }
            mavenClose.configure {
                mustRunAfter(pubTask)
            }
        }
    }
    configureSwiftPublishing()
}

fun Project.configureSwiftPublishing() {
    plugins.withId("org.jetbrains.kotlin.multiplatform") {
        val kmpExtension = extensions.getByType<KotlinMultiplatformExtension>()
        
        afterEvaluate {
            val appleTargets = kmpExtension.targets
                .filterIsInstance<KotlinNativeTarget>()
                .filter { it.konanTarget.family.isAppleFamily }
            
            if (appleTargets.isNotEmpty()) {
                val rawName = project.name // ex: EasyDiscordComponentV2-core
                val frameworkName = rawName.split("-").joinToString("") { part ->
                    part.replaceFirstChar { it.uppercase() }
                }
                
                kmpExtension.apply {
                    val xcf = XCFramework(frameworkName)
                    appleTargets.forEach { target ->
                        target.binaries.framework {
                            baseName = frameworkName
                            xcf.add(this)
                        }
                    }
                }
                
                val zipTask = tasks.register<Zip>("zipXCFramework") {
                    group = "publishing"
                    dependsOn("assemble${frameworkName}XCFramework")
                    from(layout.buildDirectory.dir("XCFrameworks/release"))
                    destinationDirectory.set(layout.buildDirectory.dir("dist/swift"))
                    archiveFileName.set("$frameworkName.xcframework.zip")
                }
                
                extensions.configure<PublishingExtension> {
                    publications.withType<MavenPublication>().configureEach {
                        if (this.name == "kotlinMultiplatform") {
                            artifact(zipTask) {
                                classifier = "xcframework"
                                extension = "zip"
                            }
                        }
                    }
                }
                
                val collectTask = tasks.register("collectSwiftInfo") {
                    dependsOn(zipTask)
                    doLast {
                        val zipFile = zipTask.get().archiveFile.get().asFile
                        if (zipFile.exists()) {
                            val checksum = MessageDigest.getInstance("SHA-256")
                                .digest(zipFile.readBytes())
                                .joinToString("") { "%02x".format(it) }
                            
                            val repoUrl = "https://repo1.maven.org/maven2"
                            val groupPath = project.group.toString().replace('.', '/')
                            val artifactId = project.name
                            val version = project.version.toString()
                            val downloadUrl = "$repoUrl/$groupPath/$artifactId/$version/$artifactId-$version-xcframework.zip"
                            
                            synchronized(collectedSwiftPackages) {
                                collectedSwiftPackages.add(SwiftPackageInfo(frameworkName, downloadUrl, checksum))
                            }
                        }
                    }
                }
            }
        }
    }
}