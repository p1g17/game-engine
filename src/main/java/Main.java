import org.demo.api.AIEngine;
import org.demo.api.GameEngine;
import org.demo.api.RuleEngine;
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
            RuleEngine ruleEngine = new RuleEngine();
            AIEngine aiEngine = new AIEngine();
            Board board = gameEngine.start("TicTacToe");

            while (!ruleEngine.getState(board).isOver())
            {
                System.out.println("Current Status::\n" + (TicTacToeBoard) board);
                System.out.println("Make Your Move");

                Player computer = new Player("O");
                Player human = new Player("X");

                int row = scanner.nextInt();
                int col = scanner.nextInt();

                Move oppMove = new Move(new Cell(row, col), human);
                gameEngine.move(board, oppMove);

                if (!ruleEngine.getState(board).isOver())
                {
                    Move computerMove = aiEngine.suggestMove(computer, board);
                    gameEngine.move(board, computerMove);
                }
            }
            System.out.println("GameResult: " + ruleEngine.getState(board));
            System.out.println("FinalResult::\n" + (TicTacToeBoard) board);
            System.out.println("Gave is Over!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
