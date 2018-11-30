package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import static org.junit.Assert.*;

public class MemoryTest {
    private SlidingMemory slidingMemory;
    private Game2048Memory game2048Memory;
    private MineMemory mineMemory;
    private BoardManager manager;

    public void setUpSlidingMemory() {
        manager = new SlidingBoardManager(4);
        slidingMemory = new SlidingMemory();
    }

    public void setUpGame2048Memory() {
        manager = new Game2048BoardManager();
        game2048Memory = new Game2048Memory();
    }

    public void setUpMineMemory() {
        manager = new MineBoardManager(9, 10);
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