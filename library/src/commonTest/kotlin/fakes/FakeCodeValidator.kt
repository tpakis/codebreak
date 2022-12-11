package fakes

import code.CodeValidator
import code.models.Code

internal class FakeCodeValidator: CodeValidator {

    var expectedResult: CodeValidator.Result = CodeValidator.Result.Correct

    override fun validate(code: Code): CodeValidator.Result {
        return expectedResult
    }

}