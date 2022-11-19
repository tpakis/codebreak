import kotlin.test.Test
import kotlin.test.assertNotNull

class CodeGeneratorTest {
    private val classToTest = CodeGenerator

    @Test
    fun generateCode_should_return_a_new_code() {
        val code = classToTest.generateCode()

        assertNotNull(code)
    }
}