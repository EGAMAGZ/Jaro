package ui.screens.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassFull
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import jaro.composeapp.generated.resources.Res
import jaro.composeapp.generated.resources.brand_png
import kotlinx.coroutines.delay
import moe.tlaster.precompose.navigation.Navigator
import navigation.AppRoutes
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashScreen(
    navigator: Navigator
) {
    var animationStarted by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(500L)
        animationStarted = true
        delay(1000L)
        navigator.navigate(AppRoutes.Home.route)
    }
    SplashContent()
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun SplashContent() {
    val infiniteTransition = rememberInfiniteTransition()

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1f)
                    .fillMaxWidth()
            ) {
                // TODO: Replace animation
                Icon(
                    Icons.Default.HourglassFull,
                    null,
                    modifier = Modifier
                        .size(96.dp)
                        .rotate(rotation)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Column {
                    Text(
                        text = "Developed by",
                        style = MaterialTheme.typography.caption.copy(
                            fontWeight = FontWeight.Thin
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    // TODO: CHANGE TO SVG THE BRAND LOGO
                    Image(
                        painter = painterResource(Res.drawable.brand_png),
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}