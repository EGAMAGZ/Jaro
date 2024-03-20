import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.truncate

fun LocalDate.daysInYear() = if (isLeapYear()) 366 else 365
fun LocalDate.isLeapYear() = when {
    year % 4 == 0 && year % 100 != 0 -> true
    year % 400 == 0 -> true
    else -> false
}

fun LocalDate.remainingDaysInYear() = daysInYear() - dayOfYear

fun LocalDate.percentageOfYear() = truncate((dayOfYear.toFloat() / daysInYear().toFloat()) * 100).toInt()

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    val actualDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
    var animationStarted by rememberSaveable { mutableStateOf(false) }
    val animatePercentage by animateIntAsState(
        targetValue = if (animationStarted) actualDate.percentageOfYear() else 0,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 500,
            easing = EaseInOut
        )
    )

    LaunchedEffect(Unit) {
        animationStarted = true
    }

    MaterialTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Hello, World!")
            Text("${actualDate.year} year")
            Text("${if (actualDate.isLeapYear()) "Leap" else "Regular"} year")
            Text("${actualDate.daysInYear()} days")
            Text("${actualDate.remainingDaysInYear()} days left")
            Text(
                text = "${animatePercentage}%",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.h1
            )
        }
    }
}