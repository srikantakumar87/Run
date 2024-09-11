import org.gradle.api.Project
import org.gradle.api.Plugin
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.sri.convention.ExtensionType
import com.sri.convention.addUiLayerDependencies
import com.sri.convention.configureAndroidCompose
import com.sri.convention.configureBuildTypes
import com.sri.convention.configureKotlinAndroid
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

class AndroidFeatureUiConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {

        target.run{
            pluginManager.run{
                apply("run.android.library.compose")


            }
           dependencies {
               addUiLayerDependencies(target)

           }


        }

    }

}