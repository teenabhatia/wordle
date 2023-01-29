package org.cis1200.wordle;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GameBoard extends JPanel {

    private final Box[][] grid = new Box[6][5];

    final Model model = new Model(
            new Dictionary("src/main/java/org/cis1200/wordle/dict.txt")
    );

    JFrame frame = null;

    public GameBoard() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFocusable(true);
        GridLayout gl = new GridLayout(6, 5, 3, 3);
        this.setLayout(gl);
        this.setSize(500, 600);
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                grid[r][c] = new Box();
                grid[r][c].setBackground(model.getColor(r, c));
                grid[r][c].setForeground(Color.BLACK);
                grid[r][c].setText(model.getString(r, c));
                this.add(grid[r][c]);

            }
        }
        repaint();
        requestFocusInWindow();
        validate();

        // addMouseListener(new MouseAdapter() {
        // @Override
        // public void mouseReleased(MouseEvent e) {
        // Point p = e.getPoint();
        // requestFocusInWindow();
        // }
        // });
    }

    public void setWord(int r, int c, String letter) {
        model.setString(r, c, letter);
        grid[r][c].setText(letter);
    }

    public String getWord() {
        String word = "";
        for (Box b : grid[CurrentTurn.getRow()]) {
            word = word.concat(b.getText());
        }
        return word;
    }

    public void entered() {
        String answer = getWord();
        int ans = model.checkIfCorrect(answer);
        if (ans == 1) {
            changeColor();
            JOptionPane.showMessageDialog(frame, "Good Job! The word was " +
                    Dictionary.getResult() + "\nIf you would like to play again, press New Game.");
            CurrentTurn.setColumn(7);
            CurrentTurn.setRow(7);
        } else if (ans == 2) {
            changeColor();
            JOptionPane.showMessageDialog(
                    frame, "You're out of tries!\n" + "The word was " + Dictionary.getResult()
            );
            CurrentTurn.setColumn(7);
            CurrentTurn.setRow(7);
        } else if (ans == 3) {
            wrongWord();
        } else if (ans == 4) {
            changeColor();
            CurrentTurn.setColumn(0);
            CurrentTurn.setRow(CurrentTurn.getRow() + 1);
        }
    }

    public void changeColor() {
        // changes color
        int x = 0;
        for (Box b : grid[CurrentTurn.getRow()]) {
            if (model.correctPlace.contains(x)) {
                b.guess(b.getText(), 1);
                x++;
            } else if (model.wrongPlaceButCorrect.contains(x)) {
                b.guess(b.getText(), 3);
                x++;
            } else if (model.wrong.contains(x)) {
                b.guess(b.getText(), 2);
                x++;
            }
        }
    }

    public void wrongWord() {
        JOptionPane.showMessageDialog(frame, "Not a real word!");
        for (Box box : this.getGridRow(CurrentTurn.getRow())) {
            box.guess(" ", 0);
        }
        CurrentTurn.setColumn(0);
    }

    public Box[] getGridRow(int r) {
        Box[] copy = grid[r];
        return copy;
    }

    public Box getBox(int r, int c) {
        Box copy = grid[r][c];
        return copy;
    }

    public void saveGameState() {
        File file = new File(
                "src/main/java/org/cis1200/wordle/savedGame.txt"
        );
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(Dictionary.getResult() + "\n");
            for (int r = 0; r < 6; r++) {
                for (int c = 0; c < 5; c++) {
                    int temp = grid[r][c].getColor().getBlue();
                    bw.write(r + "\n" + c + "\n");
                    if (temp == 255) {
                        bw.write("" + "\n");
                    } else {
                        bw.write(grid[r][c].getLetter() + "\n");
                    }
                    bw.write((grid[r][c].getColor().getBlue()) + "\n");
                }
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("1: IO Exception Thrown");
        }
        setFocusable(true);
        restart();
    }

    public void inputOnScreen(char ch) {
        if (CurrentTurn.getColumn() < 5) {
            this.getBox(CurrentTurn.getRow(), CurrentTurn.getColumn())
                    .setText(String.valueOf(Character.toUpperCase(ch)));
            this.getBox(CurrentTurn.getRow(), CurrentTurn.getColumn())
                    .setText(String.valueOf(Character.toUpperCase(ch)));
            CurrentTurn.setColumn(1 + CurrentTurn.getColumn());
            model.lettersInputted.add(String.valueOf(Character.toUpperCase(ch)));
        }
    }

    public void delete() {
        if (CurrentTurn.getColumn() > 0) {
            CurrentTurn.setColumn(CurrentTurn.getColumn() - 1);
        }
        this.getBox(CurrentTurn.getRow(), CurrentTurn.getColumn()).setText(" ");
    }

    public void restart() {

        for (Box[] boxes : grid) {
            for (Box box : boxes) {
                box.guess(" ", 0);
            }
        }
        CurrentTurn.setRow(0);
        CurrentTurn.setColumn(0);
        model.restart();
    }

    public void restore() {
        int count = 0;
        File file = new File(
                "src/main/java/org/cis1200/wordle/savedGame.txt"
        );
        if (file.exists()) {
            try {
                FileReader reader = new FileReader(
                        "src/main/java/org/cis1200/wordle/savedGame.txt"
                );
                BufferedReader buff = new BufferedReader(reader);
                Dictionary.result = buff.readLine();
                System.out.println(Dictionary.getResult());
                boolean temp = false;
                for (int row = 0; row < 6; row++) {
                    for (int col = 0; col < 5; col++) {
                        String r = buff.readLine();
                        String c = buff.readLine();
                        String letter = buff.readLine();
                        String color = buff.readLine();
                        if (color.equals("46")) { // green
                            grid[Integer.parseInt(r)][Integer.parseInt(c)].guess(letter, 1);
                            count++;
                        } else if (color.equals("63")) { // yellow
                            grid[Integer.parseInt(r)][Integer.parseInt(c)].guess(letter, 3);
                        } else if (color.equals("135")) { // grey
                            grid[Integer.parseInt(r)][Integer.parseInt(c)].guess(letter, 2);
                        } else { // white
                            grid[Integer.parseInt(r)][Integer.parseInt(c)].guess(letter, 4);
                        }
                        if (letter.equals("") && !temp && count >= 5) {
                            CurrentTurn.setColumn(7);
                            CurrentTurn.setRow(7);
                            temp = true;
                        } else if (letter.equals("") && !temp) {
                            CurrentTurn.setColumn(Integer.parseInt(c));
                            CurrentTurn.setRow(Integer.parseInt(r));
                            temp = true;
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("IO Exception Thrown");
            }
        }
    }
}