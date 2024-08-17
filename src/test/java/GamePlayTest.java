import org.demo.api.AIEngine;
import org.demo.api.GameEngine;
import org.demo.api.RuleEngine;
import org.demo.boards.TicTacToeBoard;
import org.demo.game.Board;
import org.demo.game.Cell;
import org.demo.game.Move;
import org.demo.game.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GamePlayTest
{
    GameEngine gameEngine;

    AIEngine aiEngine;

    RuleEngine ruleEngine;

    @BeforeEach
    public void setup()
    {
        gameEngine = new GameEngine();
        aiEngine = new AIEngine();
        ruleEngine = new RuleEngine();
    }

    @Test
    public void checkForRowWin()
    {
        Board board = gameEngine.start("TicTacToe");
        // make move in loop
        int[][] moves = {{1, 0}, {1, 1}, {1, 2}};
        playGame(board, moves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForColWin()
    {
        Board board = gameEngine.start("TicTacToe");
        // make move in loop
        int[][] moves = {{0, 0}, {1, 0}, {2, 0}};
        playGame(board, moves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForDiagWin()
    {
        Board board = gameEngine.start("TicTacToe");
        // make move in loop
        int[][] moves = {{0, 0}, {1, 1}, {2, 2}};
        playGame(board, moves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForRevDiagWin()
    {
        Board board = gameEngine.start("TicTacToe");
        // make move in loop
        int[][] moves = {{0, 2}, {1, 1}, {2, 0}};
        playGame(board, moves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForComputerWin()
    {
        Board board = gameEngine.start("TicTacToe");
        // make move in loop
        int[][] moves = {{1, 0}, {1, 1}, {2, 0}};
        playGame(board, moves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "O");
    }

    private void playGame(Board board, int[][] moves)
    {
        int next = 0;
        while (!ruleEngine.getState(board).isOver())
        {
            System.out.println("Current Status::\n" + (TicTacToeBoard) board);
            System.out.println("Make Your Move");

            Player computer = new Player("O");
            Player human = new Player("X");

            int row = moves[next][0];
            int col = moves[next][1];

            Move oppMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, oppMove);

            if (!ruleEngine.getState(board).isOver())
            {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
            next++;
        }
    }
}
