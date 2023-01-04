package aithanasakis.com.desktop.ui.components

import aithanasakis.com.desktop.ui.model.CodeDigitUiModel
import aithanasakis.com.desktop.ui.theme.BreakCodeTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class ColorsShowcaseArrangement {
    Horizontal, Vertical
}

@Composable
fun ColorsShowcase(
    modifier: Modifier = Modifier,
    arrangement: ColorsShowcaseArrangement = ColorsShowcaseArrangement.Vertical,
) {
    when (arrangement) {
        ColorsShowcaseArrangement.Vertical -> {
            Column(modifier = modifier) {
                ColorsListWithKeyLabel()
            }
        }

        ColorsShowcaseArrangement.Horizontal -> {
            Row(modifier = modifier) {
                ColorsListWithKeyLabel()
            }
        }
    }
}

@Composable
private fun ColorsListWithKeyLabel(modifier: Modifier = Modifier) {
    CodeDigitUiModel.values().filter { it.keyValue > 0 }.forEach { uiModel ->
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            Text("${uiModel.keyValue}", fontSize = 16.sp)
            ColouredCircle(
                modifier = Modifier.padding(4.dp),
                diameter = 25.dp,
                borderColor = Color.Black,
                fillColor = uiModel.color
            )
        }
    }
}

@Preview
@Composable
fun ColorsShowcaseVerticalPreview() {
    BreakCodeTheme {
        Column {
            ColorsShowcase(arrangement = ColorsShowcaseArrangement.Vertical)
            Spacer(modifier = Modifier.size(16.dp))
            ColorsShowcase(arrangement = ColorsShowcaseArrangement.Horizontal)
        }
    }
}