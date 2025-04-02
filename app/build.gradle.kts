plugins {
    alias(libs.plugins.scribbledash.android.application.compose)
}

android {
    namespace = "com.hrishi.scribbledash"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    // Use the Compose bundle
    implementation(libs.bundles.compose)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.koin.androidx.compose)

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.material.icons.extended)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.bundles.compose.test)

    // Debug dependencies
    debugImplementation(libs.bundles.compose.debug)

    implementation(libs.androidx.core.splashscreen)

    // Timber
    implementation(libs.timber)

    implementation(libs.bundles.koin)

    // Implement local modules
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.core.presentation.designsystem)
    implementation(projects.core.presentation.ui)
}