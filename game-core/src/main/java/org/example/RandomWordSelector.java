package org.example;

import java.io.*;
import java.util.Random;

@SuppressWarnings("RegexpSinglelineJava")
public class RandomWordSelector {
    private String[] words = new String[0];

    public RandomWordSelector() {
        getWordsFromFile();
    }

    private void getWordsFromFile() {
        StringBuilder strbldr = new StringBuilder();
        try (InputStream is = getClass().getResourceAsStream("/words.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                strbldr.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String wordsSeparate = strbldr.toString();
        words = wordsSeparate.split(", ");
    }

    public String getRandomWord() {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }
}
