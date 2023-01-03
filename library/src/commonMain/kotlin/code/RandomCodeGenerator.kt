package code

import code.models.Code
import code.models.CodeDigit

object RandomCodeGenerator {
    fun generateRandomCode(duplicatesAllowed: Boolean = true): Code {
        val returnList = if (duplicatesAllowed) {
            (1..5).map { CodeDigit.values().random() }
        } else {
            CodeDigit.values().apply { shuffle() }.take(5)
        }

        return Code(returnList)
    }
}