
import androidx.compose.runtime.Composable
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import moe.tlaster.precompose.PreComposeApp
import navigation.AppNavigation
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.JaroTheme


@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    Napier.base(DebugAntilog())
    PreComposeApp {
        JaroTheme {
            AppNavigation()
        }
    }
}