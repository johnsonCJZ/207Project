package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class ScoreBoard implements Serializable {
    private int scoreBoardSize = 10;
    private List<Object[]> scoreList;
    private SlidingTilesScoreStrategy scoreStrategy = new SlidingTilesScoreStrategy();

    ScoreBoard() {
        this.scoreList = new ArrayList<>(scoreBoardSize + 1);
    }

    int calculateScore(BoardManager boardManager) {
        return scoreStrategy.calculateScore(boardManager);
    }

    void addAndSort(Object[] scoreArray) {
        ScoreSorter scoreSorter = new ScoreSorter();

        scoreList.add(scoreArray);
        scoreSorter.sort(scoreList);
        if (scoreList.size() == scoreBoardSize + 1) {
            scoreList.remove(scoreBoardSize);
        }
    }

    List<Object[]> getScoreList() {
        return scoreList;
    }

}


