plugins {
    alias(libs.plugins.kotlin.multiplatform)
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
    publications.named<MavenPublication>("mavenJava") {
        pom {
            name.set("Discord Message Components v2 Builder Core")
            description.set("A Kotlin library to build Discord message components v2 structures.")
        }
    }
}