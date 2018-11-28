package fall2018.csc2017.slidingtiles;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import static org.junit.Assert.*;

public class SlidingTileTest {

    @Before
    public SlidingBoardManager setUpBoard(int dimension, double time, List<SlidingTile> tiles) {
        SlidingBoardManager manager = new SlidingBoardManager(dimension, time, tiles);
        SlidingBoard board = manager.getSlidingBoard();
        board.setSlidingTiles(tiles);
        return manager;
    }

    @Test
    public void testNoInversion() {
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>();
        Arrays.asList(new SlidingTile(0), new SlidingTile(1),
                new SlidingTile(2), new SlidingTile(3),
                new SlidingTile(4), new SlidingTile(5),
                new SlidingTile(6), new SlidingTile(7), new SlidingTile(8)
        );
        SlidingBoardManager testCase = setUpBoard(3, 2.0, tilesToTest);
        assertEquals(0, testCase.findInversion());
    }

    @Test
    public void testOneInversion() {
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>();
        Arrays.asList(new SlidingTile(0), new SlidingTile(2),
                new SlidingTile(1), new SlidingTile(3),
                new SlidingTile(4), new SlidingTile(5),
                new SlidingTile(6), new SlidingTile(7), new SlidingTile(8)
        );
        SlidingBoardManager testCase = setUpBoard(3, 2.0, tilesToTest);
        assertEquals(1, testCase.findInversion());
    }

    @Test
    public void testNoInversionEndWithZero() {
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>();
        Arrays.asList(new SlidingTile(1), new SlidingTile(2),
                new SlidingTile(3), new SlidingTile(4),
                new SlidingTile(5), new SlidingTile(6),
                new SlidingTile(7), new SlidingTile(8), new SlidingTile(0)
        );
        SlidingBoardManager testCase = setUpBoard(3, 2.0, tilesToTest);
        assertEquals(0, testCase.findInversion());
    }

    @Test
    public void testOneInversionZeroInMiddle() {
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>();
        Arrays.asList(new SlidingTile(1), new SlidingTile(2),
                new SlidingTile(3), new SlidingTile(4),
                new SlidingTile(5), new SlidingTile(6),
                new SlidingTile(0), new SlidingTile(8), new SlidingTile(7)
        );
        SlidingBoardManager testCase = setUpBoard(3, 2.0, tilesToTest);
        assertEquals(1, testCase.findInversion());
    }

    @Test
    public void testMultipleInversions() {
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>();
        Arrays.asList(new SlidingTile(8), new SlidingTile(0),
                new SlidingTile(6), new SlidingTile(7),
                new SlidingTile(5), new SlidingTile(4),
                new SlidingTile(2), new SlidingTile(3), new SlidingTile(1)
        );
        SlidingBoardManager testCase = setUpBoard(3, 2.0, tilesToTest);
        assertEquals(5, testCase.findInversion());
    }

    @Test
    public void testDimensionFourInversions() {
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>();
        Arrays.asList(new SlidingTile(1), new SlidingTile(2),
                new SlidingTile(3), new SlidingTile(4),
                new SlidingTile(5), new SlidingTile(6),
                new SlidingTile(7), new SlidingTile(8),
                new SlidingTile(0), new SlidingTile(9),
                new SlidingTile(10), new SlidingTile(11),
                new SlidingTile(12), new SlidingTile(13),
                new SlidingTile(14), new SlidingTile(15));
        SlidingBoardManager testCase = setUpBoard(4, 2.0, tilesToTest);
        assertEquals(0, testCase.findInversion());
    }

    @Test
    public void testDimensionFiveInversions() {
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>();
        Arrays.asList(new SlidingTile(1), new SlidingTile(2),
                new SlidingTile(3), new SlidingTile(4),
                new SlidingTile(5), new SlidingTile(6),
                new SlidingTile(7), new SlidingTile(8),
                new SlidingTile(0), new SlidingTile(9),
                new SlidingTile(10), new SlidingTile(11),
                new SlidingTile(12), new SlidingTile(13),
                new SlidingTile(14), new SlidingTile(15),
                new SlidingTile(16), new SlidingTile(17),
                new SlidingTile(18), new SlidingTile(19),
                new SlidingTile(20), new SlidingTile(21),
                new SlidingTile(22), new SlidingTile(23),
                new SlidingTile(24));
        SlidingBoardManager testCase = setUpBoard(5, 2.0, tilesToTest);
        assertEquals(0, testCase.findInversion());
    }

    @Test
    public void testMoreTimeInversions() {
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>();
        Arrays.asList(new SlidingTile(8), new SlidingTile(0),
                new SlidingTile(6), new SlidingTile(7),
                new SlidingTile(5), new SlidingTile(4),
                new SlidingTile(2), new SlidingTile(3), new SlidingTile(1)
        );
        SlidingBoardManager testCase = setUpBoard(3, 4.0, tilesToTest);
        assertEquals(5, testCase.findInversion());
    }

    @Test
    public void isSolvable(){

    }

    @Test
    public void testWonZeroAtBeginning(){
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>();
        Arrays.asList(new SlidingTile(0), new SlidingTile(1),
                new SlidingTile(2), new SlidingTile(3),
                new SlidingTile(4), new SlidingTile(5),
                new SlidingTile(6), new SlidingTile(7), new SlidingTile(8)
        );
        SlidingBoardManager testCase = setUpBoard(3, 2.0, tilesToTest);
        assertTrue(testCase.isWon());
    }

    @Test
    public void testWonZeroInMiddle(){
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>();
        Arrays.asList(new SlidingTile(1), new SlidingTile(0),
                new SlidingTile(2), new SlidingTile(3),
                new SlidingTile(4), new SlidingTile(5),
                new SlidingTile(6), new SlidingTile(7), new SlidingTile(8)
        );
        SlidingBoardManager testCase = setUpBoard(3, 2.0, tilesToTest);
        assertTrue(testCase.isWon());
    }

    @Test
    public void testWonZeroAtEnd(){
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>();
        Arrays.asList(new SlidingTile(1), new SlidingTile(2),
                new SlidingTile(3), new SlidingTile(4),
                new SlidingTile(5), new SlidingTile(6),
                new SlidingTile(7), new SlidingTile(8), new SlidingTile(0)
        );
        SlidingBoardManager testCase = setUpBoard(3, 2.0, tilesToTest);
        assertTrue(testCase.isWon());
    }

    @Test
    public void testNotWonOneInversion(){
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>();
        Arrays.asList(new SlidingTile(1), new SlidingTile(2),
                new SlidingTile(3), new SlidingTile(4),
                new SlidingTile(5), new SlidingTile(6),
                new SlidingTile(0), new SlidingTile(8), new SlidingTile(7)
        );
        SlidingBoardManager testCase = setUpBoard(3, 2.0, tilesToTest);
        assertFalse(testCase.isWon());
    }

    @Test
    public void testNotWonMoreInversions(){
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>();
        Arrays.asList(new SlidingTile(8), new SlidingTile(0),
                new SlidingTile(6), new SlidingTile(7),
                new SlidingTile(5), new SlidingTile(4),
                new SlidingTile(2), new SlidingTile(3), new SlidingTile(1)
        );
        SlidingBoardManager testCase = setUpBoard(3, 2.0, tilesToTest);
        assertFalse(testCase.isWon());
    }

    @Test
    public void touchMove(){

    }

}