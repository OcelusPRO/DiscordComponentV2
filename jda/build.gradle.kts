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
    publications.withType<MavenPublication>().all {
        pom {
            name.set("Discord Message Components v2 Builder JDA")
            description.set("JDA extension module for Discord Message Components v2 Builder.")
        }
    }
}