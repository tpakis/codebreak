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

    fun clearGame() {
        _playingGame.value = null
        CodeInputHandler.clearAll()
    }

    fun keyPressHandled(keyCode: Int): Boolean {
        val codeDigit = keyCode.toCodeDigit() ?: return false

        CodeInputHandler.newCodeDigitInput(codeDigit)
        return true
    }

    private fun Int.toCodeDigit(): CodeDigit? {
        return when (this) {
            KeyEvent.KEYCODE_1 -> CodeDigit.getById(0)
            KeyEvent.KEYCODE_2 -> CodeDigit.getById(1)
            KeyEvent.KEYCODE_3 -> CodeDigit.getById(2)
            KeyEvent.KEYCODE_4 -> CodeDigit.getById(3)
            KeyEvent.KEYCODE_5 -> CodeDigit.getById(4)
            KeyEvent.KEYCODE_6 -> CodeDigit.getById(5)
            KeyEvent.KEYCODE_7 -> CodeDigit.getById(6)
            KeyEvent.KEYCODE_8 -> CodeDigit.getById(7)
            else -> null
        }
    }

}