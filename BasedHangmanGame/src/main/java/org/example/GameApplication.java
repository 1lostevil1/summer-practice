package org.example;

import org.example.Game.ConsoleReader;
import org.example.Game.GameLogic;
import org.example.Game.RandomWordSelector;

/*
Киреев Дмитрий 3к 91г

консольная версия игры"Виселица",в которой игрок пытается угадать загаданное слово,
вводя буквы по одной за раз/Слово выбирается по уровню сложности, случайно из предварительно заданного
списка слов и категории.Количество попыток ограничено,и за каждую неверную догадку визуализируется часть виселицы
и фигурки висельника.

Функциональные требования
Программа должна выбирать случайное слово из заранее определенного списка слов.
Игрок вводит одну букву за раз, чтобы угадать слово.
Экран должен обновляться после каждого ввода, показывая * на местах неуказанных букв.
Игра завершается, когда слово угадано полностью или когда кончаются попытки.
Количество попыток ограничено и должно быть указано в начале игры.
 */

public class GameApplication {
    public static void main(String[] args) {

        RandomWordSelector rws = new RandomWordSelector();

        ConsoleReader consoleReader = new ConsoleReader();
        GameLogic game = new GameLogic(consoleReader);
        game.start(rws.getRandomWord());
    }
}