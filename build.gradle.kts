plugins {
    kotlin("jvm") version "2.0.21"
}

group = "it.gabrielecabrini"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jline:jline-terminal:3.22.0")
    implementation("org.jline:jline-reader:3.22.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}