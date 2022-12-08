package models

sealed class GameState(open val breakAttempts: List<BreakAttempt>) {
    object NotStarted: GameState(emptyList())
    class Running(override val breakAttempts: List<BreakAttempt>): GameState(breakAttempts)
    class Paused(override val breakAttempts: List<BreakAttempt>): GameState(breakAttempts)
    data class Finished(val result: GameResult, override val breakAttempts: List<BreakAttempt>): GameState(breakAttempts)
}

enum class GameResult {
    WON, LOST
}
