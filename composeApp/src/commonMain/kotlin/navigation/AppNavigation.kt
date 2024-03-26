package navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
        scene(AppRoutes.Home.route) {
            Napier.i { "Navigation to ${it.route}" }
            HomeScreen()
        }
        scene(AppRoutes.Splash.route) {
            Napier.i { "Navigation to ${it.route}" }
            SplashScreen(navigator = navigator)
        }
    }
}