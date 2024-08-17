package org.demo.api;

import org.demo.boards.TicTacToeBoard;
import org.demo.game.*;

public class GameEngine
{
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
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            ticTacToeBoard.setCell(move.getCell(), player.symbol());
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
                firstCharacter = ticTacToeBoard.getCells(i, 0);
                rawComplete = firstCharacter != null;
                if (firstCharacter != null)
                {
                    for (int j = 1; j < 3; j++)
                    {
                        if (!firstCharacter.equals(ticTacToeBoard.getCells(i, j)))
                        {
                            rawComplete = false;
                            break;
                        }
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
                firstCharacter = ticTacToeBoard.getCells(0, i);
                colComplete = firstCharacter != null;
                if (firstCharacter != null)
                {
                    for (int j = 1; j < 3; j++)
                    {
                        if (!firstCharacter.equals(ticTacToeBoard.getCells(j, i)))
                        {
                            colComplete = false;
                            break;
                        }
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
            firstCharacter = ticTacToeBoard.getCells(0, 0);
            boolean diagComplete = firstCharacter != null;
            for (int i = 0; i < 3; i++)
            {
                if (firstCharacter != null && !firstCharacter.equals(ticTacToeBoard.getCells(i, i)))
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
            firstCharacter = ticTacToeBoard.getCells(0, 2);
            boolean revDiagComplete = firstCharacter != null;
            for (int i = 0; i < 3; i++)
            {
                if (firstCharacter != null && !firstCharacter.equals(ticTacToeBoard.getCells(i, 2 - i)))
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
                    if (ticTacToeBoard.getCells(i, j) != null)
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
                        return new Move(new Cell(i, j));
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