pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "EasyDiscordComponentV2"
// 2. On inclut les modules et on force leur nom logique immédiatement
include("core")
project(":core").name = "${rootProject.name}-core"

include("jda")
project(":jda").name = "${rootProject.name}-jda"

include("webhooks")
project(":webhooks").name = "${rootProject.name}-webhooks"

include("BOM")
project(":BOM").name = "${rootProject.name}-BOM"