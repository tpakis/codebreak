import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose").version(Versions.compose_multiplatform)
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.materialIconsExtended)
    implementation(project(Deps.Modules.gameLibrary))
    implementation(project(Deps.Modules.translations))
}

compose.desktop {
    application {
        mainClass = "aithanasakis.com.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "CodeBreakerDesktopApplication"
            packageVersion = "1.0.0"
            macOS {
                iconFile.set(project.file("code-breaker.icns"))
            }
            windows {
                iconFile.set(project.file("code-breaker.ico"))
            }
            linux {
                iconFile.set(project.file("code-breaker.png"))
            }
        }
    }
}
