package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameLogicMockitoTest {

    @Mock
    private ConsoleReader mockConsoleReader;

    @Mock
    private RandomWordSelector mockWordSelector;

    @Spy
    private WordMaskOperator wordMaskOperatorSpy;

    private GameLogic gameLogic;

    @BeforeEach
    void setUp() {
        gameLogic = new GameLogic(mockConsoleReader, wordMaskOperatorSpy, mockWordSelector);
    }

    @Test
    @DisplayName("Игра прерывается по команде 'give up'")
    void testGameFlow_withGiveUp() {
        when(mockConsoleReader.inputLetter()).thenReturn("give up");
        when(mockConsoleReader.inputChoice()).thenReturn("E");

        boolean result = gameLogic.start("test");

        assertTrue(result);
        verify(mockConsoleReader, atLeastOnce()).inputLetter();
        verify(mockConsoleReader, times(1)).inputChoice();
        verify(wordMaskOperatorSpy, times(1)).setWord("test");
    }

    @Test
    @DisplayName("Успешное угадывание всех букв и выход")
    void testGameFlow_withGuessCorrectThenExit() {
        when(mockConsoleReader.inputLetter()).thenReturn("a", "b", "c");
        when(mockConsoleReader.inputChoice()).thenReturn("E");

        boolean result = gameLogic.start("abc");

        assertTrue(result);
        verify(wordMaskOperatorSpy, atLeast(1)).updateMask(anyString());
        verify(wordMaskOperatorSpy, times(3)).inputLetterInSet(anyString());
        verify(mockConsoleReader, times(1)).inputChoice();
    }

    @Test
    @DisplayName("Проигрыш после максимума ошибок")
    void testGameFlow_withMistakesAndLose() {
        when(mockConsoleReader.inputLetter()).thenReturn("x", "y", "z", "w", "q", "e");
        when(mockConsoleReader.inputChoice()).thenReturn("E");

        boolean result = gameLogic.start("abc");

        assertTrue(result);
        verify(wordMaskOperatorSpy, times(5)).inputLetterInSet(anyString());
        verify(mockConsoleReader, times(1)).inputChoice();
    }
}