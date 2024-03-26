import androidx.compose.runtime.Composable
import moe.tlaster.precompose.PreComposeApp
import navigation.AppNavigation
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.JaroTheme


@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    PreComposeApp {
        JaroTheme {
            AppNavigation()
        }
    }
}