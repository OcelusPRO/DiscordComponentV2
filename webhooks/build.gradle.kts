plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinx.serialization)
}

//kotlin {
//    // 2. Dépendances
//    sourceSets {
//        commonMain.dependencies {
//            implementation(project(":${rootProject.name}-core"))
//            implementation(libs.bundles.ktor.client.all)
//        }
//        commonTest.dependencies {
//            implementation(project(":${rootProject.name}-core"))
//            implementation(kotlin("test"))
//        }
//    }
//}

extensions.configure<PublishingExtension> {
    publications.withType<MavenPublication>().all {
        pom {
            name.set("Discord Message Components v2 Builder Webhooks")
            description.set("Webhooks module for Discord Message Components v2 Builder")
        }
    }
}