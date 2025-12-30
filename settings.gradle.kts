plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "DiscordMessageBuilder"
include("core")
include("jda")
include("webhooks")