package ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun JaroTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        shapes = Shapes,
        content = content
    )
}