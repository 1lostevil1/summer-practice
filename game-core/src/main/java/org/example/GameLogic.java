package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

@SuppressWarnings("RegexpSinglelineJava")
public class GameLogic {
    private final RandomWordSelector wordSelector = new RandomWordSelector();
    private final WordMaskOperator maskOperator = new WordMaskOperator();

    private int mistakesCount;
    private String letter;

    final static int WRONG_COUNT_MISTAKES = -1;
    final static int MAX_COUNT_MISTAKES = 5;

    private final ConsoleReader consoleReader;

    private static final Logger logger = LogManager.getLogger(GameLogic.class);

    public GameLogic(ConsoleReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    private boolean win(int numberGuessLetter, Set<String> wordUniqueLetters) {
        return numberGuessLetter == wordUniqueLetters.size();
    }

    public boolean start(String guessedWord) {

        logger.info("Starting game with word: {}", guessedWord);

        if (guessedWord == null || guessedWord.isEmpty()) {
            return false;
        }

        mistakesCount = 0;
        boolean gameEnded = false;
        maskOperator.clearBuffer();
        maskOperator.setWord(guessedWord);

        System.out.print(">A word has been guessed!\n>If you want to give up, enter - give up\n");

        while (!win(maskOperator.getNumberGuessletter(), maskOperator.getWordUniqueLetters())) {
            System.out.print("\n>Guess a letter: \n<");
            letter = consoleReader.inputLetter();

            if (letter.equalsIgnoreCase("give up")) {
                mistakesCount = WRONG_COUNT_MISTAKES;
                System.out.print("\n>You give up!");
                gameEnded = true;
                break;
            }

            if (maskOperator.isLetterUsed(letter)) {
                System.out.print(">\n>This letter is already used!");
            } else {
                maskOperator.inputLetterInSet(letter);
                if (maskOperator.checkLetterInSet(letter)) {
                    System.out.print(">Hit!\n");
                    maskOperator.updateMask(letter);
                    maskOperator.printMask();
                } else {
                    mistakesCount++;
                    System.out.print(">Missed, mistake " + mistakesCount + " out of "
                            + MAX_COUNT_MISTAKES + ".\n");
                    maskOperator.printMask();
                }
            }

            if (mistakesCount == MAX_COUNT_MISTAKES) {
                System.out.print("\n>You lost!");
                gameEnded = true;
                break;
            }
        }

        if (!gameEnded) {
            System.out.print("\n>You won!");
        }

        System.out.print("\n>Menu: [N]ew game/ [E]xit\n<");
        String choice = consoleReader.inputChoice();

        if (choice.equalsIgnoreCase("N")) {
            return start(wordSelector.getRandomWord());
        } else {
            return true;
        }
    }
}
