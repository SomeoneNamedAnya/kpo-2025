pluginManagement {
    plugins {
        kotlin("jvm") version "1.9.24"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "kpo-2025"
include("Homeworks")
include("Homeworks:HW1")
findProject(":Homeworks:HW1")?.name = "HW1"
