plugins {
    alias(libs.plugins.scribbledash.android.library)
}

android {
    namespace = "com.scribbledash.oneRoundWonder.data"
}

dependencies {
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.features.oneRoundWonder.domain)
}