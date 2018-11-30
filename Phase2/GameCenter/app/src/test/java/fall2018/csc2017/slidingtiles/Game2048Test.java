package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Game2048Test {

    private Game2048BoardManager manager = new Game2048BoardManager();
    private Game2048Board board = manager.getBoard();

    private void setBoardCannotMove(int largestTile){
        board.setUpTiles();
        int n = 2;
        List<Game2048Tile> tiles = board.getTiles();
        for (Game2048Tile tile: tiles){
            tile.setValue(n);
            if(n < largestTile){
                n = n * 2;
            }
            else{
                n = 2;
            }
        }
    }

    private void setBoardCanMove(){
        board.setUpTiles();
        List<Game2048Tile> tiles = board.getTiles();
        for (Game2048Tile tile: tiles){
            tile.setValue(2);
        }
    }

    private int[] tileToInt(Game2048Tile[] line) {
        int[] intLine = new int[4];
        for (int i = 0; i < 4; i++) {
            intLine[i] = line[i].getValue();
        }
        return intLine;
    }

    private Game2048Tile[] intToTile(int[] intLine) {
        Game2048Tile tile1 = new Game2048Tile();
        tile1.setValue(intLine[0]);
        Game2048Tile tile2 = new Game2048Tile();
        tile2.setValue(intLine[1]);
        Game2048Tile tile3 = new Game2048Tile();
        tile3.setValue(intLine[2]);
        Game2048Tile tile4 = new Game2048Tile();
        tile4.setValue(intLine[3]);
        return new Game2048Tile[]{
                tile1, tile2, tile3, tile4
        };
    }


    @Test
    public void testLose() {
        setBoardCannotMove(1024);
        assertTrue(manager.isLost());
    }

    @Test
    public void testNotLoseHas2048CanMove() {
        board.setUpTiles();
        manager.cheat();
        assertTrue(manager.canMove());
        assertFalse(manager.isLost());
    }

    @Test
    public void testNotLoseHas2048CantMove() {
        setBoardCannotMove(2048);
        assertFalse(manager.isLost());
    }

    @Test
    public void testNotLoseFullBoard() {
        setBoardCanMove();
        assertFalse(manager.isLost());
    }

    @Test
    public void testWinContains2048CantMove() {
        setBoardCannotMove(2048);
        assertTrue(manager.isWon());
    }

    @Test
    public void testNotWinNewBoard(){
        board.setUpTiles();
        board.addTile();
        assertTrue(manager.canMove());
        assertFalse(manager.isWon());
    }

    @Test
    public void testNotWinCanMove() {
        board.setUpTiles();
        Game2048Tile tile= board.getTile(0, 0);
        tile.setValue(2);
        assertTrue(manager.canMove());
        assertFalse(manager.isWon());
    }

    @Test
    public void testNotWinCantMove() {
        setBoardCannotMove(1024);
        assertFalse(manager.isWon());
    }

    @Test
    public void simpleSlideRight() {
        int[] expected = {0, 0, 2, 4};
        int[] before = {2, 4, 0, 0};
        Game2048Tile[] actual = intToTile(before);
        board.mergeList(actual, "RIGHT");
        assertArrayEquals(expected, tileToInt(actual));
    }

    @Test
    public void simpleMergeRight() {
        int[] expected = {0, 0, 0, 4};
        int[] before = {0, 0, 2, 2};
        Game2048Tile[] actual = intToTile(before);
        board.mergeList(actual, "RIGHT");
        assertArrayEquals(expected, tileToInt(actual));
    }

    @Test
    public void slideAndMergeRight() {
        int[] expected = {0, 0, 0, 8};
        int[] before = {4, 4, 0, 0};
        Game2048Tile[] actual = intToTile(before);
        board.mergeList(actual, "RIGHT");
        assertArrayEquals(expected, tileToInt(actual));
    }

    @Test
    public void doNothingRight() {
        int[] expected = {0, 0, 8, 16};
        int[] before = {0, 0, 8, 16};
        Game2048Tile[] actual = intToTile(before);
        board.mergeList(actual, "RIGHT");
        assertArrayEquals(expected, tileToInt(actual));
    }

    @Test
    public void moreTilesRight() {
        int[] expected = {0, 0, 4, 4};
        int[] before = {2, 0, 2, 4};
        Game2048Tile[] actual = intToTile(before);
        board.mergeList(actual, "RIGHT");
        assertArrayEquals(expected, tileToInt(actual));
    }

    @Test
    public void largerNumbersRight() {
        int[] expected = {0, 0, 2048, 512};
        int[] before = {1024, 0, 1024, 512};
        Game2048Tile[] actual = intToTile(before);
        board.mergeList(actual, "RIGHT");
        for (int i = 0; i < 4; i++) {
            assertEquals(expected[i], tileToInt(actual)[i]);
        }
    }

    @Test
    public void noZeroesRight() {
        int[] expected = {0, 512, 2048, 512};
        int[] before = {256, 256, 2048, 512};
        Game2048Tile[] actual = intToTile(before);
        board.mergeList(actual, "RIGHT");
        assertArrayEquals(expected, tileToInt(actual));
    }

    @Test
    public void moreNumbersTwoMergesRight() {
        int[] expected = {0, 0, 128, 256};
        int[] before = {64, 64, 128, 128};
        Game2048Tile[] actual = intToTile(before);
        board.mergeList(actual, "RIGHT");
        assertArrayEquals(expected, tileToInt(actual));
    }

    @Test
    public void simpleSlideLeft() {
        int[] expected = {2, 4, 0, 0};
        int[] before = {0, 0, 2, 4};
        Game2048Tile[] actual = intToTile(before);
        board.mergeList(actual, "LEFT");
        assertArrayEquals(expected, tileToInt(actual));
    }

    @Test
    public void simpleMergeLeft() {
        int[] expected = {4, 0, 0, 0};
        int[] before = {2, 2, 0, 0};
        Game2048Tile[] actual = intToTile(before);
        board.mergeList(actual, "LEFT");
        assertArrayEquals(expected, tileToInt(actual));
    }

    @Test
    public void slideAndMergeLeft() {
        int[] expected = {8, 0, 0, 0};
        int[] before = {0, 4, 0, 4};
        Game2048Tile[] actual = intToTile(before);
        board.mergeList(actual, "LEFT");
        assertArrayEquals(expected, tileToInt(actual));
    }

    @Test
    public void doNothingLeft() {
        int[] expected = {1024, 2, 0, 0};
        int[] before = {1024, 2, 0, 0};
        Game2048Tile[] actual = intToTile(before);
        board.mergeList(actual, "LEFT");
        assertArrayEquals(expected, tileToInt(actual));
    }

    @Test
    public void moreTilesLeft() {
        int[] expected = {4, 4, 0, 0};
        int[] before = {4, 2, 2, 0};
        Game2048Tile[] actual = intToTile(before);
        board.mergeList(actual, "LEFT");
        assertArrayEquals(expected, tileToInt(actual));
    }

    @Test
    public void largerNumbersLeft() {
        int[] expected = {512, 2048, 0, 0};
        int[] before = {512, 1024, 0, 1024};
        Game2048Tile[] actual = intToTile(before);
        board.mergeList(actual, "LEFT");
        assertArrayEquals(expected, tileToInt(actual));
    }

    @Test
    public void noZeroesLeft() {
        int[] expected = {512, 2048, 512, 0};
        int[] before = {512, 2048, 256, 256};
        Game2048Tile[] actual = intToTile(before);
        board.mergeList(actual, "LEFT");
        assertArrayEquals(expected, tileToInt(actual));
    }

    @Test
    public void moreNumbersTwoMergesLeft() {
        int[] expected = {16, 32, 0, 0};
        int[] before = {8, 8, 16, 16};
        Game2048Tile[] actual = intToTile(before);
        board.mergeList(actual, "LEFT");
        assertArrayEquals(expected, tileToInt(actual));
    }
}