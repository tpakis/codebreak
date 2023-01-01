package aithanasakis.com.android.ui

import Game
import android.view.KeyEvent
import androidx.lifecycle.ViewModel
import code.models.CodeDigit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import models.GameParameters

class GameViewModel : ViewModel() {
    private val _playingGame: MutableStateFlow<Game?> = MutableStateFlow(null)
    val playingGame: StateFlow<Game?> get() = _playingGame.asStateFlow()

    fun createNewGame(gameParameters: GameParameters) {
        _playingGame.value = Game.createNew(gameParameters)
    }

    fun keyPressHandled(keyCode: Int): Boolean {
        val codeDigit = keyCode.toCodeDigit() ?: return false

        CodeInputHandler.newCodeDigitInput(codeDigit)
        return true
    }

    private fun Int.toCodeDigit(): CodeDigit? {
        return when (this) {
            KeyEvent.KEYCODE_1 -> CodeDigit.White
            KeyEvent.KEYCODE_2 -> CodeDigit.Black
            KeyEvent.KEYCODE_3 -> CodeDigit.Blue
            KeyEvent.KEYCODE_4 -> CodeDigit.Aqua
            KeyEvent.KEYCODE_5 -> CodeDigit.Pink
            KeyEvent.KEYCODE_6 -> CodeDigit.Red
            KeyEvent.KEYCODE_7 -> CodeDigit.Orange
            KeyEvent.KEYCODE_8 -> CodeDigit.Yellow
            else -> null
        }
    }

}