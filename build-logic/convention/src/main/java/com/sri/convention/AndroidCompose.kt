package com.sri.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>

    ){
    commonExtension.run {
        buildFeatures{
            compose = true
        }


        dependencies {
            //"implementation"("androidx.compose.runtime:runtime:1.6.6")
            //"implementation"("androidx.compose.runtime:runtime-livedata:1.5.1")
            //"implementation"("androidx.compose.runtime:runtime-rxjava2:1.5.1")

            val bom = libs.findLibrary("androidx.compose.bom").get()
            "implementation"(platform(bom))
            "androidTestImplementation"(platform(bom))
            "debugImplementation"(libs.findLibrary("androidx.compose.ui.tooling.preview").get())
        }
    }

}