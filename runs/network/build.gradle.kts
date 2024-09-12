plugins {
    alias(libs.plugins.run.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.run.jvm.ktor)
}

android {
    namespace = "com.sri.runs.network"

}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.core.data)
}