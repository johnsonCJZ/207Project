package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard implements Serializable {
    private int scoreBoardSize = 10;
    private List<Object[]> scoreList;
    Strategy scoreStrategy;

    public ScoreBoard() {
        this.scoreList = new ArrayList<>(scoreBoardSize + 1);
    }

    public int calculateScore(BoardManager boardManager) {
        Strategy scoreStrategy = new SlidingTilesScoreStrategy();
        return scoreStrategy.calculateScore(boardManager);
    }

    public void addAndSort(Object[] scoreArray) {
        ScoreSorter scoreSorter = new ScoreSorter();

        scoreList.add(scoreArray);
        scoreSorter.sort(scoreList);
        if (scoreList.size() == scoreBoardSize + 1) {
            scoreList.remove(scoreBoardSize);
        }
    }

    public void setScoreBoardSize(int scoreBoardSize) {
        this.scoreBoardSize = scoreBoardSize;
    }

    public List<Object[]> getScoreList() {
        return scoreList;
    }

    public void setStrategy(Strategy s) {
        this.scoreStrategy = s;
    }

}


