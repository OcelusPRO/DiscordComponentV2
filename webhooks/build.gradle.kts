plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            compileOnly(project(":${rootProject.name}-core"))
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
        }
        commonTest.dependencies {
            implementation(project(":${rootProject.name}-core"))
            implementation(kotlin("test"))
            implementation(libs.ktor.client.mock)
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")
        }
        jvmMain.dependencies {
            compileOnly(libs.jda)
        }
    }
}

extensions.configure<PublishingExtension> {
    publications.withType<MavenPublication>().all {
        pom {
            name.set("Discord Message Components v2 Builder Webhooks")
            description.set("Webhooks module for Discord Message Components v2 Builder")
        }
    }
}