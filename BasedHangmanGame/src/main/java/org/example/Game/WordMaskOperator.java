package org.example.Game;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("RegexpSinglelineJava")
public class WordMaskOperator {
    private String word;
    private String[] mask;
    private int numberGuessletter = 0;
    private final Set<String> usedLetters = new HashSet<>();
    private final Set<String> wordUniqueLetters = new HashSet<>();

    public Set<String> getWordUniqueLetters() {
        return wordUniqueLetters;
    }

    public int getNumberGuessletter() {
        return numberGuessletter;
    }

    public void setWord(String word) {
        this.word = word;
        this.mask = new String[word.length()];
        Arrays.fill(mask, "*");
        Collections.addAll(wordUniqueLetters, word.split(""));
    }

    public void printMask() {
        System.out.print(">\n>The word: " + String.join("", mask) + "\n>");
    }

    public void updateMask(String letter) {
        for (int i = 0; i < word.length(); ++i) {
            if (Character.toString(word.charAt(i)).equalsIgnoreCase(letter)) {
                mask[i] = letter;
            }
        }
        numberGuessletter++;
    }

    public void inputLetterInSet(String letter) {
        usedLetters.add(letter);
    }

    public boolean checkLetterInSet(String letter) {
        return wordUniqueLetters.contains(letter);
    }

    public boolean isLetterUsed(String letter) {
        return usedLetters.contains(letter);
    }

    public void clearBuffer() {
        usedLetters.clear();
        wordUniqueLetters.clear();
        numberGuessletter = 0;
    }

}