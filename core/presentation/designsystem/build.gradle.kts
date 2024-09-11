plugins {
     alias(libs.plugins.run.android.library.compose)
     alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.sri.core.presentation.designsystem"

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.material3)
}