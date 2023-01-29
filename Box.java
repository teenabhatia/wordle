package org.cis1200.wordle;

import javax.swing.*;
import java.awt.*;

public class Box extends JLabel {
    public final static Color GREEN_COLOR = new Color(46, 154, 46);
    public final static Color YELLOW_COLOR = new Color(210, 210, 63);
    public final static Color GREY_COLOR = new Color(136, 135, 135);

    public Box() {
        this.setVerticalAlignment(JLabel.CENTER);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setSize(50, 50);
        this.setText(" ");
        this.setFont(new Font("Arial", Font.BOLD, 30));
        this.setBackground(Color.WHITE);
        this.setForeground(Color.DARK_GRAY);
        this.setOpaque(true);
    }

    private void changeColor(int correctness) {
        this.setForeground(Color.BLACK);
        if (correctness == 1) { // correct, colorGreen
            this.setBackground(GREEN_COLOR);
        } else if (correctness == 2) { // incorrect, colorGrey
            this.setBackground(GREY_COLOR);
        } else if (correctness == 3) { // incorrect position, colorYellow
            this.setBackground(YELLOW_COLOR);
        } else {
            this.setBackground(Color.WHITE);
            this.setForeground(Color.DARK_GRAY);
        }
    }

    public void guess(String letter, int correctness) {
        this.setText(letter);
        this.changeColor(correctness);
    }

    public String getLetter() {
        return this.getText();
    }

    public Color getColor() {
        return getBackground();
    }

}
