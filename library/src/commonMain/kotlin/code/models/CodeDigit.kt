package code.models

enum class CodeDigit(private val id: Int) {
    White(0), Black(1), Blue(2), Aqua(3), Pink(4), Red(5), Orange(6), Yellow(7);

    companion object {
        fun getCodeDigitById(id: Int): CodeDigit {
            return values().firstOrNull { it.id == id } ?: throw IllegalArgumentException("id $id not found!")
        }
    }
}