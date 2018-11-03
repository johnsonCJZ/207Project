package fall2018.csc2017.slidingtiles;

import android.util.Pair;

import java.io.Serializable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard implements Serializable {
    private int scoreBoardSize = 10;
    private List<Object[]> scoreList = new ArrayList<>(scoreBoardSize + 1);

    public int calculateScore(BoardManager boardManager, double time){
        Strategy scoreStrategy = new SlidingTilesScoreStrategy();
        return scoreStrategy.calculateScore(boardManager, time);
    }

    public void addAndSort(Object[] scoreArray) {
        ScoreSorter scoreSorter = new ScoreSorter();

        scoreList.add(scoreArray);
        scoreSorter.sort(scoreList);
        scoreList.set(10, null);
    }

    public void setScoreBoardSize(int scoreBoardSize) {
        this.scoreBoardSize = scoreBoardSize;
    }

    public List<Object[]> getScoreList() {
        return scoreList;
    }
}


