package code

import code.models.Code
import code.models.CodeDigit

internal interface CodeValidator {
    fun validate(code: Code) : Result

    sealed class Result {
        object Correct: Result()
        data class Wrong(val rightDigits: Int, val rightPositions: Int): Result()
    }
}

internal class CodeValidatorImpl(private val correctCode: Code): CodeValidator  {

    override fun validate(code: Code): CodeValidator.Result {
        if (code == correctCode) return CodeValidator.Result.Correct

        var correctPositionsCount = 0
        val correctDigits = mutableSetOf<CodeDigit>()

        code.digits.forEachIndexed { index, digit ->
            if (digit == correctCode.digits[index]) {
                correctPositionsCount++
            }
            if (correctCode.digits.contains(digit)) {
                correctDigits.add(digit)
            }
        }
        return CodeValidator.Result.Wrong(rightPositions = correctPositionsCount, rightDigits = correctDigits.size)
    }

}