plugins {
    alias(libs.plugins.run.android.library)
    alias(libs.plugins.run.jvm.ktor)
}

android {
    namespace = "com.sri.core.data"



}

dependencies {

    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}