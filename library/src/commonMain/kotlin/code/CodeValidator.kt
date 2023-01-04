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

        val codeDigitsInCorrectPositionsIndexes = mutableListOf<Int>()
        val codeDigitsInWrongPositions = mutableListOf<CodeDigit>()


        code.digits.forEachIndexed { index, codeDigit ->
            if (codeDigit == correctCode.digits[index]) {
                codeDigitsInCorrectPositionsIndexes.add(index)
            } else {
                codeDigitsInWrongPositions.add(codeDigit)
            }
        }

        val correctCodeDigitsWithoutCorrectPosition =
            correctCode.digits.filterIndexed { index, _ -> !codeDigitsInCorrectPositionsIndexes.contains(index) }

        var correctGuessesInWrongPositionsCount = 0
        codeDigitsInWrongPositions.toSet().forEach { digit ->
            if (correctCodeDigitsWithoutCorrectPosition.contains(digit)) {
                correctGuessesInWrongPositionsCount++
            }
        }

        return CodeValidator.Result.Wrong(
            rightDigitInPosition = codeDigitsInCorrectPositionsIndexes.size,
            rightDigitOffPosition = correctGuessesInWrongPositionsCount
        )
    }

}