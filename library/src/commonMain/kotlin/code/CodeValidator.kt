package code

import code.models.Code

interface CodeValidator {
    fun validate(code: Code) : Result

    sealed class Result {
        object Correct: Result()
        data class Wrong(val rightDigits: Int, val rightPositions: Int): Result()
    }
}

class CodeValidatorImpl(private val correctCode: Code): CodeValidator  {
    override fun validate(code: Code): CodeValidator.Result {
        if (code == correctCode) return CodeValidator.Result.Correct

        return CodeValidator.Result.Wrong(0, 0)
    }
}