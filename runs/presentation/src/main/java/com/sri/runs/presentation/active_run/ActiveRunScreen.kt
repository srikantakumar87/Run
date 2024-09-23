package com.sri.runs.presentation.active_run

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sri.core.presentation.designsystem.RunTheme
import com.sri.core.presentation.designsystem.StartIcon
import com.sri.core.presentation.designsystem.StopIcon
import com.sri.core.presentation.designsystem.components.RunFloatingActionButton
import com.sri.core.presentation.designsystem.components.RunScaffold
import com.sri.core.presentation.designsystem.components.RunToolbar
import com.sri.runs.presentation.R
import com.sri.runs.presentation.active_run.components.RunDataCard
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