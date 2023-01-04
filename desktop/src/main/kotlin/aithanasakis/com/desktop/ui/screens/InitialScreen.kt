package aithanasakis.com.desktop.ui.screens

import aithanasakis.com.desktop.ui.GameViewModel
import aithanasakis.com.desktop.ui.components.FocusableGameButton
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import models.GameParameters

@Composable
fun InitialScreen(viewModel: GameViewModel) {
    val focusRequester = FocusRequester()
    val buttonModifier = Modifier
        .size(height = 64.dp, width = 150.dp)
        .padding(8.dp)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Code Breaker!", fontSize = 36.sp)
        Spacer(modifier = Modifier.size(16.dp))
        FocusableGameButton(
            text = "Easy",
            onClick = { viewModel.createNewGame(GameParameters.easy) },
            modifier = buttonModifier.focusRequester(focusRequester)
        )
        FocusableGameButton(
            text = "Normal",
            onClick = { viewModel.createNewGame(GameParameters.normal) },
            modifier = buttonModifier
        )
        FocusableGameButton(
            text = "Hard",
            onClick = { viewModel.createNewGame(GameParameters.hard) },
            modifier = buttonModifier
        )
        FocusableGameButton(
            text = "Practice",
            onClick = { viewModel.createNewGame(GameParameters.practice) },
            modifier = buttonModifier
        )
    }
    LaunchedEffect("key") {
        focusRequester.requestFocus()
    }
}