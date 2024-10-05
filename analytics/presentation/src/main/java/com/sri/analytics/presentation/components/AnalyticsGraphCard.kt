package com.sri.analytics.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sri.core.presentation.designsystem.RunTheme
import kotlin.io.path.moveTo


@Composable
fun AnalyticsGraphCard(
    title: String,
    modifier: Modifier
){
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))

    ) {
        Row(
            modifier = modifier

                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
                .padding(16.dp),

        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Move forward",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )



        }
        Row(
            modifier = modifier

                .background(MaterialTheme.colorScheme.surface)


        ) {
            MountainGraph(
                data = listOf(1f, 3f, 2f, 4f, 1f),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.surface)
            )

        }
    }


}

@Preview
@Composable
private fun AnalyticsGraphCardPreview() {
    RunTheme {
        AnalyticsGraphCard("Avg. Distance per run over Time", Modifier)
    }
}