package fall2018.csc2017.slidingtiles;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Board2048ManagerTest {

    private void setBoard(int largestTile, Board2048 board){
        int n = 2;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                Tile2048 tile = board.getTiles()[i][j];
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
        Board2048Manager manager = new Board2048Manager();
        Board2048 board = manager.getBoard();
        setBoard(1024, board);
        assertTrue(manager.isLose());
    }

    @Test
    public void testNotLoseHas2048CanMove() {
        Board2048Manager manager = new Board2048Manager();
        manager.cheat();
        assertFalse(manager.isLose());
    }

    @Test
    public void testNotLoseHas2048CantMove() {
        Board2048Manager manager = new Board2048Manager();
        Board2048 board = manager.getBoard();
        setBoard(2048, board);
        assertFalse(manager.isLose());
    }

    @Test
    public void testWinContains2048CantMove() {
        Board2048Manager manager = new Board2048Manager();
        Board2048 board = manager.getBoard();
        setBoard(2048, board);
        assertTrue(manager.isWon());
    }

    @Test
    public void testNotWinCanMove() {
        Board2048Manager manager = new Board2048Manager();
        Board2048 board = manager.getBoard();
        Tile2048 tile= board.getTile(0, 0);
        tile.setValue(2);
        assertFalse(manager.isWon());
    }

    @Test
    public void testNotWinCantMove() {
        Board2048Manager manager = new Board2048Manager();
        Board2048 board = manager.getBoard();
        setBoard(1024, board);
        assertFalse(manager.isWon());
    }
}