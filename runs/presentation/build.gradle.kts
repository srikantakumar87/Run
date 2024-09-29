plugins {
    alias(libs.plugins.run.android.feature.ui)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.mapsplatform.secrets.plugin)
}

android {
    namespace = "com.sri.runs.presentation"

}

dependencies {

    implementation(libs.coil.compose)
    implementation(libs.google.maps.android.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation(projects.runs.domain)
}