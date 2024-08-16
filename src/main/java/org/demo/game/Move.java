package org.demo.game;

public class Move
{
    private Cell cell;

    public static Cell getCell()
    {
        return cell;
    }
}

class Cell
{
    int row, col;

    public Cell(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
}
