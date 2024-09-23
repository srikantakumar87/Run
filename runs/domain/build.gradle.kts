plugins {
    alias(libs.plugins.run.jvm.library)
}

dependencies{
    implementation(libs.kotlinx.coroutines.core)
    implementation(project(":core:domain"))
}