package ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import util.Util
import util.extensions.noRippleClickable

@Composable
fun PercentageText(
    percentage: Float,
    modifier: Modifier = Modifier
) {
    var showDecimals by rememberSaveable { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = modifier.noRippleClickable {
            showDecimals = showDecimals.not()
        }
    ) {
        Util.roundedPair(percentage) {
            Text("${it.first}", style = MaterialTheme.typography.h1)
            AnimatedVisibility(
                visible = showDecimals,
            ) {
                Text(
                    text = "${it.second}".padStart(2, '0'),
                    style = MaterialTheme.typography.h3.copy(
                        fontWeight = FontWeight.Light
                    )
                )
            }
            Text(
                "%", style = MaterialTheme.typography.h1
            )
        }
    }

}