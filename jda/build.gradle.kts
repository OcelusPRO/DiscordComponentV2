
plugins {
    alias(libs.plugins.kotlin.jvm)
    `java-library`
}

dependencies {
    compileOnly(libs.jda)
    compileOnly(project(":core"))
    testImplementation(kotlin("test"))
}

extensions.configure<PublishingExtension> {
    publications.named<MavenPublication>("mavenJava") {
        pom {
            name.set("Discord Message Components v2 Builder JDA")
            description.set("JDA extention module for Discord Message Components v2 Builder.")
        }
    }
}