plugins {
    alias(libs.plugins.run.android.feature.ui)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.sri.runs.presentation"

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}