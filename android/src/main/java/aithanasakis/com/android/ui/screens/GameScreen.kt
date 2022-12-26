package aithanasakis.com.android.ui.screens


import Game
import aithanasakis.com.android.ui.components.GameTimerText
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import models.GameParameters

@Composable
fun GameScreen(game: Game) {
    val remainingTime = game.remainingGameTimeInSeconds.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.align(Alignment.TopEnd).height(400.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            GameTimerText(
                modifier = Modifier.padding(top= 16.dp, end = 16.dp, bottom = 8.dp),
                fontSize = 45.sp,
                remainingMillis = remainingTime.times(1000)
            )
            Button(onClick = { game.startOrResume() }) {
                Text("Start Game")
            }
        }
    }

}
