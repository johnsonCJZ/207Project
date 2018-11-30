package fall2018.csc2017.slidingtiles;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import static org.junit.Assert.*;

public class SlidingTest {

    private SlidingBoardManager setUpBoard(int dimension, List<SlidingTile> tiles) {
        ManagerFactory factory = new ManagerFactory();
        SlidingBoard board = new BuilderBoard().setDimension(dimension).buildSlidingBoard();
        SlidingBoardManager manager = (SlidingBoardManager)factory.createNewManager(board);
        manager.setTiles(tiles);
        board.setDimension(dimension);
        return manager;
    }

    @Test
    public void testDimensionThreeNoInversion() {
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>(
        Arrays.asList(new SlidingTile(0), new SlidingTile(1),
                new SlidingTile(2), new SlidingTile(3),
                new SlidingTile(4), new SlidingTile(5),
                new SlidingTile(6), new SlidingTile(7), new SlidingTile(8)
        ));
        SlidingBoardManager testCase = setUpBoard(3, tilesToTest);
        assertEquals(0, testCase.findInversion());
        assertEquals(3, testCase.getBoard().getDimension());
    }

    @Test
    public void testOneInversion() {
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>(
        Arrays.asList(new SlidingTile(0), new SlidingTile(2),
                new SlidingTile(1), new SlidingTile(3),
                new SlidingTile(4), new SlidingTile(5),
                new SlidingTile(6), new SlidingTile(7), new SlidingTile(8)
        ));
        SlidingBoardManager testCase = setUpBoard(3, tilesToTest);
        assertEquals(1, testCase.findInversion());
    }

    @Test
    public void testNoInversionEndWithZero() {
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>(
        Arrays.asList(new SlidingTile(1), new SlidingTile(2),
                new SlidingTile(3), new SlidingTile(4),
                new SlidingTile(5), new SlidingTile(6),
                new SlidingTile(7), new SlidingTile(8), new SlidingTile(0)
        ));
        SlidingBoardManager testCase = setUpBoard(3, tilesToTest);
        assertEquals(0, testCase.findInversion());
    }

    @Test
    public void testOneInversionZeroInMiddle() {
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>(
        Arrays.asList(new SlidingTile(1), new SlidingTile(2),
                new SlidingTile(3), new SlidingTile(4),
                new SlidingTile(5), new SlidingTile(6),
                new SlidingTile(0), new SlidingTile(8), new SlidingTile(7)
        ));
        SlidingBoardManager testCase = setUpBoard(3, tilesToTest);
        assertEquals(1, testCase.findInversion());
    }

    @Test
    public void testMultipleInversions() {
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>(
        Arrays.asList(new SlidingTile(8), new SlidingTile(0),
                new SlidingTile(6), new SlidingTile(7),
                new SlidingTile(5), new SlidingTile(4),
                new SlidingTile(2), new SlidingTile(3), new SlidingTile(1)
        ));
        SlidingBoardManager testCase = setUpBoard(3, tilesToTest);
        assertEquals(26, testCase.findInversion());
    }

    @Test
    public void testDimensionFourInversions() {
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>(
        Arrays.asList(new SlidingTile(1), new SlidingTile(2),
                new SlidingTile(3), new SlidingTile(4),
                new SlidingTile(5), new SlidingTile(6),
                new SlidingTile(7), new SlidingTile(8),
                new SlidingTile(0), new SlidingTile(9),
                new SlidingTile(10), new SlidingTile(11),
                new SlidingTile(12), new SlidingTile(13),
                new SlidingTile(14), new SlidingTile(15)
        ));
        SlidingBoardManager testCase = setUpBoard(4, tilesToTest);
        assertEquals(0, testCase.findInversion());
        assertEquals(4, testCase.getBoard().getDimension());
    }

