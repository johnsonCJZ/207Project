package fall2018.csc2017.slidingtiles;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {
    private int scoreBoardSize = 10;
    private List<Pair<String, Integer>> scoreList = new ArrayList<>(scoreBoardSize + 1);


    public void moveOnePositionBackward(int index) {
        for (int i = scoreBoardSize - 1; i >= index; i--) {
            scoreList.set(i, scoreList.get(i - 1));
        }
    }

    public int calculateScore(BoardManager boardManager){
        Strategy easyStrategy = new SlidingTilesEasyStrategy();
        return easyStrategy.calculateScore(boardManager);
    }

    public void addAndSort(Pair<String, Integer> pair) {
        ScoreSorter scoreSorter = new ScoreSorter();
        Integer score = (Integer) pair.second;

        scoreList.add(pair);
        scoreSorter.sort(scoreList);
        scoreList.set(10, null);
//        if (scoreList.size() == 0) {
//            scoreList.add(pair);
//        } else if (scoreList.size() < scoreBoardSize) {
//            scoreList.add(pair);
//            scoreSorter.sort(scoreList);
//            Collections.reverse(scoreList);
//        } else {
//            if (score >= scoreList.get(0).second) {
//                moveOnePositionBackward(0);
//                scoreList.set(0, pair);
//            } else if (score < scoreList.get(0).second &&
//                    score > scoreList.get(scoreBoardSize - 1).second) {
//                for (int i = 0; i < scoreBoardSize - 1; i++) {
//                    if (score >= scoreList.get(i + 1).second && score > scoreList.get(i).second) {
//                        moveOnePositionBackward(i + 1);
//                        scoreList.set(i + 1, pair);
    }

    public void setScoreBoardSize(int scoreBoardSize) {
        this.scoreBoardSize = scoreBoardSize;
    }

    public List<Pair<String, Integer>> getScoreList() {
        return scoreList;
    }
}


