import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.run.android.library)
    alias(libs.plugins.run.jvm.ktor)
    alias(libs.plugins.compose.compiler)



}

android {
    namespace = "com.sri.auth.data"

}

dependencies {

    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.auth.domain)

}