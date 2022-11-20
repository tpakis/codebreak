import models.Code
import models.CodeDigit
import kotlin.random.Random
import kotlin.random.nextInt

object RandomCodeGenerator {
    fun generateRandomCode(): Code {
        val returnList = mutableListOf<CodeDigit>().apply {
            repeat(times = 5) { add(randomDigit()) }
        }

        return Code(returnList)
    }

    private fun randomDigit(): CodeDigit {
        val index = Random.nextInt(IntRange(0, CodeDigit.values().size))
        return CodeDigit.values()[index]
    }
}