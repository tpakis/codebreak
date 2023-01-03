package code

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class RandomCodeGeneratorTest {
    private val classToTest = RandomCodeGenerator

    @Test
    fun generateRandomCode_should_return_a_new_code() {
        val code = classToTest.generateRandomCode()

        assertNotNull(code)
    }

    @Test
    fun generateRandomCode_should_return_code_without_duplicates_when_duplicates_are_not_allowed() {
        repeat(100) {
            val code = classToTest.generateRandomCode(duplicatesAllowed = false)

            assertEquals(code.digits.size, code.digits.toSet().size)
        }
    }

    @Test
    fun generateRandomCode_should_return_code_with_duplicates_when_duplicates_are_allowed() {
        val maxAttempts = 100
        var attemptsCount = 0

        do {
            val code = classToTest.generateRandomCode(duplicatesAllowed = true)
            if (code.digits.size > code.digits.toSet().size) {
                break
            }
            attemptsCount++
        } while (attemptsCount < maxAttempts)

        assertTrue(attemptsCount < maxAttempts)
    }
}