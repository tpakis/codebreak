import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose").version("1.3.0-rc01")
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.materialIconsExtended)
    implementation(project(":library"))
}

compose.desktop {
    application {
        mainClass = "aithanasakis.com.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "KotlinJvmComposeDesktopApplication"
            packageVersion = "1.0.0"
        }
    }
}
