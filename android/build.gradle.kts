plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android-extensions")
}

group = "aithanasakis.com"
version = "1.0"

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(Deps.Android.material)
    implementation(Deps.Android.app_compat)
    implementation(Deps.Android.core_ktx)
    implementation(Deps.Android.lifecycle_ktx)
    implementation(Deps.Android.activity_compose)
    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.ui_tooling_preview)
    implementation(Deps.Compose.material)
    testImplementation(Deps.Test.jUnit)
    debugImplementation(Deps.Compose.debug_ui_tooling)
    debugImplementation(Deps.Compose.ui_test_manifest)
    implementation(Deps.Compose.material_icons_extended)
    implementation(Deps.showkase)
    kapt(Deps.showkase_kapt)
    implementation(project(Deps.Modules.gameLibrary))
    implementation(project(Deps.Modules.translations))
}

android {
    compileSdk = Versions.androidCompileSdk
    defaultConfig {
        applicationId = "aithanasakis.com.android.codebreak"
        minSdk = Versions.androidMinSdk
        targetSdk = Versions.androidTargetSdk
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}