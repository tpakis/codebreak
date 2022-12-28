package aithanasakis.com.android.ui

import Game
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import models.GameParameters

class GameViewModel: ViewModel() {
    private val _playingGame: MutableStateFlow<Game?> = MutableStateFlow(null)
    val playingGame: StateFlow<Game?> get() = _playingGame.asStateFlow()

    fun createNewGame(gameParameters: GameParameters) {
        _playingGame.value = Game.createNew(gameParameters)
    }
}