import org.gradle.api.Project
import org.gradle.api.Plugin
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.sri.convention.ExtensionType
import com.sri.convention.configureBuildTypes
import com.sri.convention.configureKotlinAndroid
import com.sri.convention.configureKotlinJvm
import com.sri.convention.libs
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class JvmKtorConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {

        target.run{
           pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")
          dependencies {
              "implementation"(libs.findBundle("ktor").get())
          }

        }

    }

}