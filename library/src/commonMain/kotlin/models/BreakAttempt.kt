package models

import code.models.Code

data class BreakAttempt(val code: Code, val rightDigits: Int, val rightPositions: Int) {
    val isCorrect get() = rightDigits == code.digits.size && rightPositions == code.digits.size
}