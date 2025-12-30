plugins {
    kotlin("jvm")
}

group = "fr.ftnl.tools.messageBuilder"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.jda)
    compileOnly(project(":core"))
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}