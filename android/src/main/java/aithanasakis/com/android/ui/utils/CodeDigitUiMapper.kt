package aithanasakis.com.android.ui.utils

import aithanasakis.com.android.ui.model.CodeDigitUiModel
import code.models.CodeDigit

object CodeDigitUiMapper {
    fun CodeDigit?.toUiModel(): CodeDigitUiModel {
        val codeDigit = this ?: return CodeDigitUiModel.LightGray

        return CodeDigitUiModel.values().firstOrNull { it.name == codeDigit.name } ?: CodeDigitUiModel.LightGray
    }

}