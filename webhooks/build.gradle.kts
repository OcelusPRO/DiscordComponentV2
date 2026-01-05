plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    // 2. Dépendances
    sourceSets {
        commonMain.dependencies {
            compileOnly(project(":${rootProject.name}-core"))
            implementation(libs.bundles.ktor.client.all)
        }
        commonTest.dependencies {
            implementation(project(":${rootProject.name}-core"))
            implementation(kotlin("test"))
            implementation(libs.ktor.client.mock)
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")
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