package aithanasakis.com.android

import aithanasakis.com.android.showkase.getBrowserIntent
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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import com.airbnb.android.showkase.models.Showkase
import translations.TextProvider

class MainActivity : ComponentActivity() {
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val textProvider = TextProvider.getByLanguage(Locale.current.language)
            CompositionLocalProvider(LocalTextProvider provides textProvider) {
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
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (viewModel.keyPressHandled(keyCode)) {
            return true
        } else if (keyCode == KeyEvent.KEYCODE_P && BuildConfig.DEBUG) {
            //  adb shell input keyevent 44
            startActivity(Showkase.getBrowserIntent(this))
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}

val LocalTextProvider = compositionLocalOf<TextProvider> { translations.EnglishTextProvider() }