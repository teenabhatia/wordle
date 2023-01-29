package org.cis1200.wordle;

public class CurrentTurn {

    private static int column = 0;
    private static int row = 0;

    public static void setColumn(int col) {
        column = col;
    }

    public static void setRow(int r) {
        row = r;
    }

    public static int getColumn() {
        return column;
    }

    public static int getRow() {
        return row;
    }

}
