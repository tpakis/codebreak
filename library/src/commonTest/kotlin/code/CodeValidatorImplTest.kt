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
        val failureString = StringBuilder()

        // Cases
        arrayOf(
            TestCase( // 0
                correctCode = correctCode,
                codeInTest = Code(listOf(Blue, Aqua, Orange, Red, Pink)),
                expectedResult = CodeValidator.Result.Wrong(rightDigits = 1, rightPositions = 0)
            ),
            TestCase( // 1
                correctCode = correctCode,
                codeInTest = Code(listOf(Blue, Yellow, Orange, Red, Pink)),
                expectedResult = CodeValidator.Result.Wrong(rightDigits = 2, rightPositions = 0)
            ),
            TestCase( // 2
                correctCode = correctCode,
                codeInTest = Code(listOf(Blue, Yellow, Orange, Red, White)),
                expectedResult = CodeValidator.Result.Wrong(rightDigits = 3, rightPositions = 0)
            ),
            TestCase( // 3
                correctCode = correctCode,
                codeInTest = Code(listOf(Black, Yellow, Orange, Red, White)),
                expectedResult = CodeValidator.Result.Wrong(rightDigits = 4, rightPositions = 0)
            ),
            TestCase( // 4
                correctCode = correctCode,
                codeInTest = Code(listOf(White, Blue, Orange, Aqua, Orange)),
                expectedResult = CodeValidator.Result.Wrong(rightDigits = 1, rightPositions = 1)
            ),
            TestCase( // 5
                correctCode = correctCode,
                codeInTest = Code(listOf(Blue, Orange, Yellow, Red, Pink)),
                expectedResult = CodeValidator.Result.Wrong(rightDigits = 2, rightPositions = 1)
            ),
            TestCase( // 6
                correctCode = correctCode,
                codeInTest = Code(listOf(Blue, Orange, Yellow, Red, White)),
                expectedResult = CodeValidator.Result.Wrong(rightDigits = 3, rightPositions = 1)
            ),
            TestCase( // 7
                correctCode = Code(listOf(Blue, Blue, Blue, Blue, Blue)),
                codeInTest = Code(listOf(Blue, Orange, Yellow, Red, White)),
                expectedResult = CodeValidator.Result.Wrong(rightDigits = 1, rightPositions = 1)
            ),
            TestCase( // 8
                correctCode = Code(listOf(Blue, Blue, Red, Red, Blue)),
                codeInTest = Code(listOf(Blue, Orange, Yellow, Red, White)),
                expectedResult = CodeValidator.Result.Wrong(rightDigits = 2, rightPositions = 2)
            ),
            TestCase( // 9
                correctCode = Code(listOf(Blue, Blue, Blue, Blue, Blue)),
                codeInTest = Code(listOf(Red, Red, Red, Red, Red)),
                expectedResult = CodeValidator.Result.Wrong(rightDigits = 0, rightPositions = 0)
            ),
        ).forEachIndexed { index, testCase ->
            classToTest = CodeValidatorImpl(correctCode = testCase.correctCode)

            val result = classToTest.validate(testCase.codeInTest)

            testCase.failureMessage
                .takeIf { result != testCase.expectedResult }
                ?.also { failureString.append("\n case($index) $it") }
        }

        assertEquals(expected = "", actual = failureString.toString())
    }

}

private class TestCase(val correctCode: Code, val codeInTest: Code, val expectedResult: CodeValidator.Result) {
    val failureMessage
        get() = "correctCode = $correctCode, codeInTest = $codeInTest, expectedResult = $expectedResult"
}