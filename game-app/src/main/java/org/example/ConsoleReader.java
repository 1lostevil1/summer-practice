package org.example;

import java.util.Scanner;

public class ConsoleReader {

    private final Scanner scanner;

    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    public ConsoleReader(String input) {
        this.scanner = new Scanner(input);
    }

    public String inputLetter() {
        String letter;
        while (true) {
            letter = scanner.nextLine();
            if (letter.equalsIgnoreCase("give up")) {
                return "give up";
            }
            if (letter.length() == 1 && Character.isLetter(letter.charAt(0))) {
                return letter;
            }
            System.out.print(">Input correct letter:\n<");
        }
    }

    public String inputChoice() {
        String choice;
        while (true) {
            choice = scanner.nextLine();
            if (choice.length() == 1 && ("NnEe".contains(choice))) {
                return choice;
            }
            System.out.print(">Input correct choice:\n<");
        }
    }
}
