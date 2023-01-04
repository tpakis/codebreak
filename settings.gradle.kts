pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        jcenter()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    
}
rootProject.name = "CodeBreak"

include(":library")
include(":android")
include(":desktop")

