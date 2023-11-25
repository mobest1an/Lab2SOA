import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    war
    kotlin("jvm")
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
    compileOnly("javax:javaee-api:8.0.1")

    implementation("javax.persistence:javax.persistence-api:2.2")
    compileOnly("jakarta.platform:jakarta.jakartaee-web-api:10.0.0")
    implementation("org.hibernate:hibernate-validator:8.0.0.Final")
    implementation("org.jboss.ejb3:jboss-ejb3-ext-api:2.3.0.Final")
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
    implementation("jakarta.ejb:jakarta.ejb-api:4.0.1")
    implementation("org.hibernate:hibernate-core:6.2.4.Final")
    implementation("org.postgresql:postgresql:42.6.0")
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
