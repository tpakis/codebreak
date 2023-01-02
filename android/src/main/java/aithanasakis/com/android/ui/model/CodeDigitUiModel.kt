package aithanasakis.com.android.ui.model

import androidx.compose.ui.graphics.Color

enum class CodeDigitUiModel(private val id: Int, val color: Color) {
    LightGray(id = -1, color = Color.LightGray),
    White(id = 0, color = Color.White),
    Black(id = 1, color = Color.Black),
    Blue(id = 2, color = Color.Blue),
    Aqua(id = 3, color = Color.Cyan),
    Pink(id = 4, color = Color(0xFFFFC0CB)),
    Red(id = 5, color = Color.Red),
    Orange(id = 6, color = Color(0xFFFFA500)),
    Yellow(id = 7, color = Color.Yellow);

    companion object {
        fun getById(id: Int): CodeDigitUiModel {
            return CodeDigitUiModel.values().firstOrNull { it.id == id } ?: throw IllegalArgumentException("id $id not found!")
        }
    }
}

