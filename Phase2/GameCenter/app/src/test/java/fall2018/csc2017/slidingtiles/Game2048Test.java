package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import static org.junit.Assert.*;

public class Game2048Test {

    private int[] lineToInt(Game2048Tile[] line) {
        int[] intLine = new int[4];
        for (int i = 0; i < 4; i++) {
            intLine[i] = line[i].getValue();
        }
        return intLine;
    }

    @Test
    public void simpleSlideRight() {
        int[] expected = {0, 0, 2, 4};
        Game2048Tile tile1 = new Game2048Tile();
        tile1.setValue(2);
        Game2048Tile tile2 = new Game2048Tile();
        tile2.setValue(4);
        Game2048Tile tile3 = new Game2048Tile();
        tile3.setValue(0);
        Game2048Tile tile4 = new Game2048Tile();
        tile4.setValue(0);
        Game2048Tile[] before = {
                tile1, tile2, tile3, tile4
        };
        Game2048Board board = new Game2048Board();
        board.mergeList(before, "RIGHT");
        assertEquals(expected, lineToInt(before));
    }

    @Test
    public void simpleMergeRight() {
        int[] expected = {0, 0, 0, 4};
        Game2048Tile tile1 = new Game2048Tile();
        tile1.setValue(0);
        Game2048Tile tile2 = new Game2048Tile();
        tile2.setValue(0);
        Game2048Tile tile3 = new Game2048Tile();
        tile3.setValue(2);
        Game2048Tile tile4 = new Game2048Tile();
        tile4.setValue(2);
        Game2048Tile[] before = {
                tile1, tile2, tile3, tile4
        };
        Game2048Board board = new Game2048Board();
        board.mergeList(before, "RIGHT");
        assertEquals(expected, lineToInt(before));
    }

    @Test
    public void slideAndMergeRight() {
        int[] expected = {0, 0, 0, 8};
        Game2048Tile tile1 = new Game2048Tile();
        tile1.setValue(4);
        Game2048Tile tile2 = new Game2048Tile();
        tile2.setValue(4);
        Game2048Tile tile3 = new Game2048Tile();
        tile3.setValue(0);
        Game2048Tile tile4 = new Game2048Tile();
        tile4.setValue(0);
        Game2048Tile[] before = {
                tile1, tile2, tile3, tile4
        };
        Game2048Board board = new Game2048Board();
        board.mergeList(before, "RIGHT");
        assertEquals(expected, lineToInt(before));

    }

    @Test
    public void doNothingRight() {
        int[] expected = {0, 0, 2, 4};
        Game2048Tile tile1 = new Game2048Tile();
        tile1.setValue(0);
        Game2048Tile tile2 = new Game2048Tile();
        tile2.setValue(0);
        Game2048Tile tile3 = new Game2048Tile();
        tile3.setValue(2);
        Game2048Tile tile4 = new Game2048Tile();
        tile4.setValue(4);
        Game2048Tile[] before = {
                tile1, tile2, tile3, tile4
        };
        Game2048Board board = new Game2048Board();
        board.mergeList(before, "RIGHT");
        assertEquals(expected, lineToInt(before));
    }

    @Test
    public void moreTilesRight() {
        int[] expected = {0, 0, 4, 4};
        Game2048Tile tile1 = new Game2048Tile();
        tile1.setValue(0);
        Game2048Tile tile2 = new Game2048Tile();
        tile2.setValue(0);
        Game2048Tile tile3 = new Game2048Tile();
        tile3.setValue(2);
        Game2048Tile tile4 = new Game2048Tile();
        tile4.setValue(4);
        Game2048Tile[] before = {
                tile1, tile2, tile3, tile4
        };
        Game2048Board board = new Game2048Board();
        board.mergeList(before, "RIGHT");
        assertEquals(expected, lineToInt(before));
    }

    @Test
    public void largerNumbersRight() {
        int[] expected = {0, 0, 2048, 512};
        Game2048Tile tile1 = new Game2048Tile();
        tile1.setValue(1024);
        Game2048Tile tile2 = new Game2048Tile();
        tile2.setValue(0);
        Game2048Tile tile3 = new Game2048Tile();
        tile3.setValue(1024);
        Game2048Tile tile4 = new Game2048Tile();
        tile4.setValue(512);
        Game2048Tile[] before = {
                tile1, tile2, tile3, tile4
        };
        Game2048Board board = new Game2048Board();
        board.mergeList(before, "RIGHT");
        assertEquals(expected, lineToInt(before));
    }

    @Test
    public void noZeroesRight() {
        int[] expected = {0, 512, 2048, 512};
        Game2048Tile tile1 = new Game2048Tile();
        tile1.setValue(256);
        Game2048Tile tile2 = new Game2048Tile();
        tile2.setValue(256);
        Game2048Tile tile3 = new Game2048Tile();
        tile3.setValue(2048);
        Game2048Tile tile4 = new Game2048Tile();
        tile4.setValue(512);
        Game2048Tile[] before = {
                tile1, tile2, tile3, tile4
        };
        Game2048Board board = new Game2048Board();
        board.mergeList(before, "RIGHT");
        assertEquals(expected, lineToInt(before));
    }

    @Test
    public void moreNumbersTwoMergesRight() {
        int[] expected = {0, 0, 128, 256};
        Game2048Tile tile1 = new Game2048Tile();
        tile1.setValue(64);
        Game2048Tile tile2 = new Game2048Tile();
        tile2.setValue(64);
        Game2048Tile tile3 = new Game2048Tile();
        tile3.setValue(128);
        Game2048Tile tile4 = new Game2048Tile();
        tile4.setValue(128);
        Game2048Tile[] before = {
                tile1, tile2, tile3, tile4
        };
        Game2048Board board = new Game2048Board();
        board.mergeList(before, "RIGHT");
        assertEquals(expected, lineToInt(before));
    }

