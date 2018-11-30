package fall2018.csc2017.slidingtiles;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;

import static org.junit.Assert.*;

public class ScoreBoardTest {
    private ScoreBoard scoreBoard = new ScoreBoard("tester");

    @Test
    public void testAddAndSortRankedFirst() {
        Object[] originalScore = {"Lucia", 100};
        Object[] newScore =  {"Lucia", 102};
        List<Object[]> expected = new ArrayList<>(
                Arrays.asList(newScore, originalScore)
        );
        List<Object[]> actual = new ArrayList<>();
        actual.add(originalScore);
        scoreBoard.setScoreList(actual);
        scoreBoard.addAndSort(newScore);
        for (int i = 0; i < expected.size(); i++) {
            int expectedScore = (int) expected.get(i)[1];
            int actualScore = (int) scoreBoard.getScoreList().get(i)[1];
            assertEquals(expectedScore, actualScore);
            String expectedName = (String) expected.get(i)[0];
            String actualName = (String) scoreBoard.getScoreList().get(i)[0];
            for (int j = 0; j < Math.min(expectedName.length(), actualName.length()); i++){
                assertEquals(expectedName.charAt(j), actualName.charAt(j));
            }
        }
    }

    @Test
    public void testAddAndSortRankedLast() {
        Object[] originalScore = {"Lucia", 100};
        Object[] score =  {"Lucia", 102};
        Object[] newScore = {"Xinyi", 99};
        List<Object[]> actual = new ArrayList<>(
                Arrays.asList(score, originalScore)
        );
        List<Object[]> expected = new ArrayList<>(
                Arrays.asList(score, originalScore, newScore)
        );
        scoreBoard.setScoreList(actual);
        scoreBoard.addAndSort(newScore);
        for (int i = 0; i < expected.size(); i++) {
            int expectedScore = (int) expected.get(i)[1];
            int actualScore = (int) scoreBoard.getScoreList().get(i)[1];
            assertEquals(expectedScore, actualScore);
            String expectedName = (String) expected.get(i)[0];
            String actualName = (String) scoreBoard.getScoreList().get(i)[0];
            for (int j = 0; j < Math.min(expectedName.length(), actualName.length()); i++){
                assertEquals(expectedName.charAt(j), actualName.charAt(j));
            }
        }
    }

    @Test
    public void testAddAndSortRankedMiddle() {
        Object[] originalScore = {"Lucia", 100};
        Object[] score1 =  {"Lucia", 102};
        Object[] score2 = {"Xinyi", 99};
        Object[] newScore = {"May", 101};
        List<Object[]> actual = new ArrayList<>(
                Arrays.asList(score1, originalScore, score2)
        );
        List<Object[]> expected = new ArrayList<>(
                Arrays.asList(score1, newScore, originalScore, score2)
        );
        scoreBoard.setScoreList(actual);
        scoreBoard.addAndSort(newScore);
        for (int i = 0; i < expected.size(); i++) {
            int expectedScore = (int) expected.get(i)[1];
            int actualScore = (int) scoreBoard.getScoreList().get(i)[1];
            assertEquals(expectedScore, actualScore);
            String expectedName = (String) expected.get(i)[0];
            String actualName = (String) scoreBoard.getScoreList().get(i)[0];
            for (int j = 0; j < Math.min(expectedName.length(), actualName.length()); i++){
                assertEquals(expectedName.charAt(j), actualName.charAt(j));
            }
        }
    }

    @Test
    public void testAddAndSortNoRank() {
        Object[] originalScore = {"Lucia", 100};
        Object[] score1 =  {"Lucia", 102};
        Object[] score2 = {"Xinyi", 99};
        Object[] score3 = {"May", 101};
        Object[] newScore = {"Xinyi Zhang", 70};
        List<Object[]> actual = new ArrayList<>(
                Arrays.asList(score1, score3, originalScore, score2)
        );
        List<Object[]> expected = new ArrayList<>(
                Arrays.asList(score1, score3, originalScore, score2)
        );
        scoreBoard.setScoreList(actual);
        scoreBoard.setScoreBoardSize(4);
        scoreBoard.addAndSort(newScore);
        for (int i = 0; i < expected.size(); i++) {
            int expectedScore = (int) expected.get(i)[1];
            int actualScore = (int) scoreBoard.getScoreList().get(i)[1];
            assertEquals(expectedScore, actualScore);
            String expectedName = (String) expected.get(i)[0];
            String actualName = (String) scoreBoard.getScoreList().get(i)[0];
            for (int j = 0; j < Math.min(expectedName.length(), actualName.length()); i++){
                assertEquals(expectedName.charAt(j), actualName.charAt(j));
            }
        }
    }
}