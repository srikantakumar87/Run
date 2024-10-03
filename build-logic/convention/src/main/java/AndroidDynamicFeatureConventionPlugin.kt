import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.DynamicFeatureExtension
import com.sri.convention.ExtensionType
import com.sri.convention.addUiLayerDependencies
import com.sri.convention.configureAndroidCompose
import com.sri.convention.configureBuildTypes
import com.sri.convention.configureKotlinAndroid
import com.sri.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin


class AndroidDynamicFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.dynamic-feature")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<DynamicFeatureExtension> {
                configureKotlinAndroid(this)
                configureAndroidCompose(this)

                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.DYNAMIC_FEATURE
                )
            }

            dependencies {
                addUiLayerDependencies(target)
                "testImplementation"(kotlin("test"))
            }
        }
    }
}