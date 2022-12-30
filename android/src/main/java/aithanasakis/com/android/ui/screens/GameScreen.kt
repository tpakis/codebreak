package aithanasakis.com.android.ui.screens

import Game
import aithanasakis.com.android.ui.components.CodeInputRow
import aithanasakis.com.android.ui.components.FocusableGameButton
import aithanasakis.com.android.ui.components.GameTimerText
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.StateFlow
import models.GameState

@Composable
fun GameScreen(game: Game) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Row(modifier = Modifier.weight(1f)) {
                AttemptsList(modifier = Modifier.weight(1f), gameStateFlow = game.gameState)
                Column {
                    Timer(game.remainingGameTimeInSeconds)
                    GameRunControls(modifier = Modifier.align(alignment = Alignment.CenterHorizontally), game = game)
                }
            }
            InputRow(
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally).padding(bottom = 8.dp),
                game = game
            )

        }
    }

}

@Composable
fun AttemptsList(modifier: Modifier = Modifier, gameStateFlow: StateFlow<GameState>) {
    Row(modifier) {
        Text("Text")
    }
}


@Composable
fun InputRow(modifier: Modifier = Modifier, game: Game) {
    CodeInputRow(
        modifier = modifier,
        width = 400.dp,
        onCheckClick = game::tryNewCode
    )
}


@Composable
fun Timer(timeFlow: StateFlow<Long>) {
    val remainingTime = timeFlow.collectAsState().value

    GameTimerText(
        modifier = Modifier.padding(top = 16.dp, end = 16.dp, bottom = 8.dp),
        fontSize = 45.sp,
        remainingMillis = remainingTime.times(1000)
    )
}

@Composable
fun GameRunControls(
    modifier: Modifier = Modifier,
    game: Game,
) {
    val firstFocusRequester = FocusRequester()
    val gameState = game.gameState.collectAsState().value
    val buttonModifier = Modifier
        .size(height = 64.dp, width = 160.dp)
        .padding(8.dp)
    Column(modifier = modifier) {
        when (gameState) {
            is GameState.NotStarted -> {
                FocusableGameButton(
                    onClick = { game.startOrResume() },
                    text = "Start Game",
                    modifier = buttonModifier.focusRequester(firstFocusRequester)
                )
            }

            is GameState.Running -> {
                FocusableGameButton(
                    onClick = { game.pause() },
                    text = "Pause Game",
                    modifier = buttonModifier
                )
                FocusableGameButton(
                    onClick = { game.quit() },
                    text = "Quit Game",
                    modifier = buttonModifier
                )
            }

            is GameState.Paused -> {
                FocusableGameButton(
                    onClick = { game.startOrResume() },
                    text = "Resume Game",
                    modifier = buttonModifier
                )
                FocusableGameButton(
                    onClick = { game.quit() },
                    text = "Quit Game",
                    modifier = buttonModifier
                )
            }

            is GameState.Finished -> {
                FocusableGameButton(
                    onClick = { game.quit() },
                    text = "Quit Game",
                    modifier = buttonModifier
                )
            }
        }
    }
    LaunchedEffect("key") {
        firstFocusRequester.requestFocus()
    }
}