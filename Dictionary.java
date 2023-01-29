package org.cis1200.wordle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Dictionary {

    private static final ArrayList<String> WORDS = new ArrayList<>();
    private BufferedReader buff;
    static String result = "hello";

    public Dictionary(String fp) {
        try {
            FileReader reader = new FileReader(fp);
            buff = new BufferedReader(reader);
            String word = buff.readLine();
            while (word != null) {
                WORDS.add(word);
                word = buff.readLine();
            }
            WORDS.removeIf(w -> w.contains("'")
                    || w.contains(" ")
                    || w.contains("-")
                    || w.contains("/")
                    || w.length() != 5);
            WORDS.replaceAll(String::toUpperCase);

        } catch (IOException e) {
            System.out.println("IO Exception while reading line");
        } finally {
            if (buff != null) {
                try {
                    buff.close();
                } catch (IOException e) {
                    System.out.println("IO Exception tried closing buffered reader");
                }
            }

        }
    }

    public static void randomWord() {
        Random rand = new Random();
        result = WORDS.get(rand.nextInt(WORDS.size()));
    }

    public static String getResult() {
        return result;
    }

    public static void setResult(String word) {
        if (realWord(word)) {
            result = word;
        }
    }

    public static boolean realWord(String word) {
        return WORDS.contains(word);
    }
}
