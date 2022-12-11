package util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
class GameTimerTest {
    private val dispatcher: CoroutineDispatcher = StandardTestDispatcher()
    private lateinit var classToTest: GameTimer

    @Test
    fun start_should_call_actionOnTimeEnd_when_time_is_up() = runTest(dispatcher) {
        var actionCalled = false
        val actionOnTimeEnd = { actionCalled = true}
        classToTest = GameTimer(2.seconds, dispatcher)

        classToTest.start(actionOnTimeEnd)
        advanceTimeBy(2.seconds.inWholeMilliseconds + 1)

        assertTrue { actionCalled }
    }

    @Test
    fun start_should_not_call_actionOnTimeEnd_before_time_is_up() = runTest(dispatcher) {
        var actionCalled = false
        val actionOnTimeEnd = { actionCalled = true}
        classToTest = GameTimer(2.seconds, dispatcher)

        classToTest.start(actionOnTimeEnd)
        advanceTimeBy(2.seconds.inWholeMilliseconds - 1)

        assertFalse { actionCalled }
    }

    @Test
    fun stop_should_stop_the_timer() = runTest(dispatcher) {
        var actionCalled = false
        val actionOnTimeEnd = { actionCalled = true}
        classToTest = GameTimer(2.seconds, dispatcher)
        classToTest.start(actionOnTimeEnd)
        advanceTimeBy(1.seconds.inWholeMilliseconds)

        classToTest.stop()
        advanceTimeBy(2.seconds.inWholeMilliseconds)

        assertFalse { actionCalled }
    }
}