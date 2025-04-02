plugins {
    alias(libs.plugins.scribbledash.jvm.library)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.junit.junit)
}