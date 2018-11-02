package fall2018.csc2017.slidingtiles;

import android.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard implements Serializable {
    private int scoreBoardSize = 10;
    private List<Pair<String, Integer>> scoreList = new ArrayList<>(scoreBoardSize + 1);
    Strategy scoreStrategy;

    public int calculateScore(BoardManager boardManager){

        return scoreStrategy.calculateScore(boardManager);
    }

    public void addAndSort(Pair<String, Integer> pair) {
        ScoreSorter scoreSorter = new ScoreSorter();
        Integer score = (Integer) pair.second;

        scoreList.add(pair);
        scoreSorter.sort(scoreList);
    }

    public void setScoreBoardSize(int scoreBoardSize) {
        this.scoreBoardSize = scoreBoardSize;
    }

    public List<Pair<String, Integer>> getScoreList() {
        return scoreList;
    }

    public void setStrategy(Strategy s){
        this.scoreStrategy=s;
    }
}


