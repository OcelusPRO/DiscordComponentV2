plugins {
    alias(libs.plugins.kotlin.jvm)
    `java-library`
}

dependencies {
    compileOnly(project(":${rootProject.name}-core"))
    testImplementation(kotlin("test"))
}

extensions.configure<PublishingExtension> {
    publications.withType<MavenPublication>().all {
        pom {
            name.set("Discord Message Components v2 Builder Webhooks")
            description.set("Webhooks module for Discord Message Components v2 Builder")
        }
    }
}