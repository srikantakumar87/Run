plugins {
    alias(libs.plugins.run.android.library)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.sri.runs.location"

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.koin)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.material3)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.google.android.gms.play.services.location)

    implementation(projects.core.domain)
    implementation(projects.runs.domain)
}