package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MemoryTest {
    private Factory factory = new Factory();
    private SlidingMemory slidingMemory;
    private Game2048Memory game2048Memory;
    private MineMemory mineMemory;
    private SlidingBoardManager slidingBoardManager;
    private Game2048BoardManager game2048BoardManager;
    private MineBoardManager mineBoardManager;

    public void setUpSlidingManager() {
        slidingMemory = new SlidingMemory();
        SlidingBoard board = new BuilderBoard().setDimension(4).buildSlidingBoard();
        slidingBoardManager = (SlidingBoardManager) factory.createNewManager(board);
    }

    public void setUpGame2048Manager() {
        game2048Memory = new Game2048Memory();
        Game2048Board board = new BuilderBoard().build2048Board();
        game2048BoardManager = (Game2048BoardManager) factory.createNewManager(board);
        game2048BoardManager.setTime(6.6);
    }

    public void setUpMineManager() {
        mineMemory = new MineMemory();
        MineBoard board = new BuilderBoard().setMine(10).setMineLeft(10).setDimension(9).setMineTiles().buildMineBoard();
        mineBoardManager = (MineBoardManager) factory.createNewManager(board);
    }

    @Test
    public void testSlidingMemoryMakeCopy() {
        setUpSlidingManager();
        slidingMemory.makeCopy(slidingBoardManager);
        assertEquals(slidingMemory.getDimension(), slidingBoardManager.getDimension());
        assertEquals(slidingMemory.getTimeTmp(), slidingBoardManager.getTime(), 0.0);
        for (int i = 0; i < slidingBoardManager.getTiles().size(); i++) {
            assertEquals((int) slidingMemory.getSlidingTiles().get(i), slidingBoardManager.getTiles().get(i).getId());
        }
    }

    @Test
    public void testSlidingMemoryCopy() {
        setUpSlidingManager();
        slidingMemory.makeCopy(slidingBoardManager);
        SlidingBoardManager managerCopy = slidingMemory.copy();
        assertEquals(managerCopy.getDimension(), slidingMemory.getDimension());
        assertEquals(managerCopy.getTime(), slidingMemory.getTimeTmp(), 0.0);
        for (int i = 0; i < managerCopy.getTiles().size(); i++) {
            assertEquals((int) slidingMemory.getSlidingTiles().get(i), managerCopy.getTiles().get(i).getId());
        }
    }

    @Test
    public void testGame2048MemoryMakeCopy() {
        setUpGame2048Manager();
        game2048Memory.makeCopy(game2048BoardManager);
        assertEquals(game2048Memory.getScore(), game2048BoardManager.getScore());
        assertEquals(game2048Memory.getTime2048(), game2048BoardManager.getTime(), 0.0);
        for (int i = 0; i < game2048Memory.getTileValueList().size(); i++) {
            assertEquals((int) game2048Memory.getTileValueList().get(i), game2048BoardManager.getTiles().get(i).getValue());
        }
    }

    @Test
    public void testGame2048MemoryCopy() {
        setUpGame2048Manager();
        game2048Memory.makeCopy(game2048BoardManager);
        Game2048BoardManager managerCopy = game2048Memory.copy();
        assertEquals(game2048Memory.getScore(), managerCopy.getScore());
        assertEquals(game2048Memory.getTime2048(), managerCopy.getTime(), 0.0);
        for (int i = 0; i < managerCopy.getTiles().size(); i++) {
            assertEquals(managerCopy.getTiles().get(i).getValue(), (int) game2048Memory.getTileValueList().get(i));
        }
    }

    @Test
    public void testMineMemoryMakeCopy() {
        setUpMineManager();
        mineMemory.makeCopy(mineBoardManager);
        assertEquals(mineMemory.getDimension(), mineBoardManager.getDimension());
        assertEquals(mineMemory.getMine(), mineBoardManager.getBoard().getMineNum());
        assertEquals(mineMemory.getMineLeft(), mineBoardManager.getBoard().getMineLeft());
        for (int i = 0; i < mineMemory.getIsFlaggedOfTiles().size(); i++) {
            assertEquals(mineMemory.getIsFlaggedOfTiles().get(i), mineBoardManager.getTiles().get(i).isFlagged());
            assertEquals(mineMemory.getIsMineOfTiles().get(i), mineBoardManager.getTiles().get(i).isMine());
            assertEquals(mineMemory.getIsObscuredOfTiles().get(i), mineBoardManager.getTiles().get(i).isObscured());
            assertEquals(mineMemory.getNumberOfTiles().get(i), mineBoardManager.getTiles().get(i).getNumber(), 0.0);
        }
    }

    @Test
    public void testMineMemoryCopy() {
        setUpMineManager();
        mineMemory.makeCopy(mineBoardManager);
        MineBoardManager managerCopy = mineMemory.copy();
        assertEquals(mineMemory.getDimension(), managerCopy.getDimension());
        assertEquals(mineMemory.getMine(), managerCopy.getBoard().getMineNum());
        assertEquals(mineMemory.getMineLeft(), managerCopy.getBoard().getMineLeft());
        for (int i = 0; i < managerCopy.getTiles().size(); i++) {
            assertEquals(managerCopy.getTiles().get(i).isFlagged(), mineMemory.getIsFlaggedOfTiles().get(i));
            assertEquals(managerCopy.getTiles().get(i).isMine(), mineMemory.getIsMineOfTiles().get(i));
            assertEquals(managerCopy.getTiles().get(i).isObscured(), mineMemory.getIsObscuredOfTiles().get(i));
            assertEquals(managerCopy.getTiles().get(i).getNumber(), mineMemory.getNumberOfTiles().get(i), 0.0);
        }
    }

}