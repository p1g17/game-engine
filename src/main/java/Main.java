import org.demo.api.GameEngine;
import org.demo.boards.TicTacToeBoard;
import org.demo.game.Board;
import org.demo.game.Cell;
import org.demo.game.Move;
import org.demo.game.Player;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Scanner scanner = new Scanner(System.in);
            GameEngine gameEngine = new GameEngine();
            Board board = gameEngine.start("TicTacToe");

            while (!gameEngine.isComplete(board).isOver())
            {
                System.out.println("Current Status::\n" + (TicTacToeBoard) board);
                System.out.println("Make Your Move");

                Player computer = new Player("O");
                Player opponent = new Player("X");

                int row = scanner.nextInt();
                int col = scanner.nextInt();

                Move oppMove = new Move(new Cell(row, col));
                gameEngine.move(board, opponent, oppMove);

                if (!gameEngine.isComplete(board).isOver())
                {
                    Move computerMove = gameEngine.suggestMove(computer, board);
                    gameEngine.move(board, computer, computerMove);
                }
            }
            System.out.println("GameResult: " + gameEngine.isComplete(board));
            System.out.println("FinalResult::\n" + (TicTacToeBoard) board);
            System.out.println("Gave is Over!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
