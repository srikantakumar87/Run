plugins {
    alias(libs.plugins.run.android.library)
}

android {
    namespace = "com.sri.analytics.data"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(projects.core.database)
    implementation(libs.bundles.koin)
    implementation(projects.analytics.domain)
    implementation(projects.core.domain)


}