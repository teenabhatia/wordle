package org.cis1200.wordle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RunWordle implements Runnable {

    @Override
    public void run() {

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("Wordle");
        frame.setSize(600, 800);
        frame.setLocationRelativeTo(null);


        // Game board
        final GameBoard board = new GameBoard();
        frame.add(board, BorderLayout.CENTER);

        // clear button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.SOUTH);

        // Note here that when we add an action listener to the clear button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.
        final JButton restart = new JButton("New Game");
        restart.addActionListener(e -> board.restart());
        restart.addActionListener(e -> frame.requestFocus());
        control_panel.add(restart);

        final JButton save = new JButton("Save");
        save.addActionListener(e -> board.saveGameState());
        save.addActionListener(e -> frame.requestFocus());
        save.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Your progress has " +
                "been saved. To restore your progress, press the restore progress button."));
        control_panel.add(save);


        final JButton restore = new JButton("Restore Progress");
        restore.addActionListener(e -> board.restore());
        restore.addActionListener(e -> frame.requestFocus());
        control_panel.add(restore);

        final JButton exit = new JButton("Exit");
        exit.addActionListener(e -> System.exit(0));
        control_panel.add(exit);

        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (Character.isLetter(e.getKeyChar())) {
                    board.inputOnScreen(e.getKeyChar());
                } else if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    board.entered();
                } else if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    board.delete();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        };

        // Put the frame on the screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(listener);
        frame.setFocusable(true);

        JOptionPane.showMessageDialog(
                frame, "Welcome to Wordle\n" + "You have 6 tries to guess a 5 letter word.\n"
                        + "You can type your guesses in and press delete to delete a letter. " +
                        "\nHowever, " +
                        "once you have pressed enter, you cannot delete that word " +
                        "and it will count as a guess."
                        + "Gray tile: Letter does not exist in word.\n"
                        + "Yellow tile: Letter exists in the word but is in the wrong position,\n" +
                        "if you input a word with 2 or more of the same letters, " +
                        "more than one may turn yellow.\n"
                        + "Green tile: Correct letter in correct position.\n"
                        + "To save your progress, press Save. To restore a saved game, " +
                        "press Restore Progress.\n"
                        + "To exit, press Exit."
                        + "All the best!"
        );

    }

}
