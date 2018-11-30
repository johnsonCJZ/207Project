package fall2018.csc2017.slidingtiles;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MineTest {

    private MineBoard board;
    private MineBoardManager manager;
    private List<MineTile> tiles;
    private MineTile tile;

    @Before
    public void setUp() {
        Factory f = new Factory();
        board = new BuilderBoard().setMine(5).setMineLeft(5).setDimension(5).setMineTiles().buildMineBoard();
        manager = (MineBoardManager) f.createNewManager(board);
    }

    @Test
    public void testGetSurround() {
        setUp();
        assertEquals(3, board.getSurround(0).size());
        assertEquals(8, board.getSurround(12).size());
        for (MineTile tile : board.getSurround(12)) {
            int tileRow = tile.getPosition() / board.getDimension();
            int tileCol = tile.getPosition() % board.getDimension();
            int row = 12 / board.getDimension();
            int col = 12 % board.getDimension();
            assertTrue(tileRow - row <= 1 && tileRow - row >= -1 && tileCol - col <= 1 && tileCol - col >= -1);
        }
    }

    @Test
    public void testFirstTouchMove() {
        setUp();
        manager.move(12);
        assertEquals(board.getMineNum(), 5);
        assertEquals(board.getMinePosition().size(), 5);
        assertEquals(board.getTile(12).getNumber(), 0);
        for (MineTile tile : board.getSurround(12)) {
            assertFalse(tile.isMine());
        }
        for (MineTile tile : board.getMinePosition()) {
            assertTrue(tile.isMine());
        }
    }

    @Test
    public void testFlag() {
        setUp();
        manager.mark(12);
        assertEquals(board.getMineLeft(), board.getMineNum() - 1);
        manager.mark(12);
        assertEquals(board.getMineLeft(), board.getMineNum());
    }

    @Test
    public void testTapOnMine() {
        setUp();
        manager.move(12);
        manager.move(board.getMinePosition().get(0).getPosition());
        assertEquals(manager.isLost(), true);
        assertEquals(board.getMinePosition().get(0).getBackground(), R.drawable.mine_b);
        assertEquals(board.getMinePosition().get(1).getBackground(), R.drawable.mine_b2);
    }

    @Test
    public void testMineNumOfTiles() {
        setUp();
        manager.move(12);
        for (MineTile tile : manager.getTiles()) {
            if (tile.isMine()) {
                assertEquals(tile.getNumber(), -1);
            } else {
                int mineNum = 0;
                for (MineTile surroundTile : board.getSurround(tile.getPosition())) {
                    if (surroundTile.isMine()) {
                        mineNum++;
                    }
                }
                assertEquals(tile.getNumber(), mineNum);
            }
        }
    }

    @Test
    public void testIsWon() {
        setUp();
        manager.move(12);
        for (MineTile tile : manager.getTiles()) {
            if (!tile.isMine()) {
                board.reveal(tile.getPosition());
            }
        }
        assertTrue(manager.isWon());
    }
}