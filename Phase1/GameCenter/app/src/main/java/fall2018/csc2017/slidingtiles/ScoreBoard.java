package fall2018.csc2017.slidingtiles;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreBoard {
    private List<Pair<String,Integer>> scoreList = new ArrayList<>();
    private int scoreBoardSize = 10;

    public void moveOnePositionBackward(int index) {
        for (int i = scoreBoardSize - 1; i >= index; i--) {
            scoreList.set(i, scoreList.get(i - 1));
        }
    }

    public static int calculateScore(Board board){
        
    }

    public void addAndSort(int score) {
        if (scoreList.size() == 0) {
            scoreList.add(new Pair(personalBoardName, score));
        } else if (scoreList.size() < scoreBoardSize) {
            scoreList.add(new Pair(personalBoardName,score);
            Collections.sort(scoreList);
            Collections.reverse(scoreList);
        } else {
            if (score >= scoreList.get(0)) {
                moveOnePositionBackward(0);
                scoreList.set(0, score);
            } else if (score < scoreList.get(0) &&
                    score > scoreList.get(scoreBoardSize - 1)) {
                for (int i = 0; i < scoreBoardSize - 1; i++) {
                    if (score <= i + 1 && score > i) {
                        moveOnePositionBackward(i + 1);
                        scoreList.set(i + 1, score);
                    }
                }
            }
        }
    }

    public void setScoreBoardSize(int scoreBoardSize) {
        this.scoreBoardSize = scoreBoardSize;
    }
}


