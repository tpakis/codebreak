import code.CodeValidator
import code.CodeValidatorImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import models.GameParameters


class Game private constructor(
    private val codeValidator: CodeValidator,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val parameters: GameParameters,
) {

    companion object {
        fun createNew(parameters: GameParameters): Game =
            Game(codeValidator = CodeValidatorImpl(parameters.codeToBreak), parameters = parameters)
    }
}