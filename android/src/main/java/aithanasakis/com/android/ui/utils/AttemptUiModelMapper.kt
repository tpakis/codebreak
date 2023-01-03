package aithanasakis.com.android.ui.utils

import aithanasakis.com.android.ui.model.AttemptUiModel
import aithanasakis.com.android.ui.utils.CodeDigitUiMapper.toUiModel
import androidx.compose.ui.graphics.Color
import models.BreakAttempt

object AttemptUiModelMapper {
    fun BreakAttempt.toUiModel(index: Int): AttemptUiModel {
        val badgeColors = mutableListOf<Color>().apply {
            repeat(rightDigitInPosition) {
                add(Color.Black)
            }
            repeat(rightDigitOffPosition) {
                add(Color.White)
            }
        }
        val codeColors = code.digits.map { it.toUiModel().color }

        return AttemptUiModel(index = index,  codeColors = codeColors, badgesColors = badgeColors)
    }
}