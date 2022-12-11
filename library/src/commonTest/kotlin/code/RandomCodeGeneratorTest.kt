package code

import kotlin.test.Test
import kotlin.test.assertNotNull

class RandomCodeGeneratorTest {
    private val classToTest = RandomCodeGenerator

    @Test
    fun generateRandomCode_should_return_a_new_code() {
        val code = classToTest.generateRandomCode()

        assertNotNull(code)
    }
}