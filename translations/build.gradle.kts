plugins {
    kotlin("multiplatform")
}

group = "aithanasakis.com"
version = "1.0"

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
    }
    sourceSets {
        val commonMain by getting
    }
}