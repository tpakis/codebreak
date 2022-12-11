import code.CodeValidator
import code.CodeValidatorImpl
import code.models.Code
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import models.BreakAttempt
import models.GameParameters
import models.GameResult
import models.GameState
import util.GameTimer
import kotlin.time.Duration.Companion.INFINITE
import kotlin.time.Duration.Companion.seconds

class Game internal constructor(
    private val codeValidator: CodeValidator,
    private val parameters: GameParameters,
    private val gameTimer: GameTimer,
) {
    private val isGameRunning: Boolean get() = _gameState.value is GameState.Running
    private val isGameFinished: Boolean get() = _gameState.value is GameState.Finished
    private val canStartGame: Boolean get() = _gameState.value is GameState.NotStarted || _gameState.value is GameState.Paused
    private val isGameTimeConstrained = parameters.gameDuration != INFINITE

    private val _gameState = MutableStateFlow<GameState>(GameState.NotStarted)
    val gameState: StateFlow<GameState> get() = _gameState.asStateFlow()

    val remainingGameTimeInSeconds: StateFlow<Long> get() = gameTimer.remainingGameTimeInSeconds.asStateFlow()

    fun startOrResume() {
        if (canStartGame) {
            _gameState.value = GameState.Running(_gameState.value.breakAttempts)
            startTimer()
        }
    }

    private fun startTimer() {
        if (isGameTimeConstrained) {
            gameTimer.start { lostGame() }
        }
    }

    fun tryNewCode(code: Code) {
        if (isGameRunning) {
            val validationResult = codeValidator.validate(code)
            val newBreakAttempt = code.toBreakAttempt(validationResult)

            updateGameStateWith(newBreakAttempt)
            checkToStopTimer()
        }
    }

    private fun updateGameStateWith(newBreakAttempt: BreakAttempt) {
        val newBreakAttemptsList = _gameState.value.breakAttempts.plusElement(newBreakAttempt)

        _gameState.value = when {
            newBreakAttempt.isCorrect -> GameState.Finished(GameResult.WON, newBreakAttemptsList)
            newBreakAttemptsList.size == parameters.maxAttempts -> GameState.Finished(
                GameResult.LOST,
                newBreakAttemptsList
            )

            else -> GameState.Running(newBreakAttemptsList)
        }
    }

    private fun checkToStopTimer() {
        if (isGameFinished) {
            stopTimer()
        }
    }

    fun pause() {
        if (isGameRunning) {
            _gameState.value = GameState.Paused(_gameState.value.breakAttempts)
            stopTimer()
        }
    }

    fun quit() {
        if (isGameFinished) return
        lostGame()
    }

    private fun lostGame() {
        _gameState.value = GameState.Finished(GameResult.LOST, _gameState.value.breakAttempts)
        stopTimer()
    }

    private fun stopTimer() {
        gameTimer.stop()
    }

    companion object {
        const val NO_TIME_CONSTRAINT = -1L

        fun createNew(parameters: GameParameters): Game =
            Game(
                codeValidator = CodeValidatorImpl(parameters.codeToBreak),
                parameters = parameters,
                gameTimer = GameTimer(parameters.gameDuration)
            )
    }
}

private fun Code.toBreakAttempt(validationResult: CodeValidator.Result): BreakAttempt = when (validationResult) {
    is CodeValidator.Result.Correct -> BreakAttempt(
        code = this,
        rightDigits = digits.size,
        rightPositions = digits.size
    )

    is CodeValidator.Result.Wrong -> BreakAttempt(
        code = this,
        rightDigits = validationResult.rightDigits,
        rightPositions = validationResult.rightPositions
    )
}