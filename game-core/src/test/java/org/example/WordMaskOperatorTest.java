package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordMaskOperatorTest {

    private WordMaskOperator operator;

    @BeforeEach
    void setup() {
        // Перед каждым тестом создаётся новый объект и устанавливается слово "hello"
        operator = new WordMaskOperator();
        operator.setWord("hello");
    }

    @Test
    @DisplayName("Маска инициализируется как строка из звездочек длиной слова")
    void setWord_initialMaskCorrect() {
        assertEquals("*****", operator.getMaskString());
    }

    @Test
    @DisplayName("Проверка наличия буквы в слове: буквы есть в слове")
    void checkLetterInSet_returnsTrueIfLetterInWord() {
        assertTrue(operator.checkLetterInSet("h"));
        assertTrue(operator.checkLetterInSet("e"));
        assertTrue(operator.checkLetterInSet("l"));
        assertTrue(operator.checkLetterInSet("o"));
    }

    @Test
    @DisplayName("Проверка наличия буквы в слове: буквы нет в слове")
    void checkLetterInSet_returnsFalseIfLetterNotInWord() {
        assertFalse(operator.checkLetterInSet("z"));
    }

    @Test
    @DisplayName("Обновление маски открывает все совпадающие буквы")
    void updateMask_revealsCorrectLetters() {
        operator.updateMask("l");
        assertEquals("**ll*", operator.getMaskString());
    }

    @Test
    @DisplayName("Проверка добавления и проверки использованных букв")
    void inputLetterInSet_and_isLetterUsed() {
        assertFalse(operator.isLetterUsed("a"));
        operator.inputLetterInSet("a");
        assertTrue(operator.isLetterUsed("a"));
    }

    @Test
    @DisplayName("Очистка состояния сбрасывает использованные буквы, счетчик и уникальные буквы слова")
    void clearBuffer_resetsUsedLettersAndCounters() {
        operator.inputLetterInSet("a");
        operator.updateMask("h");
        operator.clearBuffer();

        assertFalse(operator.isLetterUsed("a"));
        assertEquals(0, operator.getNumberGuessletter());
        assertTrue(operator.getWordUniqueLetters().isEmpty());
    }
}