package aithanasakis.com.desktop.ui.utils

import aithanasakis.com.desktop.ui.model.CodeDigitUiModel
import code.models.CodeDigit

object CodeDigitUiMapper {
    fun CodeDigit?.toUiModel(): CodeDigitUiModel {
        val codeDigit = this ?: return CodeDigitUiModel.Transparent

        return CodeDigitUiModel.values().firstOrNull { it.name == codeDigit.name } ?: CodeDigitUiModel.Transparent
    }

}