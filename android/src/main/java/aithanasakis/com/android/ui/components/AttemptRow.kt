package aithanasakis.com.android.ui.components

import aithanasakis.com.android.ui.model.AttemptUiModel
import aithanasakis.com.android.ui.theme.BreakCodeTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AttemptRow(modifier: Modifier = Modifier, width: Dp, borderColor: Color = Color.Transparent, attemptUiModel: AttemptUiModel) {
    Row(modifier.width(width), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        with(attemptUiModel) {
            val height = width.value / (5 * codeColors.size)
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
            width = 1000.dp,
            attemptUiModel = AttemptUiModel(
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