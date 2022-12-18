val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.7.22"
    id("io.ktor.plugin") version "2.2.1"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.22"
}

group = "com.nikitakrapo"
version = "0.0.1"
application {
    mainClass.set("com.nikitakrapo.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.core.jvm)
    implementation(libs.ktor.server.content.negotiation.jvm)
    implementation(libs.ktor.serialization.kotlinx.json.jvm)
    implementation(libs.ktor.server.netty.jvm)
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation(libs.ktor.server.tests.jvm)
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}