plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    // 2. Dépendances
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

extensions.configure<PublishingExtension> {
    publications.withType<MavenPublication>().all {
        pom {
            name.set("Discord Message Components v2 Builder Core")
            description.set("A Kotlin library to build Discord message components v2 structures.")
        }
    }
}