package org.demo.boards;

import org.demo.game.Board;
import org.demo.game.Cell;
import org.demo.game.Move;

import java.util.Arrays;

public class TicTacToeBoard extends Board
{
    private String[][] cells = new String[3][3];

    public String getCells(int row, int col)
    {
        return cells[row][col];
    }

    public void setCell(Cell cell, String symbol)
    {
        cells[cell.getRow()][cell.getCol()] = symbol;
    }

    public void move(Move move)
    {
        this.setCell(move.getCell(), move.getPlayer().symbol());
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                String value = cells[i][j];
                result.append(value != null ? value : "-");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
