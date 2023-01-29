package org.cis1200.wordle;

import java.awt.*;
import java.util.*;

public class Model {

    private final Box[][] board = new Box[6][5];

    ArrayList<Integer> correctPlace = new ArrayList<>();

    ArrayList<Integer> wrongPlaceButCorrect = new ArrayList<>();

    ArrayList<Integer> wrong = new ArrayList<>();

    LinkedList<String> lettersInputted = new LinkedList<>();

    public Model(Dictionary dict) {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new Box();
            }
        }
        restart();
    }

    public String getString(int i, int j) {
        return board[i][j].getLetter();
    }

    public void setString(int i, int j, String letter) {
        board[i][j].setText(letter.toUpperCase());
    }

    public void restart() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new Box();
            }
        }
        for (Box[] boxes : board) {
            for (Box box : boxes) {
                box.guess(" ", 0);
            }
        }

        CurrentTurn.setRow(0);
        CurrentTurn.setColumn(0);

        lettersInputted = new LinkedList<>();

        Dictionary.randomWord();
        System.out.println(Dictionary.getResult());
    }

    public int checkIfCorrect(String word) {
        ArrayList<String> answerLetters = new ArrayList<>();
        ArrayList<String> enteredLetters = new ArrayList<>();
        correctPlace = new ArrayList<>();
        wrongPlaceButCorrect = new ArrayList<>();
        wrong = new ArrayList<>();
        String[] answerSplit = Dictionary.getResult().split("");
        Collections.addAll(answerLetters, answerSplit);
        String[] wordSplit = word.split("");
        Collections.addAll(enteredLetters, wordSplit);
        if (CurrentTurn.getColumn() == 5) {
            if (Dictionary.realWord(word)) {
                for (int x = 0; x < 5; x++) {
                    if (wordSplit[x].equals(answerSplit[x])) {
                        correctPlace.add(x);
                        answerLetters.set(x, "");
                    }
                }

                for (int y = 0; y < 5; y++) {
                    if (answerLetters.contains(enteredLetters.get(y))) {
                        wrongPlaceButCorrect.add(y);
                    }
                }
                for (int z = 0; z < 5; z++) {
                    if (!correctPlace.contains(z) && !wrongPlaceButCorrect.contains(z)) {
                        wrong.add(z);
                    }
                }

                // ALL CORRECT
                boolean empty = answerLetters.get(0).equals("") && answerLetters.get(1).equals("")
                        &&
                        answerLetters.get(2).equals("") && answerLetters.get(3).equals("") &&
                        answerLetters.get(4).equals("");
                if (empty) {
                    return 1;
                } else if (CurrentTurn.getRow() == 5) {
                    // OUT OF TRIES
                    if (Dictionary.realWord(word)) {
                        return 2;
                    } else {
                        // NOT A REAL WORD
                        return 3;
                    }
                } else {
                    // REAL WORD BUT NOT FULLY CORRECT
                    if (Dictionary.realWord(word)) {
                        return 4;
                    } else {
                        return 3;
                    }
                }
            } else {
                return 3;
            }
        } else {
            // NOT A FULL WORD TYPED
            return 0;
        }

    }

    public Color getColor(int i, int j) {
        return board[i][j].getColor();
    }

}
