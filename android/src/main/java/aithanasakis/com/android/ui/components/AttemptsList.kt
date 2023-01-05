package aithanasakis.com.android.ui.components

import aithanasakis.com.android.translations.LocalTextProvider
import aithanasakis.com.android.ui.utils.AttemptUiModelMapper.toUiModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.StateFlow
import models.GameState
import kotlin.math.ceil

@Composable
fun AttemptsList(modifier: Modifier = Modifier, gameStateFlow: StateFlow<GameState>, maxAttemptsToDisplay: Int = 16) {
    val halfIndex = ceil(maxAttemptsToDisplay / 2f).toInt()
    val breakAttemptsLists = gameStateFlow.collectAsState()
        .value.breakAttempts
        .takeLast(maxAttemptsToDisplay)
        .chunked(halfIndex)


    Column(modifier = modifier.padding(16.dp)) {
        Text(modifier = Modifier.padding(bottom = 16.dp), text = LocalTextProvider.current.attempts, fontSize = 20.sp)
        Row(modifier = modifier.padding(start = 16.dp, end = 16.dp)) {
            breakAttemptsLists.forEachIndexed { listIndex, attempts ->
                val baseIndex = listIndex * halfIndex + 1
                Column(modifier = Modifier.padding(end = 16.dp)) {
                    attempts.forEachIndexed { subIndex, attempt ->
                        AttemptRow(width = 300.dp, attemptUiModel = attempt.toUiModel(baseIndex + subIndex))
                    }
                }
            }
        }
    }

}