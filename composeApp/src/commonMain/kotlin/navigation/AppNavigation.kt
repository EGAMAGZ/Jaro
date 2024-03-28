package navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import io.github.aakira.napier.Napier
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import ui.screens.home.HomeScreen
import ui.screens.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navigator = rememberNavigator()

    NavHost(
        navigator = navigator,
        initialRoute = AppRoutes.Splash.route,
        navTransition = NavTransition(
            createTransition = fadeIn(),
            destroyTransition = fadeOut()
        )
    ) {
        scene(route = AppRoutes.Home.route) {
            Napier.i { "Navigation to ${it.route.route}" }
            HomeScreen()
        }
        scene(
            route = AppRoutes.Splash.route,
            navTransition = NavTransition(
                createTransition = fadeIn() + scaleIn(),
                destroyTransition = fadeOut() + scaleOut()
            )
        ) {
            Napier.i { "Navigation to ${it.route.route}" }
            SplashScreen(navigator = navigator)
        }
    }
}