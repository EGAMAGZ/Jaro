import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import util.extensions.daysInYear
import util.extensions.isLeapYear
import util.extensions.percentageOfYear
import util.extensions.remainingDaysInYear

@Composable
fun CustomProgressBar() {
    Box {
        Canvas(
            modifier = Modifier.fillMaxWidth()
                .height(20.dp)
        ) {
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    val actualDate = Clock.System.todayIn(TimeZone.currentSystemDefault())

    var animationStarted by rememberSaveable { mutableStateOf(false) }
    val transition = updateTransition(animationStarted, "animation")

    val percentage by transition.animateInt(
        transitionSpec = {
            tween(
                durationMillis = 1000,
                delayMillis = 500
            )
        },
    ) {
        if (it) actualDate.percentageOfYear else 0
    }

    LaunchedEffect(Unit) {
        animationStarted = true
    }

    MaterialTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Hello, World!")
            Text("${actualDate.year} year")
            Text("${if (actualDate.isLeapYear) "Leap" else "Regular"} year")
            Text("${actualDate.daysInYear} days")
            Text("${actualDate.remainingDaysInYear} days left")
            Text(
                text = "${percentage}%",
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            )
        }
    }
}