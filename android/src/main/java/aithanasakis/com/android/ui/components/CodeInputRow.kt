package aithanasakis.com.android.ui.components

import aithanasakis.com.android.ui.model.AttemptUiModel
import aithanasakis.com.android.ui.theme.BreakCodeTheme
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CodeInputRow(modifier: Modifier = Modifier, width: Dp, borderColor: Color = Color.Transparent, onCheckClick: () -> Unit) {
    Row(
        modifier.width(width),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val height = width.value / 9

        repeat(5) {
            ColouredCircle(
                modifier = Modifier.padding(4.dp),
                diameter = Dp(height),
                borderColor = Color.Black,
                fillColor = Color.LightGray
            )
        }
        Spacer(Modifier.size(Dp(height / 2)))
        Button(modifier = Modifier.height(Dp(height/1.5f)), onClick = onCheckClick) {
            Text("Check Code", style = TextStyle(fontSize = 10.sp))
        }


    }
}

@Preview
@Composable
fun PreviewCodeInputRow() {
    BreakCodeTheme {
        CodeInputRow(width = 400.dp, onCheckClick = { Log.d("Button", "check click") })
    }
}