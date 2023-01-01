package aithanasakis.com.android.ui

import code.models.CodeDigit

object CodeInputHandler {
    private var focusedIndex = NO_FOCUS

    fun codeDigitFocused(index: Int) {
        focusedIndex = index
    }

    fun newCodeDigitInput(codeDigit: CodeDigit) {

    }

    fun clearDigitFocus() {
        focusedIndex = NO_FOCUS
    }
}

internal const val NO_FOCUS = -1