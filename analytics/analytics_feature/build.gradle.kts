plugins {
    alias(libs.plugins.run.android.dynamic.feature)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.compose.compiler)
}
android {
    namespace = "com.sri.analytics.analytics_feature"

}

dependencies {
    implementation(project(":app"))
    api(projects.analytics.presentation)
    implementation(projects.analytics.domain)
    implementation(projects.analytics.data)
    implementation(projects.core.database)
}