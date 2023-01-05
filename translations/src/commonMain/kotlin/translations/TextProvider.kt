package translations

interface TextProvider {
    val welcome: String
    val easy: String
    val medium: String
    val hard: String
    val practice: String
    val resumeGame: String
    val pauseGame: String
    val startGame: String
    val quitGame: String
    val attempts: String
    val checkCode: String
    val won: String
    val lost: String
    val gameEnd: String

    companion object {
        fun getByLanguage(language: String): TextProvider = if (language.equals("el", ignoreCase = true)) {
            GreekTextProvider()
        } else {
            EnglishTextProvider()
        }
    }
}

class EnglishTextProvider : TextProvider {
    override val welcome: String = "Welcome to Code Breaker!"
    override val easy: String = "Easy"
    override val medium: String = "Normal"
    override val hard: String = "Hard"
    override val practice: String = "Practice"
    override val resumeGame: String = "Resume Game"
    override val pauseGame: String = "Pause Game"
    override val startGame: String = "Start Game"
    override val quitGame: String = "Quit Game"
    override val attempts: String = "Code break Attempts"
    override val checkCode: String = "Check Code"
    override val won: String = "You Won!"
    override val lost: String = "You Lost! The Correct Code Was:"
    override val gameEnd: String = "Game Ended"
}

class GreekTextProvider : TextProvider {
    override val welcome: String = "Καλωσήρθατε στο Σπάσε τον κώδικα!"
    override val easy: String = "Εύκολο"
    override val medium: String = "Κανονικό"
    override val hard: String = "Δύσκολο"
    override val practice: String = "Προπόνηση"
    override val resumeGame: String = "Ξανάρχισε"
    override val pauseGame: String = "Διάλειμμα"
    override val startGame: String = "Πάμε!"
    override val quitGame: String = "Έξοδος"
    override val attempts: String = "Προσπάθειες λύσης:"
    override val checkCode: String = "Έλεγχος"
    override val won: String = "Νίκησες!"
    override val lost: String = "Έχασες! Ο σωστός κώδικας ήταν:"
    override val gameEnd: String = "Τέλος Παιχνιδιού"
}