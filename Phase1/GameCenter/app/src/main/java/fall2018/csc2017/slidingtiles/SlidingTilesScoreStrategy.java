package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

public class SlidingTilesScoreStrategy implements Strategy<BoardManager>, Serializable {

    @Override
    public int calculateScore(BoardManager boardManager) {
        double time = boardManager.getTime();
        int step = boardManager.getHistory().getSize();

        return (int) Math.round(10000/time + 10000/step);
        //should also consider time
    }
}
