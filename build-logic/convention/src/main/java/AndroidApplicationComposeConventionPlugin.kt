import com.android.build.api.dsl.ApplicationExtension
import com.sri.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {

        target.run {
            pluginManager.apply("run.android.application")
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")
            //pluginManager.apply("androidx.compose.runtime:runtime:1.5.1")

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)

        }

    }


}