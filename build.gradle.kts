plugins {
    alias(libs.plugins.kotlin)
}

allprojects {
    repositories {
        gradlePluginPortal()
        mavenCentral()

        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

kotlin {
    jvmToolchain(25)
}