package aithanasakis.com.desktop.ui.components

import aithanasakis.com.desktop.ui.theme.BreakCodeTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ColouredCircle(modifier: Modifier = Modifier, diameter: Dp = 100.dp, borderColor: Color, fillColor: Color) {
    Row(modifier.size(diameter)) {
        // Creating a Canvas to draw a Circle
        Canvas(Modifier.fillMaxSize()) {
            val canvasSize = size.minDimension // same as max height since it is a circle
            val center = Offset(x = canvasSize / 2, y = canvasSize / 2)

            drawCircle(color = borderColor, center = center, radius = canvasSize / 1.85f)
            drawCircle(color = fillColor, center = center, radius = canvasSize / 2)
        }
    }
}

@Preview
@Composable
fun PreviewCircle() {
    BreakCodeTheme {
        ColouredCircle(modifier = Modifier.padding(10.dp), diameter = 200.dp, borderColor = Color.Green, fillColor = Color.Yellow)
    }
}