package aithanasakis.com.desktop.ui

import Game
import code.models.CodeDigit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import models.GameParameters
import java.awt.event.KeyEvent

class GameViewModel {
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
            KeyEvent.VK_1 -> CodeDigit.getById(0)
            KeyEvent.VK_2 -> CodeDigit.getById(1)
            KeyEvent.VK_3 -> CodeDigit.getById(2)
            KeyEvent.VK_4 -> CodeDigit.getById(3)
            KeyEvent.VK_5 -> CodeDigit.getById(4)
            KeyEvent.VK_6 -> CodeDigit.getById(5)
            KeyEvent.VK_7 -> CodeDigit.getById(6)
            KeyEvent.VK_8 -> CodeDigit.getById(7)
            else -> null
        }
    }

}