package code.models

class Code private constructor() {
    private val _digits = mutableListOf<CodeDigit>()
    val digits: List<CodeDigit> get() = _digits.toList()

    override fun toString(): String {
        return "Code($digits)"
    }

    companion object {
        operator fun invoke(digits: List<CodeDigit>): Code {
            require(digits.size == 5) { "A code consists of 5 digits" }
            return Code().apply { _digits.addAll(digits) }
        }
    }
}