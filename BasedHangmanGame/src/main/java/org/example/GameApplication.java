package org.example;

import org.example.Game.ConsoleReader;
import org.example.Game.GameLogic;
import org.example.Game.RandomWordSelector;

public class GameApplication {
    public static void main(String[] args) {

        RandomWordSelector rws = new RandomWordSelector();

        ConsoleReader consoleReader = new ConsoleReader();
        GameLogic game = new GameLogic(consoleReader);
        game.start(rws.getRandomWord());
    }
}