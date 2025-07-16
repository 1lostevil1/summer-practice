package org.example;

import org.junit.jupiter.api.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Пустая строка")
    void emptyStr() {
        ConsoleReader consoleReader = new ConsoleReader(""); // пустой ввод
        GameLogic gameLogic = new GameLogic(consoleReader);
        boolean result = gameLogic.start("");
        assertFalse(result);
        String output = outContent.toString();
        assertTrue(output.isEmpty() || !output.contains(">A word has been guessed!"));
    }

    @Test
    @DisplayName("Превышено количество попыток")
    void maxCount() {
        // Ввод 6 букв - все неверные (y,i,o,f,E)
        String input = "a\ny\ni\no\nf\nE\nE\n"; // последний "E" - выбор выхода из меню
        ConsoleReader consoleReader = new ConsoleReader(input);
        GameLogic gameLogic = new GameLogic(consoleReader);
        gameLogic.start("rect");
        String output = outContent.toString();
        assertTrue(output.contains(">You lost!"), "Output should contain losing message");
    }

    @Test
    @DisplayName("Проверка что состояние игры при угадывании корректно изменяется")
    void guess() {
        // Ввод: r, e, c, t, E(выйти)
        String input = "r\ne\nc\nt\nE\n";
        ConsoleReader consoleReader = new ConsoleReader(input);
        GameLogic gameLogic = new GameLogic(consoleReader);
        gameLogic.start("rect");
        String output = outContent.toString();

        // Проверяем наличие "Hit!" хотя бы 4 раза (один за каждую угаданную букву):
        long hitCount = output.lines().filter(line -> line.contains("Hit!")).count();
        assertEquals(4, hitCount, "Should have 4 hits for letters r,e,c,t");
        assertTrue(output.contains(">You won!"));
    }

    @Test
    @DisplayName("Проверка что состояние игры при не угадывании корректно изменяется")
    void notGuess() {
        // Ввод: y (не угадали), r, e, c, t, E(выход)
        String input = "y\nr\ne\nc\nt\nE\n";
        ConsoleReader consoleReader = new ConsoleReader(input);
        GameLogic gameLogic = new GameLogic(consoleReader);
        gameLogic.start("rect");
        String output = outContent.toString();

        assertTrue(output.contains("Missed, mistake 1 out of 5."),
                "Output should contain mistake notice");
    }

    @Test
    @DisplayName("Проверка ввода строки длиной больше 1 приводит к повторному вводу")
    void wrongLetter() {
        // Ввод: "ty" (неверный), затем r,e,c,t,E
        // При вводе "ty" должен быть запрос повторного ввода с подсказкой "Input correct letter"
        String input = "ty\nr\ne\nc\nt\nE\n";
        ConsoleReader consoleReader = new ConsoleReader(input);
        GameLogic gameLogic = new GameLogic(consoleReader);
        gameLogic.start("rect");
        String output = outContent.toString();

        assertTrue(output.contains("Input correct letter"),
                "Output should contain prompt for correct letter after wrong input");
    }
}
