plugins {
    alias(libs.plugins.run.android.feature.ui)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.sri.auth.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.auth.domain)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.room.ktx)

    //implementation(projects.auth.domain)
}