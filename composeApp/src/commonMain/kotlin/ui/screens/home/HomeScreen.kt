package ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import kotlinx.datetime.LocalDate
import moe.tlaster.precompose.viewmodel.viewModel
import util.Util
import util.extensions.percentageOfYear
import viewmodels.HomeViewModel

@Composable
fun HomeScreen() {
    val viewModel = viewModel(HomeViewModel::class) {
        HomeViewModel()
    }
    var animationStarted by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        animationStarted = true
    }

    val actualDate by viewModel.actualDate.collectAsState()

    HomeContent(
        animationStarted = animationStarted,
        actualDate = actualDate
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PercentageText(
    percentage: Float
) {
    var showDecimals by rememberSaveable { mutableStateOf(false) }

    Util.roundedPair(percentage) {
        CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {

            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.clickable {
                    showDecimals = showDecimals.not()
                }

            ) {
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

@Composable
private fun HomeContent(
    animationStarted: Boolean,
    actualDate: LocalDate
) {

    val transition = updateTransition(animationStarted, "percentage_animation")

    val percentage by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 1000,
                delayMillis = 300
            )
        }
    ) {
        if (it) actualDate.percentageOfYear else 0f
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PercentageText(percentage = percentage)

        //Text("${actualDate.percentageOfYear}")
        //Text("${actualDate.roundedPercentageOfYear}")
    }
}

private object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f, 0.0f, 0.0f, 0.0f)
}