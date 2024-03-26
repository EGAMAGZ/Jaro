package navigation

sealed class AppRoutes(val route: String) {

    data object Splash : AppRoutes("/")
    data object Home : AppRoutes("/home")
}