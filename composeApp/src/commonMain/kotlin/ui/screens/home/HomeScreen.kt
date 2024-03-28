package ui.screens.home

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.datetime.LocalDate
import moe.tlaster.precompose.viewmodel.viewModel
import ui.components.PercentageText
import ui.components.ProgressBarBackground
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
        viewModel.startTimer()
    }

    val actualDate by viewModel.actualDate.collectAsState()

    HomeContent(
        animationStarted = animationStarted,
        actualDate = actualDate
    )
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

    Box {
        ProgressBarBackground(indicatorNumber = percentage)

        PercentageText(
            percentage = percentage,
            modifier = Modifier.align(Alignment.Center)
        )
    }
    /*Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Text("${actualDate.percentageOfYear}")
        //Text("${actualDate.roundedPercentageOfYear}")
    }*/
}
