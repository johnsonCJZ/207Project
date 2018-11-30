package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import static org.junit.Assert.*;

public class MemoryTest {
    private SlidingMemory slidingMemory;
    private Game2048Memory game2048Memory;
    private MineMemory mineMemory;
    private BoardManager manager;

    public void setUpSlidingMemory() {
        ManagerFactory f = new ManagerFactory();
        SlidingBoard b =new BuilderBoard()
                .setDimension(4)
                .buildSlidingBoard();
        manager = f.createNewManager(b);
        slidingMemory = new SlidingMemory();
    }

    public void setUpGame2048Memory() {
        ManagerFactory f = new ManagerFactory();
        Game2048Board board = new BuilderBoard().build2048Board();
        manager = f.createNewManager(board);
        game2048Memory = new Game2048Memory();
    }

    public void setUpMineMemory() {

        ManagerFactory f = new ManagerFactory();
        MineBoard b = new BuilderBoard()
                .setMine(10)
                .setMineLeft(10)
                .setDimension(9)
                .setMineTiles()
                .buildMineBoard();
        manager = (MineBoardManager) f.createNewManager(b);
        mineMemory = new MineMemory();
    }

    @Test
    public void testSlidingMemoryMakeCopy() {
        setUpSlidingMemory();
        slidingMemory.makeCopy((SlidingBoardManager) manager);
        assertEquals(slidingMemory.getDimension(), manager.getDimension());
    }

    @Test
    public void testSlidingMemoryCopy() {
    }

    @Test
    public void testGame2048MemoryMakeCopy() {
    }

    @Test
    public void testGame2048MemoryCopy() {
    }

    @Test
    public void testMineMemoryMakeCopy() {
    }

    @Test
    public void testMineMemoryCopy() {
    }
}