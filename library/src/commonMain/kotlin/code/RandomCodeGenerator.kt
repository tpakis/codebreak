package code

import code.models.Code
import code.models.CodeDigit
import kotlin.random.Random
import kotlin.random.nextInt

object RandomCodeGenerator {
    fun generateRandomCode(): Code {
        val returnList = (1..5).map { randomDigit() }

        return Code(returnList)
    }

    private fun randomDigit(): CodeDigit {
        val index = Random.nextInt(IntRange(0, CodeDigit.values().size - 1))
        return CodeDigit.values()[index]
    }
}