package code

import code.models.Code
import code.models.CodeDigit

internal interface CodeValidator {
    fun validate(code: Code): Result

    sealed class Result {
        object Correct : Result()
        data class Wrong(val rightDigitOffPosition: Int, val rightDigitInPosition: Int) : Result()
    }
}

internal class CodeValidatorImpl(private val correctCode: Code) : CodeValidator {

    override fun validate(code: Code): CodeValidator.Result {
        if (code == correctCode) return CodeValidator.Result.Correct

        val codeDigitsInCorrectPositions = mutableListOf<CodeDigit>()
        val codeDigitsInWrongPositions = mutableListOf<CodeDigit>()
        var correctDigitsInWrongPositionsCount = 0

        code.digits.forEachIndexed { index, codeDigit ->
            if (codeDigit == correctCode.digits[index]) {
                codeDigitsInCorrectPositions.add(codeDigit)
            } else {
                codeDigitsInWrongPositions.add(codeDigit)
            }
        }

        codeDigitsInWrongPositions.toSet().forEach { digit ->
            if (correctCode.digits.contains(digit)) {
                correctDigitsInWrongPositionsCount++
            }
        }
        return CodeValidator.Result.Wrong(
            rightDigitInPosition = codeDigitsInCorrectPositions.size,
            rightDigitOffPosition = correctDigitsInWrongPositionsCount
        )
    }

}