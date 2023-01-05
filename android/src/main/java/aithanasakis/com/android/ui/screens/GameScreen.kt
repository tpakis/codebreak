package aithanasakis.com.android.ui.screens

import Game
import aithanasakis.com.android.ui.components.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.StateFlow
import models.GameState
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
fun GameScreen(game: Game, onQuitGame: () -> Unit) {
    var showEndGameDialog by remember { mutableStateOf(false) }

    if (showEndGameDialog && game.gameState.value is GameState.Finished) {
        GameResultDialog(
            gameResult = (game.gameState.value as GameState.Finished).result,
            correctCode = game.getCodeToBreak(),
            onDismiss = { showEndGameDialog = false }
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.weight(1f)) {
            AttemptsList(modifier = Modifier.weight(1f), gameStateFlow = game.gameState)
            Column {
                Timer(game.remainingGameTimeInSeconds)
                GameRunControls(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    game = game,
                    onQuitGame = onQuitGame,
                    onGameFinished = { showEndGameDialog = true },
                )
                ColorsShowcase(
                    modifier = Modifier.align(alignment = Alignment.End).padding(top = 12.dp, end = 24.dp)
                )
            }
        }
        InputRow(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally).padding(bottom = 8.dp),
            game = game
        )

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
    val remainingTime = timeFlow.collectAsState().value.toDuration(DurationUnit.SECONDS)

    GameTimerText(
        modifier = Modifier.padding(top = 16.dp, end = 16.dp, bottom = 8.dp),
        fontSize = 45.sp,
        remainingMillis = if (remainingTime < 1.days) {
            remainingTime.inWholeMilliseconds
        } else {
            (99.minutes + 59.seconds).inWholeMilliseconds
        }
    )
}

@Composable
fun GameRunControls(
    modifier: Modifier = Modifier,
    game: Game,
    onQuitGame: () -> Unit,
    onGameFinished: () -> Unit,
) {
    val firstFocusRequester = FocusRequester()
    val gameState = game.gameState.collectAsState().value
    val buttonModifier = Modifier
        .size(height = 50.dp, width = 140.dp)
        .padding(top = 8.dp)
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
                    modifier = buttonModifier.focusRequester(firstFocusRequester)
                )
                FocusableGameButton(
                    onClick = {
                        game.quit()
                        onQuitGame()
                    },
                    text = "Quit Game",
                    modifier = buttonModifier
                )
            }

            is GameState.Paused -> {
                FocusableGameButton(
                    onClick = { game.startOrResume() },
                    text = "Resume Game",
                    modifier = buttonModifier.focusRequester(firstFocusRequester)
                )
                FocusableGameButton(
                    onClick = {
                        game.quit()
                        onQuitGame()
                    },
                    text = "Quit Game",
                    modifier = buttonModifier
                )
            }

            is GameState.Finished -> {
                FocusableGameButton(
                    onClick = {
                        game.quit()
                        onQuitGame()
                    },
                    text = "Quit Game",
                    modifier = buttonModifier.focusRequester(firstFocusRequester)
                )
            }
        }
    }

    LaunchedEffect(gameState::class.simpleName) {
        firstFocusRequester.requestFocus()
        if (gameState is GameState.Finished) {
            onGameFinished()
        }
    }
}