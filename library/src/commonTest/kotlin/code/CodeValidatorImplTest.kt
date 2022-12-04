package code

import code.models.Code
import code.models.CodeDigit
import code.models.CodeDigit.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CodeValidatorImplTest {

    private val correctCode = Code(listOf(White, White, Yellow, Black, Red))
    private lateinit var classToTest: CodeValidator

    @Test
    fun validate_should_return_Correct_when_the_code_is_correct() {
        classToTest = CodeValidatorImpl(correctCode)

        val result = classToTest.validate(correctCode)

        assertTrue { result is CodeValidator.Result.Correct }
    }

    @Test
    fun validate_should_return_Wrong_when_the_code_is_wrong() {
        classToTest = CodeValidatorImpl(correctCode)
        val wrongDigit = CodeDigit.values().first { !correctCode.digits.contains(it) }

        val result = classToTest.validate(Code((1..5).map { wrongDigit }))

        assertTrue { result is CodeValidator.Result.Wrong }
    }

    @Test
    fun parametrized_tests() {
        //     private val correctCode = Code(listOf(White, White, Yellow, Black, Red))
        // White(0), Black(1), Blue(2), Aqua(3), Pink(4), Red(5), Orange(6), Yellow(7)
        val failureString = StringBuilder()
        arrayOf(
            TestCase(
                correctCode = correctCode,
                codeInTest = Code(listOf(Blue, Aqua, Orange, Red, Pink)),
                expectedResult = CodeValidator.Result.Wrong(rightDigits = 1, rightPositions = 0)
            ),
            TestCase(
                correctCode = correctCode,
                codeInTest = Code(listOf(Blue, Yellow, Orange, Red, Pink)),
                expectedResult = CodeValidator.Result.Wrong(rightDigits = 2, rightPositions = 0)
            ),
            TestCase(
                correctCode = correctCode,
                codeInTest = Code(listOf(Blue, Yellow, Orange, Red, White)),
                expectedResult = CodeValidator.Result.Wrong(rightDigits = 3, rightPositions = 0)
            ),
            TestCase(
                correctCode = correctCode,
                codeInTest = Code(listOf(Black, Yellow, Orange, Red, White)),
                expectedResult = CodeValidator.Result.Wrong(rightDigits = 4, rightPositions = 0)
            )
        ).forEach { testCase ->
            classToTest = CodeValidatorImpl(correctCode = testCase.correctCode)

            val result = classToTest.validate(testCase.codeInTest)

            testCase.failureMessage
                .takeIf { result != testCase.expectedResult }
                ?.also { failureString.append("\n $it") }
        }

        assertEquals(expected = "", actual = failureString.toString())
    }

}

private class TestCase(val correctCode: Code, val codeInTest: Code, val expectedResult: CodeValidator.Result) {
    val failureMessage
        get() = "correctCode = $correctCode, codeInTest = $codeInTest, expectedResult = $expectedResult"
}