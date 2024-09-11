plugins {
    alias(libs.plugins.run.android.library)
    alias(libs.plugins.run.android.room)
    alias(libs.plugins.compose.compiler)

}
android {
    namespace = "com.sri.core.database"

}

dependencies {

    implementation(libs.org.mongodb.bson)
    implementation(projects.core.domain)

}