package ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size

@Composable
fun ProgressBarBackground(
    indicatorNumber: Float,
    modifier: Modifier = Modifier
) {

    val color = MaterialTheme.colors.primaryVariant
    val backgroundColor = MaterialTheme.colors.primary
    Canvas(
        modifier = modifier
            .fillMaxSize()
    ) {
        drawRect(
            color = backgroundColor,
            size = size
        )

        val progress = (indicatorNumber / 100) * size.width
        drawRect(
            size = Size(progress, size.height),
            color = color
        )
    }
}