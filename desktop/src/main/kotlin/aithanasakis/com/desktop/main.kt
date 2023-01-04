package aithanasakis.com.desktop

import aithanasakis.com.desktop.ui.GameViewModel
import aithanasakis.com.desktop.ui.screens.GameScreen
import aithanasakis.com.desktop.ui.screens.InitialScreen
import aithanasakis.com.desktop.ui.theme.BreakCodeTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.key
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

private val viewModel = GameViewModel()

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Code Breaker",
        state = WindowState(size = DpSize(1440.dp, 768.dp)),
        onKeyEvent = {
            viewModel.keyPressHandled(it.key.keyCode.toInt())
        }
    ) {
        BreakCodeTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                val game = viewModel.playingGame.collectAsState().value
                when (game) {
                    null -> InitialScreen(viewModel)
                    else -> GameScreen(game, viewModel::clearGame)
                }
            }
        }
    }
}
