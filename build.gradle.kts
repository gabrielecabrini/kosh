plugins {
    application
    kotlin("jvm") version "2.1.0"
    id("com.github.gmazzo.buildconfig") version "5.5.1"
}

group = "it.gabrielecabrini"
version = "0.1.0"

application {
    mainClass = "it.gabrielecabrini.kosh.KoshKt"
}

buildConfig {
    buildConfigField("APP_VERSION", provider { "${project.version}" })

    useKotlinOutput()
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jline:jline-terminal:3.26.0")
    implementation("org.jline:jline-reader:3.26.0")
    implementation("org.jline:jline-builtins:3.26.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21)) // Set Java version explicitly
    }
}

tasks {
    val fatJar = register<Jar>("fatJar") {
        dependsOn.addAll(listOf("compileJava", "compileKotlin", "processResources"))
        archiveClassifier.set("standalone") // Naming the jar
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest { attributes(mapOf("Main-Class" to application.mainClass)) }
        val sourcesMain = sourceSets.main.get()
        val contents = configurations.runtimeClasspath.get()
            .map { if (it.isDirectory) it else zipTree(it) } +
                sourcesMain.output
        from(contents)
    }
    build {
        dependsOn(fatJar) // Trigger fat jar creation during build
    }
}
