package ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import ui.theme.NoRippleTheme
import util.Util

@Composable
fun PercentageText(
    percentage: Float,
    modifier: Modifier = Modifier
) {
    var showDecimals by rememberSaveable { mutableStateOf(false) }

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = modifier.clickable {
                showDecimals = showDecimals.not()
            }

        ) {
            Util.roundedPair(percentage) {

                Text("${it.first}", style = MaterialTheme.typography.h1)
                AnimatedVisibility(
                    visible = showDecimals,
                ) {
                    Text(
                        text = "${it.second}",
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
}