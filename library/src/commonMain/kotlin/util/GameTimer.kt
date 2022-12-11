package util

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

internal class GameTimer(duration: Duration, dispatcher: CoroutineDispatcher = Dispatchers.Default) {

    private val scope = CoroutineScope(dispatcher)
    private var countDownJbb: Job? = null
    private var actionOnTimeEnd: (() -> Unit)? = null

    val remainingGameTimeInSeconds =
        MutableStateFlow(duration.inWholeSeconds)

    fun start(actionOnTimeEnd: ()-> Unit) {
        this.actionOnTimeEnd = actionOnTimeEnd
        countDownJbb = scope.launch {
            while (remainingGameTimeInSeconds.value > 0L) {
                delay(1.seconds)
                nextSecondPassed()
            }
        }
    }

    private fun nextSecondPassed() {
        remainingGameTimeInSeconds.value = remainingGameTimeInSeconds.value - 1
        if (remainingGameTimeInSeconds.value == 0L) {
            actionOnTimeEnd?.invoke()
        }
    }

    fun stop() {
        countDownJbb?.cancel()
        countDownJbb = null
        actionOnTimeEnd = null
    }
}