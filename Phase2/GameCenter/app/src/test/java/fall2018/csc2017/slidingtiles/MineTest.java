package fall2018.csc2017.slidingtiles;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MineTest {

    private MineBoard board;
    private List<MineTile> tiles = new ArrayList<>();
    private MineTile tile = new MineTile();

    @Before
    public void setUp() {
        board = new MineBoard();
        board.setMineNum(4);
        board.setDimension(9);
        board.setTiles(tiles);
    }

    @Test
    public void testGetSurround() {
        setUp();
        assertEquals(3,board.getSurround(0).size());
        assertEquals(8,board.getSurround(15).size());
        for (MineTile tile: board.getSurround(15)) {
            int tileRow = tile.getPosition()/board.getDimension();
            int tileCol = tile.getPosition()%board.getDimension();
            int row = 15/board.getDimension();
            int col = 15%board.getDimension();
            assertTrue(tileRow - row <= 1 && tileRow - row >= -1 && tileCol - col <= 1 && tileCol - col >= -1);
        }
    }


    @Test
    public void testShowMine() {
        setUp();
        tile.setMine();
        tile.showMine();
        assertEquals(tile.getBackground(), R.drawable.mine_b2);
        tile.reveal();
        tile.showMine();
        assertEquals(tile.getBackground(), R.drawable.mine_b);
    }

    @Test
    public void testFlag() {
        setUp();
        assertFalse(tile.isFlagged());
        tile.flag();
        assertTrue(tile.isFlagged());
    }

    @Test
    public void reveal() {
        setUp();
        assertTrue(tile.isObscured());
        tile.reveal();
        assertFalse(tile.isObscured());
    }

    @Test
    public void testIsMine() {
        setUp();
        assertFalse(tile.isMine());
    }

    @Test
    public void testIsFlagged() {
        setUp();
        assertFalse(tile.isFlagged());
    }

    @Test
    public void testIsObscured() {
        setUp();
        assertTrue(tile.isObscured());
    }


    @Test
    public void touchMove() {
    }

    @Test
    public void mark() {
    }

    @Test
    public void isLost() {
    }

    @Test
    public void isWon() {
    }
}