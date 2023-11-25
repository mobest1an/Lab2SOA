import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    war
    kotlin("jvm")
    kotlin("plugin.allopen")
    kotlin("plugin.noarg")
}

allOpen {
    annotations(
        "jakarta.persistence.Entity",
    )
}

noArg {
    annotations(
        "jakarta.persistence.Entity",
    )
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
    implementation("jakarta.platform:jakarta.jakartaee-api:10.0.0")
    implementation("org.hibernate.orm:hibernate-core:6.2.4.Final")
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
    implementation("org.jboss.ejb3:jboss-ejb3-ext-api:2.3.0.Final")
    implementation("org.postgresql:postgresql:42.6.0")
}

configurations {
    all {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
        exclude(group = "ch.qos.logback", module = "logback-classic")
        exclude(group = "org.apache.logging.log4j", module = "log4j-to-slf4j")
    }
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
