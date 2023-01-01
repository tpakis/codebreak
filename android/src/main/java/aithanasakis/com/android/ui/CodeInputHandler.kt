package aithanasakis.com.android.ui

object CodeInputHandler {
    private var focusedIndex = NO_FOCUS

    fun codeDigitFocused(index: Int) {
        focusedIndex = index
    }

    fun clearDigitFocus() {
        focusedIndex = NO_FOCUS
    }
}

internal const val NO_FOCUS = -1