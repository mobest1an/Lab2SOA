import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	kotlin("plugin.spring")
//	id("com.github.gradlecommunity.jaxb2") version "3.1.0"
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
	implementation(project(":common"))

	implementation("org.jvnet.jaxb2.maven2:maven-jaxb2-plugin:0.15.3")

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-web-services")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	testImplementation("org.springframework.boot:spring-boot-starter-test")

//	jaxb2("org.jvnet.jaxb2_commons:jaxb2-basics-runtime:1.11.1")
//	jaxb2("org.jvnet.jaxb2_commons:jaxb2-basics-ant:1.11.1")
//	jaxb2("org.jvnet.jaxb2_commons:jaxb2-basics:1.11.1")
//	jaxb2("com.sun.xml.bind:jaxb-core:4.0.2")
//	jaxb2("com.sun.xml.bind:jaxb-xjc:4.0.2")
//	jaxb2("com.sun.xml.bind:jaxb-impl:4.0.2")
}

//jaxb2 {
//	xjc {
//		configurations {
//			"request-classes" {
//
//			}
//		}
//	}
//}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
