package com.sri.runs.presentation.active_run

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sri.core.presentation.designsystem.RunTheme
import com.sri.core.presentation.designsystem.StartIcon
import com.sri.core.presentation.designsystem.StopIcon
import com.sri.core.presentation.designsystem.components.RunDialog
//import com.sri.core.presentation.designsystem.components.RunDialog
import com.sri.core.presentation.designsystem.components.RunFloatingActionButton
import com.sri.core.presentation.designsystem.components.RunOutlinedActionButton
import com.sri.core.presentation.designsystem.components.RunScaffold
import com.sri.core.presentation.designsystem.components.RunToolbar
import com.sri.runs.presentation.R
import com.sri.runs.presentation.active_run.components.RunDataCard
import com.sri.runs.presentation.util.hasLocationPermission
import com.sri.runs.presentation.util.hasNotificationPermission
import com.sri.runs.presentation.util.shouldShowLocationPermissionRationale
import com.sri.runs.presentation.util.shouldShowNotificationPermissionRationale
/*import com.sri.runs.presentation.util.hasLocationPermission
import com.sri.runs.presentation.util.hasNotificationPermission
import com.sri.runs.presentation.util.shouldShowLocationPermissionRationale
import com.sri.runs.presentation.util.shouldShowNotificationPermissionRationale*/
import org.koin.androidx.compose.koinViewModel




@Composable
fun ActiveRunScreenRoot(
    viewModel: ActiveRunViewModel = koinViewModel()
){

    ActiveRunScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ActiveRunScreen(
    state: ActiveRunState,
    onAction: (ActiveRunAction) -> Unit
){
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
        ){ perms ->

            val hasCourseLocationPermission = perms[
                Manifest.permission.ACCESS_COARSE_LOCATION
            ] == true
            val hasFineLocationPermission = perms[
                Manifest.permission.ACCESS_FINE_LOCATION
            ] == true
            val hasNotificationPermission = if(Build.VERSION.SDK_INT >= 33){
                perms[Manifest.permission.POST_NOTIFICATIONS] == true
            }else true

        val activity = context as ComponentActivity
        val showLocationRationale = activity.shouldShowLocationPermissionRationale()
        val showNotificationRationale = activity.shouldShowNotificationPermissionRationale()

        onAction(
            ActiveRunAction.SubmitLocationPermissionInfo(
                acceptedLocationPermission = hasCourseLocationPermission && hasFineLocationPermission,
                showLocationRationale = showLocationRationale
            )
        )
        onAction(
            ActiveRunAction.SubmitNotificationPermissionInfo(
                acceptedNotificationPermission = hasNotificationPermission,
                showNotificationPermissionRationale = showNotificationRationale
            )
        )


    }
    LaunchedEffect(key1 = true){
        val activity = context as ComponentActivity
        val showLocationRationale = activity.shouldShowLocationPermissionRationale()
        val showNotificationRationale = activity.shouldShowNotificationPermissionRationale()

        onAction(
            ActiveRunAction.SubmitLocationPermissionInfo(
                acceptedLocationPermission = context.hasLocationPermission(),
                showLocationRationale = showLocationRationale
            )
        )
        onAction(
            ActiveRunAction.SubmitNotificationPermissionInfo(
                acceptedNotificationPermission = context.hasNotificationPermission(),
                showNotificationPermissionRationale = showNotificationRationale
            )
        )

        if(!showLocationRationale && !showNotificationRationale){
            permissionLauncher.requestRunPermissions(context)
        }



    }

    RunScaffold(
        withGradient = false,
        topAppBar = {
            RunToolbar(
                showBackButton = true,
                title = stringResource(id= R.string.active_run_title),
                onBackClick = {
                    onAction(ActiveRunAction.OnBackClick)
                }
            ) { }
        },
        floatingActionButton = {
            RunFloatingActionButton(
                icon = if(state.shouldTrack){
                    StopIcon
                }
                else {
                    StartIcon
                },
                onClick = {
                    onAction(ActiveRunAction.OnToggleRunClick)
                },
                iconSize = 20.dp,
                contentDescription = if(state.shouldTrack){
                    stringResource(id = R.string.pause_run)
                }
                else {
                    stringResource(id = R.string.start_run)
                }
            )


        }

    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ){
            RunDataCard(
                elapsedTime = state.elapsedTime,
                runData = state.runData,
                modifier = Modifier
                    .padding(16.dp)
                    .padding(padding)
                    .fillMaxWidth()
            )
        }
    }

    if(state.showLocationRationale || state.showNotificationRationale){
        RunDialog(
            title = stringResource(id= R.string.permission_required),
            onDismiss = {
                /*Normal dismissing not allowed for permission dialogs*/
            },
            description = when{
                state.showLocationRationale && state.showNotificationRationale ->{
                    stringResource(id = R.string.location_notification_rationale)
                }
                state.showLocationRationale -> {
                    stringResource(id = R.string.location_rationale)
                }
                else -> {
                    stringResource(id = R.string.notification_rationale)
                }
            },
            primaryButton = {
                RunOutlinedActionButton(
                    text = stringResource(id= R.string.okay),
                    isLoading =false,
                    onClick = {
                        onAction(ActiveRunAction.DismissRationaleDialog)
                        permissionLauncher.requestRunPermissions(context)

                    }
                )
            },
            secondaryButton = {},
            modifier = Modifier
            )


    }
}

private fun ActivityResultLauncher<Array<String>>.requestRunPermissions(
    context: Context

){
    val hasLocationPermission = context.hasLocationPermission()
    val hasNotificationPermission = context.hasNotificationPermission()

    val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION

    )
    val notificationPermissions = if(Build.VERSION.SDK_INT >= 33){
        arrayOf(Manifest.permission.POST_NOTIFICATIONS)

    }else
    {
        arrayOf()
    }
    when{
        !hasLocationPermission && !hasNotificationPermission -> {
            launch(locationPermissions + notificationPermissions)
        }
        !hasLocationPermission ->{
            launch(locationPermissions)
        }
        !hasNotificationPermission ->{
            launch(notificationPermissions)
        }

    }

}

@Preview
@Composable
fun ActiveRunScreenPreview(){
    RunTheme {
        ActiveRunScreen(
            state = ActiveRunState(),
            onAction = {}
        )

    }

}