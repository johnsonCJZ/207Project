package fall2018.csc2017.slidingtiles;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MineSweeperTileTest {

    private MineSweeperTile tile;

    @Before
    public void setUp() {
        tile = new MineSweeperTile();
        tile.setBackground();
    }

    @Test
    public void testGetBackground() {
        setUp();
        assertEquals(tile.getBackground(), R.drawable.mine_mask);
        tile.reveal();
        assertEquals(tile.getBackground(), R.drawable.mine_base);
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
    public void testSetMine() {
        setUp();
        tile.setMine();
        tile.reveal();
        assertEquals(-1, tile.getNumber());
        assertEquals(R.drawable.mine_b, tile.getBackground());
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
    public void testSetNumber() {
        setUp();
        tile.setNumber(1);
        assertEquals(1, tile.getNumber());
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
    public void getNumber() {
        setUp();
        assertEquals(0, tile.getNumber());
    }

    @Test
    public void getPosition() {
        setUp();
        tile.setPosition(1);
        assertEquals(1, tile.getPosition());
    }

    @Test
    public void setPosition() {
        setUp();
        tile.setPosition(0);
        assertEquals(0, tile.getPosition());
    }
}