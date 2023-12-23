import org.gradle.internal.Actions.set
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

val jaxb by configurations.creating

dependencies {
    implementation(project(":common"))

    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-jackson:2.9.0")

    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    implementation("org.springframework.boot:spring-boot-starter-web-services")
    implementation("wsdl4j:wsdl4j")

    implementation("org.reactivestreams:reactive-streams:1.0.1")
    implementation("javax.activation:activation:1.1.1")
//    implementation("jakarta.activation:jakarta.activation-api:2.1.2")
    implementation("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")
//    implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.1")
    implementation("org.glassfish.jaxb:jaxb-runtime")

    jaxb("com.sun.xml.bind:jaxb-xjc:2.1.7")
}

configurations {
    jaxb
}

tasks.register("genJaxb") {
    ext["sourcesDir"] = "${buildDir}/generated-sources/jaxb"
    ext["classesDir"] = "${buildDir}/classes/jaxb"
    ext["schema"] = "C:\\Users\\Ivan\\IdeaProjects\\Lab2SOA\\services\\agency-service\\src\\main\\resources\\agency.xsd"

    ext["classesDir"]?.let { outputs.dir(it) }

    doLast {
        ant.withGroovyBuilder {
            "taskdef"(
                "name" to "xjc", "classname" to "com.sun.tools.xjc.XJCTask",
                "classpath" to jaxb.asPath
            )
            ext["sourcesDir"]?.let { mkdir(it) }
            ext["classesDir"]?.let { mkdir(it) }

            "xjc"(
                "destdir" to ext["sourcesDir"], "schema" to ext["schema"]
//                "package" to "target.package.name"
            ) {
                "arg"("value" to "-wsdl")
                "produces"("dir" to ext["sourcesDir"], "includes" to "**/*.java")
            }

            "javac"(
                "destdir" to ext["classesDir"], "source" to 1.8, "target" to 1.8, "debug" to true,
                "debugLevel" to "lines,vars,source", "classpath" to jaxb.asPath
            ) {
                "src"("path" to ext["sourcesDir"])
                "include"("name" to "**/*.java")
                "include"("name" to "*.java")
            }

            "copy"("todir" to ext["classesDir"]) {
                "fileset"("dir" to ext["sourcesDir"], "erroronmissingdir" to false) {
                    "exclude"("name" to "**/*.java")
                }
            }
        }
    }
}
tasks.build {
    dependsOn("genJaxb")
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
    archiveFileName.set("agency-server.jar")
}

dependencyManagement {
    imports {
        mavenBom( "org.springframework.cloud:spring-cloud-dependencies:2021.0.8")
    }
}