    @Test
    public void simpleSlideLeft() {
        int[] expected = {2, 4, 0, 0};
        Game2048Tile tile1 = new Game2048Tile();
        tile1.setValue(0);
        Game2048Tile tile2 = new Game2048Tile();
        tile2.setValue(0);
        Game2048Tile tile3 = new Game2048Tile();
        tile3.setValue(2);
        Game2048Tile tile4 = new Game2048Tile();
        tile4.setValue(4);
        Game2048Tile[] before = {
                tile1, tile2, tile3, tile4
        };
        Game2048Board board = new Game2048Board();
        board.mergeList(before, "LEFT");
        assertEquals(expected, lineToInt(before));
    }

    @Test
    public void simpleMergeLeft() {
        int[] expected = {4, 0, 0, 0};
        Game2048Tile tile1 = new Game2048Tile();
        tile1.setValue(2);
        Game2048Tile tile2 = new Game2048Tile();
        tile2.setValue(2);
        Game2048Tile tile3 = new Game2048Tile();
        tile3.setValue(0);
        Game2048Tile tile4 = new Game2048Tile();
        tile4.setValue(0);
        Game2048Tile[] before = {
                tile1, tile2, tile3, tile4
        };
        Game2048Board board = new Game2048Board();
        board.mergeList(before, "LEFT");
        assertEquals(expected, lineToInt(before));
    }

    @Test
    public void slideAndMergeLeft() {
        int[] expected = {8, 0, 0, 0};
        Game2048Tile tile1 = new Game2048Tile();
        tile1.setValue(0);
        Game2048Tile tile2 = new Game2048Tile();
        tile2.setValue(4);
        Game2048Tile tile3 = new Game2048Tile();
        tile3.setValue(0);
        Game2048Tile tile4 = new Game2048Tile();
        tile4.setValue(4);
        Game2048Tile[] before = {
                tile1, tile2, tile3, tile4
        };
        Game2048Board board = new Game2048Board();
        board.mergeList(before, "LEFT");
        assertEquals(expected, lineToInt(before));

    }

    @Test
    public void doNothingLeft() {
        int[] expected = {1024, 2, 0, 0};
        Game2048Tile tile1 = new Game2048Tile();
        tile1.setValue(1024);
        Game2048Tile tile2 = new Game2048Tile();
        tile2.setValue(2);
        Game2048Tile tile3 = new Game2048Tile();
        tile3.setValue(0);
        Game2048Tile tile4 = new Game2048Tile();
        tile4.setValue(0);
        Game2048Tile[] before = {
                tile1, tile2, tile3, tile4
        };
        Game2048Board board = new Game2048Board();
        board.mergeList(before, "LEFT");
        assertEquals(expected, lineToInt(before));
    }

    @Test
    public void moreTilesLeft() {
        int[] expected = {4, 4, 0, 0};
        Game2048Tile tile1 = new Game2048Tile();
        tile1.setValue(4);
        Game2048Tile tile2 = new Game2048Tile();
        tile2.setValue(2);
        Game2048Tile tile3 = new Game2048Tile();
        tile3.setValue(2);
        Game2048Tile tile4 = new Game2048Tile();
        tile4.setValue(0);
        Game2048Tile[] before = {
                tile1, tile2, tile3, tile4
        };
        Game2048Board board = new Game2048Board();
        board.mergeList(before, "LEFT");
        assertEquals(expected, lineToInt(before));
    }

    @Test
    public void largerNumbersLeft() {
        int[] expected = {512, 2048, 0, 0};
        Game2048Tile tile1 = new Game2048Tile();
        tile1.setValue(512);
        Game2048Tile tile2 = new Game2048Tile();
        tile2.setValue(1024);
        Game2048Tile tile3 = new Game2048Tile();
        tile3.setValue(0);
        Game2048Tile tile4 = new Game2048Tile();
        tile4.setValue(1024);
        Game2048Tile[] before = {
                tile1, tile2, tile3, tile4
        };
        Game2048Board board = new Game2048Board();
        board.mergeList(before, "LEFT");
        assertEquals(expected, lineToInt(before));
    }

    @Test
    public void noZeroesLeft() {
        int[] expected = {512, 2048, 512, 0};
        Game2048Tile tile1 = new Game2048Tile();
        tile1.setValue(512);
        Game2048Tile tile2 = new Game2048Tile();
        tile2.setValue(2048);
        Game2048Tile tile3 = new Game2048Tile();
        tile3.setValue(256);
        Game2048Tile tile4 = new Game2048Tile();
        tile4.setValue(256);
        Game2048Tile[] before = {
                tile1, tile2, tile3, tile4
        };
        Game2048Board board = new Game2048Board();
        board.mergeList(before, "LEFT");
        assertEquals(expected, lineToInt(before));
    }

    @Test
    public void moreNumbersTwoMergesLeft() {
        int[] expected = {16, 32, 0, 0};
        Game2048Tile tile1 = new Game2048Tile();
        tile1.setValue(8);
        Game2048Tile tile2 = new Game2048Tile();
        tile2.setValue(8);
        Game2048Tile tile3 = new Game2048Tile();
        tile3.setValue(16);
        Game2048Tile tile4 = new Game2048Tile();
        tile4.setValue(16);
        Game2048Tile[] before = {
                tile1, tile2, tile3, tile4
        };
        Game2048Board board = new Game2048Board();
        board.mergeList(before, "LEFT");
        assertEquals(expected, lineToInt(before));
    }
}