package aithanasakis.com.android.ui.components

import aithanasakis.com.android.translations.LocalTextProvider
import aithanasakis.com.android.ui.theme.BreakCodeTheme
import aithanasakis.com.android.ui.utils.CodeDigitUiMapper.toUiModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import code.RandomCodeGenerator
import code.models.Code
import models.GameResult

@Composable
fun GameResultDialog(modifier: Modifier = Modifier, gameResult: GameResult, correctCode: Code, onDismiss: () -> Unit) {
    when (gameResult) {
        GameResult.WON -> WinDialog(modifier, onDismiss)
        GameResult.LOST -> LostDialog(modifier, correctCode, onDismiss)
    }
}

@Composable
fun WinDialog(modifier: Modifier = Modifier, onDismiss: () -> Unit = {}) {
    val focusRequester = FocusRequester()
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = modifier.defaultMinSize(minWidth = 300.dp).padding(8.dp),
            elevation = 8.dp
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = LocalTextProvider.current.won, modifier = Modifier.padding(16.dp), fontSize = 24.sp)
                Icon(Icons.Rounded.CheckCircle, modifier = Modifier.size(100.dp), contentDescription = "Won", tint = Color.Green)
                FocusableGameButton(text = "OK", modifier = Modifier.padding(16.dp), onClick = onDismiss, focusRequester = focusRequester)
            }
        }
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
fun LostDialog(modifier: Modifier = Modifier, correctCode: Code, onDismiss: () -> Unit = {}) {
    val focusRequester = FocusRequester()
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = modifier.padding(8.dp),
            elevation = 8.dp
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = LocalTextProvider.current.lost, modifier = Modifier.padding(16.dp), fontSize = 24.sp)
                Row(modifier = Modifier.padding(bottom = 16.dp)) {
                    correctCode.digits.forEach { digit ->
                        ColouredCircle(
                            modifier = Modifier.padding(4.dp),
                            diameter = 40.dp,
                            borderColor = Color.Black,
                            fillColor = digit.toUiModel().color
                        )
                    }
                }
                Icon(Icons.Rounded.Cancel, modifier = Modifier.size(100.dp), contentDescription = "Lost", tint = Color.Red)

                FocusableGameButton(text = "OK", modifier = Modifier.padding(16.dp), onClick = onDismiss, focusRequester = focusRequester)
            }
        }
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Preview
@Composable
fun WinDialogPreview() {
    BreakCodeTheme {
        WinDialog()
    }
}

@Preview
@Composable
fun LostDialogPreview() {
    BreakCodeTheme {
        LostDialog(correctCode = RandomCodeGenerator.generateRandomCode(false))
    }
}