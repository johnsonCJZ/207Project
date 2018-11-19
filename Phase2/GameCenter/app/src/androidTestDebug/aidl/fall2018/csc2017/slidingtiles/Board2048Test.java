package fall2018.csc2017.slidingtiles;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Board2048Test {
    private Tile2048[] oneNumberTileArray;

    @Before
    public void setUp() throws Exception {
        Tile2048[] oneNumberTileArray = new Tile2048[4];
        for (int i = 0; i < 4; i++){
            oneNumberTileArray[i] = new Tile2048();
        }
        oneNumberTileArray[1].setValue(3);
        this.oneNumberTileArray = oneNumberTileArray;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void moveEmptyTest() {
        Tile2048[] expected = new Tile2048[4];
        expected[0].setValue(3);
        Board2048.moveEmpty(oneNumberTileArray, "LEFT");
        Assert.assertEquals(expected, oneNumberTileArray);
    }
}