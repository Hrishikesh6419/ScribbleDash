plugins {
    alias(libs.plugins.scribbledash.android.library)
}

android {
    namespace = "com.scribbledash.home.data"
}

dependencies {
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.features.home.domain)
}