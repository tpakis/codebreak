package aithanasakis.com.desktop.ui.model

import androidx.compose.ui.graphics.Color

enum class CodeDigitUiModel(val keyValue: Int, val color: Color) {
    Transparent(keyValue = -1, color = Color.LightGray),
    White(keyValue = 1, color = Color.White),
    Black(keyValue = 2, color = Color.Black),
    Blue(keyValue = 3, color = Color.Blue),
    Aqua(keyValue = 4, color = Color.Cyan),
    Pink(keyValue = 5, color = Color(0xFFFFC0CB)),
    Red(keyValue = 6, color = Color.Red),
    Orange(keyValue = 7, color = Color(0xFFFFA500)),
    Yellow(keyValue = 8, color = Color.Yellow);
}

