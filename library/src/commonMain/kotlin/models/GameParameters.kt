package models

import CodeGenerator
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

data class GameParameters(val gameDuration: Duration, val maxAttempts: Int, val codeToBreak: Code = CodeGenerator.generateCode()) {
    companion object {
        val practice = GameParameters(gameDuration = Duration.INFINITE, 30)
        val easy = GameParameters(gameDuration = 10.minutes, 15)
        val normal = GameParameters(gameDuration = 6.minutes, 12)
        val hard = GameParameters(gameDuration = 2.minutes, 9)
    }
}

