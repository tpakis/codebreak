package aithanasakis.com.android

import aithanasakis.com.android.ui.GameViewModel
import aithanasakis.com.android.ui.screens.GameScreen
import aithanasakis.com.android.ui.screens.InitialScreen
import aithanasakis.com.android.ui.theme.BreakCodeTheme
import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (viewModel.keyPressHandled(keyCode)) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}