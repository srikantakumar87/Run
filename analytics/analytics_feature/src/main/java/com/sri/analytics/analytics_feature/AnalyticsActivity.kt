package com.sri.analytics.analytics_feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.play.core.splitcompat.SplitCompat
import com.sri.analytics.data.di.analyticsModule
import com.sri.analytics.presentation.AnalyticsDashboardScreenRoot
import com.sri.analytics.presentation.di.analyticsPresentationModule
import com.sri.core.presentation.designsystem.RunTheme
import org.koin.core.context.loadKoinModules

class AnalyticsActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(
            listOf(
                analyticsModule,
                analyticsPresentationModule
            )

        )
        SplitCompat.installActivity(this)

        setContent{
            RunTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "analytics_dashboard"
                ){
                    composable("analytics_dashboard"){
                        AnalyticsDashboardScreenRoot(
                            onBackClick = { finish()}

                        )

                    }
                }
            }

        }


    }
}