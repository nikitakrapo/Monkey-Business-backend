plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
}

ktor {
    fatJar {
        archiveFileName.set("monkey-business-backend-fat.jar")
    }
}

group = "com.nikitakrapo"
version = "0.0.1"
application {
    mainClass.set("com.monkeybusiness.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    google()
    mavenCentral()
}

sqldelight {
    databases {
        create("FinancesDatabase") {
            packageName.set("com.monkeybusiness")
        }
    }
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.ktor.server.core.jvm)
    implementation(libs.ktor.server.authentication)
    implementation(libs.ktor.server.content.negotiation.jvm)
    implementation(libs.ktor.serialization.kotlinx.json.jvm)
    implementation(libs.ktor.server.netty.jvm)
    implementation(libs.logback)
    implementation(libs.firebase.admin)
    implementation(libs.sqldelight.driver.jvm)
    implementation(libs.sqldelight.coroutines)
    testImplementation(libs.ktor.server.tests.jvm)
    testImplementation(libs.kotlin.test.junit)
}