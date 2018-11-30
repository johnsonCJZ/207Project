package fall2018.csc2017.slidingtiles;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ScoreBoardTest {
    ScoreBoard sb2048;
    ScoreBoard sbMine;
    ScoreBoard sbSlide;

    private Object[] createScore(int score){
        Object[] arr = new Object[2];
        arr[0] = "1";
        arr[1] = score;
        return arr;
    }

    public ArrayList getScore(List<Object[]> list) {
        ArrayList result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            result.add(list.get(i)[1]);
        }
        return result;
    }

    @Before
    public void setup(){
        sb2048 = new ScoreBoard("2048");
        sbMine = new ScoreBoard("Mine");
        sbSlide = new ScoreBoard("Slide");
    }
    @Test
    public void testCalculateScore() {
        setup();
        sb2048.setScoreBoardSize(10);
        sbMine.setScoreBoardSize(11);
        sbSlide.setScoreBoardSize(5);

    }

    @Test
    public void setScoreBoardSizeTest() {
        setup();
        sb2048.setScoreBoardSize(10);
        sbMine.setScoreBoardSize(11);
        sbSlide.setScoreBoardSize(5);

    }

    @Test
    public void testAddAndSort() {
        setup();
        sb2048.setScoreBoardSize(5);
        sb2048.addAndSort(createScore(1));
        ArrayList arr = new ArrayList(Arrays.asList(1));
        assertArrayEquals(getScore(sb2048.getScoreList()), arr);



    }

    @Test
    public void testGetScoreList() {
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}