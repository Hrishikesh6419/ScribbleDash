plugins {
    alias(libs.plugins.scribbledash.android.library)
}

android {
    namespace = "com.scribbledash.statistics.data"
}

dependencies {
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.features.statistics.domain)
}