    @Test
    public void testDimensionFiveInversions() {
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>(
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
                new SlidingTile(24)
        ));
        SlidingBoardManager testCase = setUpBoard(5, tilesToTest);
        assertEquals(0, testCase.findInversion());
        assertEquals(5, testCase.getBoard().getDimension());
    }

    @Test
    public void testNotWonZeroAtBeginning(){
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>(
        Arrays.asList(new SlidingTile(0), new SlidingTile(1),
                new SlidingTile(2), new SlidingTile(3),
                new SlidingTile(4), new SlidingTile(5),
                new SlidingTile(6), new SlidingTile(7), new SlidingTile(8)
        ));
        SlidingBoardManager testCase = setUpBoard(3, tilesToTest);
        assertFalse(testCase.isWon());
    }

    @Test
    public void testNotWonZeroInMiddle(){
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>(
        Arrays.asList(new SlidingTile(1), new SlidingTile(0),
                new SlidingTile(2), new SlidingTile(3),
                new SlidingTile(4), new SlidingTile(5),
                new SlidingTile(6), new SlidingTile(7), new SlidingTile(8)
        ));
        SlidingBoardManager testCase = setUpBoard(3, tilesToTest);
        assertFalse(testCase.isWon());
    }

    @Test
    public void testWonZeroAtEnd(){
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>(
        Arrays.asList(new SlidingTile(1), new SlidingTile(2),
                new SlidingTile(3), new SlidingTile(4),
                new SlidingTile(5), new SlidingTile(6),
                new SlidingTile(7), new SlidingTile(8), new SlidingTile(0)
        ));
        SlidingBoardManager testCase = setUpBoard(3, tilesToTest);
        assertTrue(testCase.isWon());
    }

    @Test
    public void testNotWonOneInversion(){
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>(
        Arrays.asList(new SlidingTile(1), new SlidingTile(2),
                new SlidingTile(3), new SlidingTile(4),
                new SlidingTile(5), new SlidingTile(6),
                new SlidingTile(0), new SlidingTile(8), new SlidingTile(7)
        ));
        SlidingBoardManager testCase = setUpBoard(3, tilesToTest);
        assertFalse(testCase.isWon());
    }

    @Test
    public void testNotWonMoreInversions(){
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>(
        Arrays.asList(new SlidingTile(8), new SlidingTile(0),
                new SlidingTile(6), new SlidingTile(7),
                new SlidingTile(5), new SlidingTile(4),
                new SlidingTile(2), new SlidingTile(3), new SlidingTile(1)
        ));
        SlidingBoardManager testCase = setUpBoard(3, tilesToTest);
        assertFalse(testCase.isWon());
    }

    @Test
    public void testMoveCorner() {
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>(
        Arrays.asList(new SlidingTile(0), new SlidingTile(1),
                new SlidingTile(2), new SlidingTile(3),
                new SlidingTile(4), new SlidingTile(5),
                new SlidingTile(6), new SlidingTile(7), new SlidingTile(8)
        ));
        SlidingBoardManager testCase = setUpBoard(3, tilesToTest);
        testCase.move(1);
        List<SlidingTile> tiles = testCase.getTiles();
        int[] expected = {1, 0, 2, 3, 4, 5, 6, 7, 8};
        int[] actual = new int[tilesToTest.size()];
        for (int i = 0; i < tilesToTest.size(); i++) {
            actual[i] = tiles.get(i).getId();
        }
        assertArrayEquals(expected, actual);
        assertTrue(testCase.getBoard().hasChanged());
    }

    @Test
    public void testMoveSide() {
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>(
        Arrays.asList(new SlidingTile(1), new SlidingTile(0),
                new SlidingTile(2), new SlidingTile(3),
                new SlidingTile(4), new SlidingTile(5),
                new SlidingTile(6), new SlidingTile(7), new SlidingTile(8)
        ));
        SlidingBoardManager testCase = setUpBoard(3, tilesToTest);
        testCase.move(0);
        List<SlidingTile> tiles = testCase.getTiles();
        int[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] actual = new int[tilesToTest.size()];
        for (int i = 0; i < tilesToTest.size(); i++) {
            actual[i] = tiles.get(i).getId();
        }
        assertArrayEquals(expected, actual);
        assertTrue(testCase.getBoard().hasChanged());
    }

    @Test
    public void testMoveMiddle() {
        ArrayList<SlidingTile> tilesToTest = new ArrayList<>(
        Arrays.asList(new SlidingTile(1), new SlidingTile(2),
                new SlidingTile(3), new SlidingTile(4),
                new SlidingTile(0), new SlidingTile(5),
                new SlidingTile(6), new SlidingTile(7), new SlidingTile(8)
        ));
        SlidingBoardManager testCase = setUpBoard(3, tilesToTest);
        testCase.move(1);
        List<SlidingTile> tiles = testCase.getTiles();
        int[] expected = {1, 0, 3, 4, 2, 5, 6, 7, 8};
        int[] actual = new int[tilesToTest.size()];
        for (int i = 0; i < tilesToTest.size(); i++) {
            actual[i] = tiles.get(i).getId();
        }
        assertArrayEquals(expected, actual);
        assertTrue(testCase.getBoard().hasChanged());
    }

    @Test
    public void testCalculateSlidingScore() {

    }
}