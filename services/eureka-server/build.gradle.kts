import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.jpa")
    kotlin("plugin.spring")
}

group = "com.iver"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")


    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.bootJar {
    archiveFileName.set("eureka-server.jar")
}

tasks.register("prepareKotlinBuildScriptModel")

dependencyManagement {
    imports {
        mavenBom( "org.springframework.cloud:spring-cloud-dependencies:2021.0.8")
    }
}
