package org.demo.boards;

import org.demo.game.Board;

public class TicTacToeBoard extends Board
{
    private String[][] cells = new String[3][3];

    public String getCells(int row, int col)
    {
        return cells[row][col];
    }

    public void setCells(int row, int col, String value)
    {
        cells[row][col] = value;
    }
}
