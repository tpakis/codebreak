object Deps {
    object Android {
        const val material = "com.google.android.material:material:${Versions.material}"
        const val app_compat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
        const val lifecycle_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_ktx}"
        const val activity_compose = "androidx.activity:activity-compose:${Versions.activity_compose}"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.compose_ui}"
        const val ui_tooling_preview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose_ui}"
        const val material = "androidx.compose.material:material:${Versions.compose_material}"
        const val debug_ui_tooling = "androidx.compose.ui:ui-tooling:${Versions.compose_ui}"
        const val ui_test_manifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose_ui}"
        const val material_icons_extended="androidx.compose.material:material-icons-extended:${Versions.extended_icons}"
    }

    object Modules {
        const val gameLibrary = ":library"
        const val translations = ":translations"
    }

    object Test {
        const val jUnit = "junit:junit:${Versions.junit}"
    }

    const val showkase = "com.airbnb.android:showkase:${Versions.showkase_version}"
    const val showkase_kapt = "com.airbnb.android:showkase-processor:${Versions.showkase_version}"
}