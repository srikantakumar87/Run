plugins {
    alias(libs.plugins.run.android.library)
    alias(libs.plugins.compose.compiler)



}

android {
    namespace = "com.sri.core.data"
}

dependencies {

    implementation(libs.timber)
    implementation(libs.bundles.koin)
    implementation(projects.core.domain)
    implementation(projects.core.database)

}