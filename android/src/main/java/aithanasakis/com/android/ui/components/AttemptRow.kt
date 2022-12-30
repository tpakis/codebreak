package aithanasakis.com.android.ui.components

import aithanasakis.com.android.ui.model.AttemptUiModel
import aithanasakis.com.android.ui.theme.BreakCodeTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AttemptRow(modifier: Modifier = Modifier, width: Dp, borderColor: Color = Color.Transparent, attemptUiModel: AttemptUiModel) {
    Row(modifier.requiredWidth(width), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        with(attemptUiModel) {
            val height = width.value / (2 * codeColors.size + 1)
            Text(modifier = Modifier.size(Dp(height)).align(Alignment.Bottom), text = "#$index ", fontSize = (height/1.8).sp )
            codeColors.forEach {
                ColouredCircle(
                    modifier = Modifier.padding(4.dp),
                    diameter = Dp(height),
                    borderColor = borderColor,
                    fillColor = it
                )
            }
            Spacer(Modifier.size(Dp(height/2)))
            badgesColors.forEach {
                ColouredCircle(modifier = Modifier.padding(1.dp), diameter = Dp(height / 2), borderColor = borderColor, fillColor = it)
            }
            repeat(times = codeColors.size - badgesColors.size) {
                ColouredCircle(modifier = Modifier.padding(1.dp), diameter = Dp(height / 2), borderColor = Color.White, fillColor = Color.Transparent)
            }
        }
    }
}

@Preview
@Composable
fun PreviewAttempt() {
    BreakCodeTheme {
        AttemptRow(
            width = 400.dp,
            attemptUiModel = AttemptUiModel(
                index = 15,
                codeColors = listOf(
                    Color.Blue,
                    Color.Green,
                    Color.Red,
                    Color.White,
                    Color.Magenta
                ), badgesColors = listOf(Color.Black, Color.Black, Color.White)
            )
        )
    }
}