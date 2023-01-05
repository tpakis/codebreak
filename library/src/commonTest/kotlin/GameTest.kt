import code.CodeValidator
import code.RandomCodeGenerator
import fakes.FakeCodeValidator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import models.GameParameters
import models.GameResult
import models.GameState
import util.GameTimer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class GameTest {

    private val codeValidator = FakeCodeValidator()
    private val parameters: GameParameters = GameParameters.easy
    private val dispatcher: CoroutineDispatcher = StandardTestDispatcher()

    private val gameTimer: GameTimer = GameTimer(parameters.gameDuration, dispatcher)

    private val classToTest = Game(codeValidator, parameters, gameTimer)

    @Test
    fun startOrResume_should_start_the_game_when_it_is_not_started() {
        classToTest.startOrResume()

        assertTrue { classToTest.gameState.value is GameState.Running }
    }

    @Test
    fun pause_should_pause_the_game_when_it_is_running() {
        classToTest.startOrResume()

        classToTest.pause()

        assertTrue { classToTest.gameState.value is GameState.Paused }
    }

    @Test
    fun pause_should_not_pause_the_game_when_it_is_not_started() {
        classToTest.pause()

        assertTrue { classToTest.gameState.value is GameState.NotStarted }
    }

    @Test
    fun pause_should_not_pause_the_game_when_it_is_finished() = runTest(dispatcher) {
        classToTest.startOrResume()
        advanceUntilIdle()

        classToTest.pause()

        assertTrue { classToTest.gameState.value is GameState.Finished }
    }

    @Test
    fun game_should_finish_as_lost_when_time_runs_out_without_solution() = runTest(dispatcher) {
        classToTest.startOrResume()

        advanceTimeBy(parameters.gameDuration.inWholeMilliseconds + 1)

        assertTrue { classToTest.gameState.value is GameState.Finished }
        assertEquals(expected = GameResult.LOST, actual = (classToTest.gameState.value as GameState.Finished).result )
    }

    @Test
    fun game_should_finish_as_won_when_the_correct_code_is_entered_in_tryNewCode() = runTest(dispatcher) {
        classToTest.startOrResume()
        codeValidator.expectedResult = CodeValidator.Result.Correct

        classToTest.tryNewCode(parameters.codeToBreak)

        assertTrue { classToTest.gameState.value is GameState.Finished }
        assertEquals(expected = GameResult.WON, actual = (classToTest.gameState.value as GameState.Finished).result )
    }

    @Test
    fun gameState_should_contain_the_attempt_when_tryNewCode_is_wrong() = runTest(dispatcher) {
        classToTest.startOrResume()
        codeValidator.expectedResult = CodeValidator.Result.Wrong(rightDigitInPosition = 1, rightDigitOffPosition = 2)

        classToTest.tryNewCode(RandomCodeGenerator.generateRandomCode())

        assertTrue { classToTest.gameState.value is GameState.Running }
        assertEquals(expected = 1, actual = classToTest.gameState.value.breakAttempts.first().rightDigitInPosition )
        assertEquals(expected = 2, actual = classToTest.gameState.value.breakAttempts.first().rightDigitOffPosition )
    }

    @Test
    fun game_should_finish_as_lost_when_the_max_amount_of_attempts_is_reached_without_solution() = runTest(dispatcher) {
        classToTest.startOrResume()
        codeValidator.expectedResult = CodeValidator.Result.Wrong(rightDigitInPosition = 1, rightDigitOffPosition = 2)
        val randomCode = RandomCodeGenerator.generateRandomCode()

        repeat(parameters.maxAttempts) {
            classToTest.tryNewCode(randomCode)
        }

        assertTrue { classToTest.gameState.value is GameState.Finished }
        assertEquals(expected = GameResult.LOST, actual = (classToTest.gameState.value as GameState.Finished).result )
        assertEquals(expected = parameters.maxAttempts, actual = classToTest.gameState.value.breakAttempts.size)
    }

    @Test
    fun tryNewCode_should_ignore_codes_when_the_game_is_not_started() = runTest(dispatcher) {
        codeValidator.expectedResult = CodeValidator.Result.Correct

        classToTest.tryNewCode(parameters.codeToBreak)

        assertTrue { classToTest.gameState.value is GameState.NotStarted }
    }

    @Test
    fun tryNewCode_should_ignore_codes_when_the_game_is_paused() = runTest(dispatcher) {
        classToTest.startOrResume()
        codeValidator.expectedResult = CodeValidator.Result.Correct
        classToTest.pause()

        classToTest.tryNewCode(parameters.codeToBreak)

        assertTrue { classToTest.gameState.value is GameState.Paused }
    }

    @Test
    fun tryNewCode_should_ignore_codes_when_the_game_is_finished() = runTest(dispatcher) {
        classToTest.startOrResume()
        advanceTimeBy(parameters.gameDuration.inWholeMilliseconds + 1)
        codeValidator.expectedResult = CodeValidator.Result.Correct

        classToTest.tryNewCode(parameters.codeToBreak)

        assertTrue { classToTest.gameState.value is GameState.Finished }
        assertEquals(expected = GameResult.LOST, actual = (classToTest.gameState.value as GameState.Finished).result )
    }

    @Test
    fun quit_should_finish_the_game_as_lost() = runTest(dispatcher) {
        classToTest.startOrResume()

        classToTest.quit()

        assertTrue { classToTest.gameState.value is GameState.Finished }
        assertEquals(expected = GameResult.LOST, actual = (classToTest.gameState.value as GameState.Finished).result )
    }

    @Test
    fun quit_should_not_quit_the_game_when_its_already_finished() = runTest(dispatcher) {
        classToTest.startOrResume()
        codeValidator.expectedResult = CodeValidator.Result.Correct
        classToTest.tryNewCode(parameters.codeToBreak)

        classToTest.quit()

        assertTrue { classToTest.gameState.value is GameState.Finished }
        assertEquals(expected = GameResult.WON, actual = (classToTest.gameState.value as GameState.Finished).result )
    }

    @Test
    fun getCodeToBreak_should_return_the_correct_code() {
        val correctCode = classToTest.getCodeToBreak()

        assertEquals(expected = parameters.codeToBreak, correctCode)
    }
}