package aithanasakis.com.desktop.ui.components

import aithanasakis.com.desktop.ui.CodeInputHandler
import aithanasakis.com.desktop.ui.theme.BreakCodeTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import code.models.Code

@Composable
fun CodeInputRow(
    modifier: Modifier = Modifier,
    width: Dp,
    onCheckClick: (codeToCheck: Code) -> Unit
) {
    val selectedColors = CodeInputHandler.codeInputUiState.collectAsState().value.map { it.color }

    Row(
        modifier.width(width),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val height = width.value / 9

        repeat(5) { index ->
            FocusableColourCircle(
                modifier = Modifier.padding(4.dp),
                diameter = Dp(height),
                borderColor = Color.Black,
                fillColor = selectedColors[index],
                onFocusChange = { isFocused ->
                    if (isFocused) {
                        CodeInputHandler.codeDigitFocused(index)
                    } else {
                        CodeInputHandler.clearDigitFocus()
                    }
                },
                onClick = CodeInputHandler::setNextDigitForFocused
            )
        }
        Spacer(Modifier.size(Dp(height / 2)))

        FocusableGameButton(
            modifier = Modifier.height(Dp(height / 1.5f)),
            enabled = CodeInputHandler.getCodeInput() != null,
            onClick = { CodeInputHandler.getCodeInput()?.let(onCheckClick) },
            text = "Check Code",
            fontSize = 10.sp,
        )
    }
}

@Preview
@Composable
fun PreviewCodeInputRow() {
    BreakCodeTheme {
        CodeInputRow(width = 400.dp, onCheckClick = { })
    }
}