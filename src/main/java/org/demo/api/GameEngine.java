package org.demo.api;

import org.demo.game.Board;
import org.demo.game.GameResult;
import org.demo.game.Move;
import org.demo.boards.*;
import org.demo.game.Player;

public class GameEngine
{
    public static void main(String[] args)
    {
        System.out.println("Hello world!");
    }

    public Board start(String type)
    {
        if (type.equals("TicTacToe"))
        {
            return new TicTacToeBoard();
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    public void move(Board board, Player player, Move move)
    {
        if (board instanceof TicTacToeBoard)
        {
            board.setCell(player.symbol(), Move.getCell());
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    public GameResult isComplete(Board board)
    {
        if (board instanceof TicTacToeBoard)
        {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            String firstCharacter = "-";

            // Takes care of all the rows
            boolean rawComplete = true;
            for (int i = 0; i < 3; i++)
            {
                rawComplete = true;
                firstCharacter = ticTacToeBoard.getCells(i, 0);
                for (int j = 1; j < 3; j++)
                {
                    if (!ticTacToeBoard.getCells(i, j).equals(firstCharacter))
                    {
                        rawComplete = false;
                        break;
                    }
                }
                if (rawComplete)
                {
                    break;
                }
            }
            if (rawComplete)
            {
                return new GameResult(true, firstCharacter);
            }

            // Takes care of all the columns
            boolean colComplete = true;
            for (int i = 0; i < 3; i++)
            {
                colComplete = true;
                firstCharacter = ticTacToeBoard.getCells(0, i);
                for (int j = 1; j < 3; j++)
                {
                    if (!ticTacToeBoard.getCells(j, i).equals(firstCharacter))
                    {
                        colComplete = false;
                        break;
                    }
                }
                if (colComplete)
                {
                    break;
                }
            }
            if (colComplete)
            {
                return new GameResult(true, firstCharacter);
            }

            // Takes care of diagonal-1
            boolean diagComplete = true;
            for (int i = 0; i < 3; i++)
            {
                diagComplete = true;
                firstCharacter = ticTacToeBoard.getCells(0, 0);
                if (!ticTacToeBoard.getCells(i, i).equals(firstCharacter))
                {
                    diagComplete = false;
                    break;
                }
            }
            if (diagComplete)
            {
                return new GameResult(true, firstCharacter);
            }

            // Takes care of diagonal-2
            boolean revDiagComplete = true;
            for (int i = 0; i < 3; i++)
            {
                revDiagComplete = true;
                firstCharacter = ticTacToeBoard.getCells(0, 2);
                if (!ticTacToeBoard.getCells(i, 2 - i).equals(firstCharacter))
                {
                    revDiagComplete = false;
                    break;
                }
            }

            if (revDiagComplete)
            {
                return new GameResult(true, firstCharacter);
            }

            int countOfFilledCells = 0;
            for (int i = 0; i < 3; i++)
            {
                for (int j = 1; j < 3; j++)
                {
                    if (ticTacToeBoard.getCells(j, i) != null)
                    {
                        countOfFilledCells++;
                    }
                }
            }

            if (countOfFilledCells == 9)
            {
                return new GameResult(true, "-");
            }
            else
            {
                return new GameResult(false, "-");
            }
        }
        else
        {
            return new GameResult(false, "-");
        }
    }
}