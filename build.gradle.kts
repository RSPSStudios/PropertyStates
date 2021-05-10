plugins {
    kotlin("jvm") version "1.5.0"
}

group = "com.javatar"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0-RC")
}
