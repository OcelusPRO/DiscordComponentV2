plugins {
    `java-platform`
}

dependencies {
    constraints {
        api(project(":core"))
        api(project(":jda"))
        api(project(":webhooks"))
    }
}

extensions.configure<PublishingExtension> {
    publications.named<MavenPublication>("mavenJava") {
        pom {
            name.set("BOM for Discord Message Components v2 Builder")
            description.set("A Bill of Materials (BOM) for the Discord Message Components v2 Builder library.")
        }
    }
}