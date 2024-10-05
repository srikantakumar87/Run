package com.sri.analytics.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sri.core.presentation.designsystem.RunTheme
import com.sri.core.presentation.designsystem.components.GradientBackground


@Composable
fun MountainGraph(
    data: List<Float>,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    colorBg: Color = MaterialTheme.colorScheme.surface
) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val path = Path()

        // Calculate scaling factors
        val xScale = width / (data.size - 1).toFloat()
        val yScale = height / data.maxOrNull()!!

        // Draw the mountain path
        path.moveTo(0f, height) // Start at bottom left
        data.forEachIndexed { index, value ->
            val x = index * xScale
            val y = height - (value * yScale)
            path.lineTo(x, y)
        }
        path.lineTo(width, height) // Close the path at bottom right
        path.close()


        drawPath(
            path,
            //color = color.copy(0.1f),
            brush = Brush.verticalGradient(
                colors = listOf(
                   color.copy(alpha = 0.3f),
                    colorBg
                )

            ),
            style = Fill
        )

        drawPath(
            path = path,
            color = color,
            style = Stroke(width = 2.dp.toPx())
        )

        // Draw the path with a color
        //drawPath(path, color = color)
    }
}

@Preview
@Composable
private fun MountainGraphPreview() {
    RunTheme {
        MountainGraph (
            data = listOf(1f, 3f, 2f, 4f, 1f, 4f, 2f, 3f, 1f),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surface)

            )
    }
}