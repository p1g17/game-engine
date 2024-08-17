package org.demo.api;

import org.demo.boards.TicTacToeBoard;
import org.demo.game.*;

public class AIEngine
{

    public Move suggestMove(Player computer, Board board)
    {
        if (board instanceof TicTacToeBoard)
        {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if (ticTacToeBoard.getCells(i, j) == null)
                    {
                        return new Move(new Cell(i, j), computer);
                    }
                }
            }
            throw new IllegalStateException();
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
}
