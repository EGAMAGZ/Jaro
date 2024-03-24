import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import util.extensions.percentageOfYear
import util.extensions.remainingDaysInYear

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

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    val actualDate = Clock.System.todayIn(TimeZone.currentSystemDefault())

    var animationStarted by rememberSaveable { mutableStateOf(false) }
    val transition = updateTransition(animationStarted, "animation")
    println(actualDate.percentageOfYear)

    val percentage by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 1000,
                delayMillis = 500
            )
        },
    ) {
        if (it) actualDate.percentageOfYear else 0f
    }

    LaunchedEffect(Unit) {
        animationStarted = true
    }

    MaterialTheme {

        Box {
            ProgressBarBackground(
                indicatorNumber = percentage,
            )
            Column(

                modifier = Modifier
                    .align(Alignment.Center),
            ) {
                Text(
                    text = "${percentage.toInt()}%",
                    style = MaterialTheme.typography.h1
                )
                Text(
                    text = "${actualDate.remainingDaysInYear} days remaining.",
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}