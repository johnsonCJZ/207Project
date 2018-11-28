package fall2018.csc2017.slidingtiles;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Game2048BoardManagerTest {

    @Before
    private void setBoard(int largestTile, Game2048Board board){
        int n = 2;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                Game2048Tile tile = board.getTiles()[i][j];
                tile.setValue(n);
                if(n < largestTile){
                    n = n * 2;
                }
                else{
                    n = 2;
                }
            }
        }
    }

    @Test
    public void testLose() {
        Game2048BoardManager manager = new Game2048BoardManager();
        Game2048Board board = manager.getBoard();
        setBoard(1024, board);
        assertTrue(manager.isLose());
    }

    @Test
    public void testNotLoseHas2048CanMove() {
        Game2048BoardManager manager = new Game2048BoardManager();
        manager.cheat();
        assertFalse(manager.isLose());
    }

    @Test
    public void testNotLoseHas2048CantMove() {
        Game2048BoardManager manager = new Game2048BoardManager();
        Game2048Board board = manager.getBoard();
        setBoard(2048, board);
        assertFalse(manager.isLose());
    }

    @Test
    public void testNotLoseFullBoard() {

    }

    @Test
    public void testWinContains2048CantMove() {
        Game2048BoardManager manager = new Game2048BoardManager();
        Game2048Board board = manager.getBoard();
        setBoard(2048, board);
        assertTrue(manager.isWon());
    }

    @Test
    public void testNotWinCanMove() {
        Game2048BoardManager manager = new Game2048BoardManager();
        Game2048Board board = manager.getBoard();
        Game2048Tile tile= board.getTile(0, 0);
        tile.setValue(2);
        assertFalse(manager.isWon());
    }

    @Test
    public void testNotWinCantMove() {
        Game2048BoardManager manager = new Game2048BoardManager();
        Game2048Board board = manager.getBoard();
        setBoard(1024, board);
        assertFalse(manager.isWon());
    }

    @Test
    public void testMoveUp() {

    }

    @Test
    public void testMoveDown() {

    }

    @Test
    public void testMoveLeft() {

    }

    @Test
    public void testMoveRight() {

    }
}