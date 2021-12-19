plugins {
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    implementation(project(":image-api-core"))
    implementation(kotlin("stdlib"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = true
jar.enabled = false