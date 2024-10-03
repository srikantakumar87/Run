plugins {
    alias(libs.plugins.run.android.feature.ui)
    alias(libs.plugins.compose.compiler)

}

android {
    namespace = "com.sri.analytics.presentation"

}

dependencies {

    implementation(projects.analytics.domain)
}