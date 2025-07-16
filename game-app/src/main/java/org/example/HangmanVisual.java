package org.example;

public class HangmanVisual {

    private static final String[] HANGMAN_STATES = new String[]{
            """
          +---+
          |   |
              |
              |
              |
              |
        =========
        """,
            """
          +---+
          |   |
          O   |
          |   |
              |
              |
        =========
        """,
            """
          +---+
          |   |
          O   |
         /|   |
              |
              |
        =========
        """,
            """
          +---+
          |   |
          O   |
         /|\\  |
              |
              |
        =========
        """,
            """
          +---+
          |   |
          O   |
         /|\\  |
         / \\  |
              |
        =========
        """
    };


    public static String getVisual(int mistakesCount) {
        if (mistakesCount < 0) return HANGMAN_STATES[0];
        if (mistakesCount >= HANGMAN_STATES.length) return HANGMAN_STATES[HANGMAN_STATES.length - 1];
        return HANGMAN_STATES[mistakesCount];
    }
}

