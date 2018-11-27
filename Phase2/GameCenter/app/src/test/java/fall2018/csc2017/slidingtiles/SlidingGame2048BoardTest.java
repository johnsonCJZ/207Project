package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import static org.junit.Assert.*;

public class Board2048Test {

    @Test
    public void simpleSlideRight() {
        int[] expected = {0, 0, 2, 4};
        Tile2048[] before = {
                new Tile2048(2), new Tile2048(4), new Tile2048(0), new Tile2048(0),
        };
        Board2048 board = new Board2048();
        board.mergeList(before, "RIGHT");
        Converter conv = new Converter();
        assertEquals(expected, conv.lineToInt(before));
    }

    @Test
    public void simpleMergeRight() {
        int[] expected = {0, 0, 0, 4};
        Tile2048[] before = {
                new Tile2048(0), new Tile2048(0), new Tile2048(2), new Tile2048(2),
        };
        Board2048 board = new Board2048();
        board.mergeList(before, "RIGHT");
        Converter conv = new Converter();
        assertEquals(expected, conv.lineToInt(before));
    }

    @Test
    public void slideAndMergeRight() {
        int[] expected = {0, 0, 0, 8};
        Tile2048[] before = {
                new Tile2048(4), new Tile2048(0), new Tile2048(4), new Tile2048(0),
        };
        Board2048 board = new Board2048();
        board.mergeList(before, "RIGHT");
        Converter conv = new Converter();
        assertEquals(expected, conv.lineToInt(before));

    }

    @Test
    public void doNothingRight() {
        int[] expected = {0, 0, 2, 4};
        Tile2048[] before = {
                new Tile2048(0), new Tile2048(0), new Tile2048(2), new Tile2048(4),
        };
        Board2048 board = new Board2048();
        board.mergeList(before, "RIGHT");
        Converter conv = new Converter();
        assertEquals(expected, conv.lineToInt(before));
    }

    @Test
    public void moreTilesRight() {
        int[] expected = {0, 0, 4, 4};
        Tile2048[] before = {
                new Tile2048(0), new Tile2048(2), new Tile2048(2), new Tile2048(4),
        };
        Board2048 board = new Board2048();
        board.mergeList(before, "RIGHT");
        Converter conv = new Converter();
        assertEquals(expected, conv.lineToInt(before));
    }

    @Test
    public void largerNumbersRight() {
        int[] expected = {0, 0, 2048, 512};
        Tile2048[] before = {
                new Tile2048(1024), new Tile2048(0), new Tile2048(1024), new Tile2048(512),
        };
        Board2048 board = new Board2048();
        board.mergeList(before, "RIGHT");
        Converter conv = new Converter();
        assertEquals(expected, conv.lineToInt(before));
    }

    @Test
    public void noZeroesRight() {
        int[] expected = {0, 512, 2048, 512};
        Tile2048[] before = {
                new Tile2048(256), new Tile2048(256), new Tile2048(2048), new Tile2048(512),
        };
        Board2048 board = new Board2048();
        board.mergeList(before, "RIGHT");
        Converter conv = new Converter();
        assertEquals(expected, conv.lineToInt(before));
    }

    @Test
    public void simpleSlideLeft() {
        int[] expected = {2, 4, 0, 0};
        Tile2048[] before = {
                new Tile2048(0), new Tile2048(0), new Tile2048(2), new Tile2048(4),
        };
        Board2048 board = new Board2048();
        board.mergeList(before, "LEFT");
        Converter conv = new Converter();
        assertEquals(expected, conv.lineToInt(before));
    }

    @Test
    public void simpleMergeLeft() {
        int[] expected = {4, 0, 0, 0};
        Tile2048[] before = {
                new Tile2048(2), new Tile2048(2), new Tile2048(0), new Tile2048(0),
        };
        Board2048 board = new Board2048();
        board.mergeList(before, "LEFT");
        Converter conv = new Converter();
        assertEquals(expected, conv.lineToInt(before));
    }

    @Test
    public void slideAndMergeLeft() {
        int[] expected = {8, 0, 0, 0};
        Tile2048[] before = {
                new Tile2048(0), new Tile2048(4), new Tile2048(0), new Tile2048(4),
        };
        Board2048 board = new Board2048();
        board.mergeList(before, "LEFT");
        Converter conv = new Converter();
        assertEquals(expected, conv.lineToInt(before));

    }

    @Test
    public void doNothingLeft() {
        int[] expected = {1024, 2, 0, 0};
        Tile2048[] before = {
                new Tile2048(1024), new Tile2048(2), new Tile2048(0), new Tile2048(0),
        };
        Board2048 board = new Board2048();
        board.mergeList(before, "LEFT");
        Converter conv = new Converter();
        assertEquals(expected, conv.lineToInt(before));
    }

    @Test
    public void moreTilesLeft() {
        int[] expected = {4, 2, 0, 0};
        Tile2048[] before = {
                new Tile2048(4), new Tile2048(2), new Tile2048(2), new Tile2048(0),
        };
        Board2048 board = new Board2048();
        board.mergeList(before, "LEFT");
        Converter conv = new Converter();
        assertEquals(expected, conv.lineToInt(before));
    }

    @Test
    public void largerNumbersLeft() {
        int[] expected = {512, 2048, 0, 0};
        Tile2048[] before = {
                new Tile2048(512), new Tile2048(1024), new Tile2048(0), new Tile2048(1024),
        };
        Board2048 board = new Board2048();
        board.mergeList(before, "LEFT");
        Converter conv = new Converter();
        assertEquals(expected, conv.lineToInt(before));
    }

    @Test
    public void noZeroesLeft() {
        int[] expected = {512, 2048, 512, 0};
        Tile2048[] before = {
                new Tile2048(512), new Tile2048(2048), new Tile2048(256), new Tile2048(256),
        };
        Board2048 board = new Board2048();
        board.mergeList(before, "LEFT");
        Converter conv = new Converter();
        assertEquals(expected, conv.lineToInt(before));
    }

    @Test
    public void moreNumbersTwoMergesLeft() {
        int[] expected = {16, 32, 0, 0};
        Tile2048[] before = {
                new Tile2048(8), new Tile2048(8), new Tile2048(16), new Tile2048(16),
        };
        Board2048 board = new Board2048();
        board.mergeList(before, "LEFT");
        Converter conv = new Converter();
        assertEquals(expected, conv.lineToInt(before));
    }

    @Test
    public void moreNumbersTwoMergesRight() {
        int[] expected = {0, 0, 128, 256};
        Tile2048[] before = {
                new Tile2048(64), new Tile2048(64), new Tile2048(128), new Tile2048(128),
        };
        Board2048 board = new Board2048();
        board.mergeList(before, "LEFT");
        Converter conv = new Converter();
        assertEquals(expected, conv.lineToInt(before));
    }
}