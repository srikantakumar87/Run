package com.sri.run

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sri.auth.presentation.intro.IntroScreenRoot
import com.sri.auth.presentation.login.LoginScreenRoot
import com.sri.auth.presentation.register.RegisterScreenRoot
import com.sri.runs.presentation.active_run.ActiveRunScreenRoot
import com.sri.runs.presentation.run_overview.RunOverviewScreenRoot

//import com.sri.auth.presentation.register.RegisterScreenRoot

@Composable
fun NavigationRoot(
    navController: NavHostController,
    isLoggedIn: Boolean
){
    NavHost(
        navController = navController,
        startDestination = if(isLoggedIn) "runs" else "auth"){

        authGraph(navController)
        runGraph(navController)

    }


}

private fun NavGraphBuilder.authGraph(navController: NavHostController){
    navigation(
       startDestination = "intro",
        route = "auth"
    ){
        composable(route = "intro"){
            IntroScreenRoot(
                onSignUpClick = {
                    navController.navigate("register")
                },
                onSignInClick = {
                    navController.navigate("login")
                }

            )


        }
        composable(route = "register"){
            RegisterScreenRoot(
                onSignInClick = {
                    navController.navigate("login"){
                        popUpTo("register"){
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSuccessfulRegistration = {
                    navController.navigate("login")
                }
            )
        }
        composable("login"){

            LoginScreenRoot(
                onLoginSuccess = {
                    navController.navigate("runs"){
                        popUpTo("auth"){
                            inclusive = true

                        }

                    }


                },
                onSignUpClick = {

                    navController.navigate("register"){
                        popUpTo("login"){
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }

                }

            )

        }

    }
}

private fun NavGraphBuilder.runGraph(navController: NavHostController){
    navigation(
        startDestination = "run_overview",
        route = "runs"
    ) {
        composable(route = "run_overview") {

            RunOverviewScreenRoot(
                onStartRunClick = {
                    navController.navigate("active_run")
                }
            )
        }
        composable(route = "active_run") {
            ActiveRunScreenRoot()
        }

    }
}