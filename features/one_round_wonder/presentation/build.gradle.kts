plugins {
    alias(libs.plugins.scribbledash.android.feature.ui)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.scribbledash.oneRoundWonder.core"
}

dependencies {
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.koin.androidx.compose)

    // Project Dependencies
    implementation(projects.core.domain)
    implementation(projects.features.oneRoundWonder.domain)
}