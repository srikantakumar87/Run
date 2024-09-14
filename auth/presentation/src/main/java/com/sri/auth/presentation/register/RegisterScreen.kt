package com.sri.auth.presentation.register

import androidx.compose.runtime.Composable

@Composable
fun RegisterScreenRoot(
    navController: androidx.navigation.NavController,
    viewModel: RegisterViewModel = androidx.hilt.navigation.compose.hiltViewModel(),
){
    RegisterScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )

}