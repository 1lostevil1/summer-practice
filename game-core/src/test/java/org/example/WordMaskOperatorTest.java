package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordMaskOperatorTest {

    private WordMaskOperator operator;

    @BeforeEach
    void setup() {
        operator = new WordMaskOperator();
        operator.setWord("hello");
    }

    @Test
    void setWord_initialMaskCorrect() {
        assertEquals("*****", operator.getMaskString());
    }

    @Test
    void checkLetterInSet_returnsTrueIfLetterInWord() {
        assertTrue(operator.checkLetterInSet("h"));
        assertTrue(operator.checkLetterInSet("e"));
        assertTrue(operator.checkLetterInSet("l"));
        assertTrue(operator.checkLetterInSet("o"));
    }

    @Test
    void checkLetterInSet_returnsFalseIfLetterNotInWord() {
        assertFalse(operator.checkLetterInSet("z"));
    }

    @Test
    void updateMask_revealsCorrectLetters() {
        operator.updateMask("l");
        // После открытия буквы 'l' маска должна выглядеть как "**ll*"
        assertEquals("**ll*", operator.getMaskString());
    }

    @Test
    void inputLetterInSet_and_isLetterUsed() {
        assertFalse(operator.isLetterUsed("a"));
        operator.inputLetterInSet("a");
        assertTrue(operator.isLetterUsed("a"));
    }

    @Test
    void clearBuffer_resetsUsedLettersAndCounters() {
        operator.inputLetterInSet("a");
        operator.updateMask("h");
        operator.clearBuffer();

        assertFalse(operator.isLetterUsed("a"));
        assertEquals(0, operator.getNumberGuessletter());
        assertTrue(operator.getWordUniqueLetters().isEmpty());
    }
}
