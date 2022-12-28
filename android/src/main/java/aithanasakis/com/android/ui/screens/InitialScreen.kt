package aithanasakis.com.android.ui.screens

import aithanasakis.com.android.ui.GameViewModel
import aithanasakis.com.android.ui.components.FocusableGameButton
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import models.GameParameters

@Composable
fun InitialScreen(viewModel: GameViewModel) {
    val focusRequester = FocusRequester()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Code Breaker!")
        Spacer(modifier = Modifier.size(16.dp))
        FocusableGameButton(
            text = "Easy",
            onClick = { viewModel.createNewGame(GameParameters.easy) },
            modifier = Modifier.padding(4.dp).focusRequester(focusRequester)
        )
        FocusableGameButton(
            text = "Normal",
            onClick = { viewModel.createNewGame(GameParameters.normal) },
            modifier = Modifier.padding(4.dp)
        )
        FocusableGameButton(
            text = "Hard",
            onClick = { viewModel.createNewGame(GameParameters.hard) },
            modifier = Modifier.padding(4.dp)
        )
        FocusableGameButton(
            text = "Practice",
            onClick = { viewModel.createNewGame(GameParameters.practice) },
            modifier = Modifier.padding(4.dp)
        )
    }
    LaunchedEffect("key") {
        focusRequester.requestFocus()
    }
}