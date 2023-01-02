package aithanasakis.com.android.ui

import aithanasakis.com.android.ui.model.CodeDigitUiModel
import aithanasakis.com.android.ui.utils.CodeDigitUiMapper.toUiModel
import android.util.Log
import code.models.Code
import code.models.CodeDigit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object CodeInputHandler {
    private var focusedIndex = NO_FOCUS
    private val codeInput = mutableListOf<CodeDigit?>(null, null, null, null, null)

    private val _codeInputUiState = MutableStateFlow(codeInput.map { it.toUiModel() })
    val codeInputUiState: StateFlow<List<CodeDigitUiModel>> get() = _codeInputUiState.asStateFlow()

    fun codeDigitFocused(index: Int) {
        focusedIndex = index
    }

    fun newCodeDigitInput(codeDigit: CodeDigit) {
        if (focusedIndex > NO_FOCUS) {
            codeInput[focusedIndex] = codeDigit
            _codeInputUiState.value = codeInput.map { it.toUiModel() }
        }
    }

    fun clearDigitFocus() {
        focusedIndex = NO_FOCUS
    }

    fun getCodeInput(): Code? {
        return try {
            Code(codeInput.filterNotNull())
        } catch (e: IllegalArgumentException) {
            null.also { Log.e("CodeInputHandler", e.message ?: "") }
        }
    }
}

internal const val NO_FOCUS = -1