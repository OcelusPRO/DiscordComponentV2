plugins {
    kotlin("jvm")
    alias(libs.plugins.kotlinx.serialization)
}

group = "fr.ftnl.tools.messageBuilder"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(libs.kotlinx.serialization.json)
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}