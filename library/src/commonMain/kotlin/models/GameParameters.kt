package models

import code.RandomCodeGenerator
import code.models.Code
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

data class GameParameters(val gameDuration: Duration, val maxAttempts: Int, val codeToBreak: Code = RandomCodeGenerator.generateRandomCode()) {
    companion object {
        val practice = GameParameters(gameDuration = Duration.INFINITE, 30)
        val easy = GameParameters(gameDuration = 10.minutes, 15, RandomCodeGenerator.generateRandomCode(duplicatesAllowed = false))
        val normal = GameParameters(gameDuration = 6.minutes, 12)
        val hard = GameParameters(gameDuration = 2.minutes, 9)
    }
}

