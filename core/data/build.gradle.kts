plugins {
    alias(libs.plugins.scribbledash.android.library)
}

android {
    namespace = "com.scribbledash.core.data"
}

dependencies {
    implementation(libs.timber)
    implementation(libs.bundles.koin)
    implementation(projects.core.domain)
    implementation(libs.play.services.time)
}