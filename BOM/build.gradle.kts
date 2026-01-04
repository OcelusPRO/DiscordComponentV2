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
    publications {
        // On utilise create au lieu de named pour s'assurer qu'elle existe
        create<MavenPublication>("mavenJava") {
            from(components["javaPlatform"])
            pom {
                name.set("BOM for Discord Message Components v2 Builder")
                description.set("A Bill of Materials (BOM) for the Discord Message Components v2 Builder library.")
            }
        }
    }
}