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
    private List<Object[]> scoreList = scoreBoard.getScoreList();
    private Object[] originalScore = {"Lucia", 100};

//    private Object[] createScore(int score){
//        Object[] arr = new Object[2];
//        arr[0] = "1";
//        arr[1] = score;
//        return arr;
//    }
//
//    public ArrayList getScore(List<Object[]> list) {
//        ArrayList result = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++){
//            result.add(list.get(i)[1]);
//        }
//        return result;
//    }

//    @Before
//    public void setup(){
//        scoreBoard2048 = new ScoreBoard("2048");
//        scoreBoardMine = new ScoreBoard("Mine");
//        scoreBoardSlide = new ScoreBoard("Slide");
//    }

    @Before
    public void setup() {
        scoreList.add(originalScore);
    }

    private void test(List<Object[]> expected, Object[] newScore) {
        scoreBoard.addAndSort(newScore);
        for (int i = 0; i < expected.size(); i++) {
            int expectedScore = (int) expected.get(i)[1];
            int actualScore = (int) scoreList.get(i)[1];
            assertEquals(expectedScore, actualScore);
            String expectedName = (String) expected.get(i)[0];
            String actualName = (String) scoreList.get(i)[0];
            for (int j = 0; j < Math.min(expectedName.length(), actualName.length()); i++){
                assertEquals(expectedName.charAt(j), actualName.charAt(j));
            }
        }
    }

    @Test
    public void testAddAndSortRankedFirst() {
        List<Object[]> expected = new ArrayList<>();
        Object[] newScore =  {"Lucia", 102};
        expected.add(newScore);
        expected.add(originalScore);
        test(expected, newScore);
    }

    @Test
    public void testAddAndSortRankedLast() {
        List<Object[]> expected = new ArrayList<>();
        Object[] score =  {"Lucia", 102};
        Object[] newScore = {"Xinyi", 99};
        expected.add(score);
        expected.add(originalScore);
        expected.add(newScore);
        test(expected, newScore);
    }

    @Test
    public void testAddAndSortRankedMiddle() {
        List<Object[]> expected = new ArrayList<>();
        Object[] score1 =  {"Lucia", 102};
        Object[] score2 = {"Xinyi", 99};
        Object[] newScore = {"May", 101};
        expected.add(score1);
        expected.add(newScore);
        expected.add(originalScore);
        expected.add(score2);
        test(expected, newScore);
    }

    @Test
    public void testAddAndSortNoRank() {
        List<Object[]> expected = new ArrayList<>();
        Object[] score1 =  {"Lucia", 102};
        Object[] score2 = {"Xinyi", 99};
        Object[] Score3 = {"May", 101};
        expected.add(score1);
        expected.add(Score3);
        expected.add(originalScore);
        expected.add(score2);
        Object[] newScore = {"Xinyi Zhang", 70};
        scoreBoard.setScoreBoardSize(4);
        test(expected, newScore);
    }


//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }
}