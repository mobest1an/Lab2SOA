rootProject.name = "Lab2SOA"

include("services:agency-service")
include("services:flat-service")

pluginManagement {
    val kotlinVersion = "1.7.22"
    val springBootVersion = "2.7.4"
    val dependencyManagementVersion = "1.1.0"

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.allopen" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.noarg" -> useVersion(kotlinVersion)

                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(dependencyManagementVersion)
            }
        }
    }
}

