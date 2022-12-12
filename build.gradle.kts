buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("com.android.tools.build:gradle:7.2.2")
    }
}

group = "aithanasakis.com"
version = "1.0"

allprojects {
    repositories {
        mavenCentral()
    }
}