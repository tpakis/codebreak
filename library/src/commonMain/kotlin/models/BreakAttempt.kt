package models

import code.models.Code

data class BreakAttempt(val code: Code, val rightDigitOffPosition: Int, val rightDigitInPosition: Int) {
    val isCorrect get() = rightDigitInPosition == code.digits.size
}