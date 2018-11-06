package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/**
 *
 */
public class SlidingTilesScoreStrategy implements Strategy<BoardManager>, Serializable {

    /**
     *
     * @param boardManager
     * @return
     */
    private int calStepScore(BoardManager boardManager) {
        int step = boardManager.getHistory().getSize()-1;

        return Math.round(100 - step/5);
    }

    /**
     *
     * @param boardManager
     * @return
     */
    private int calTimeScore(BoardManager boardManager) {
        double time = boardManager.getTime();

        return (int) Math.round((100 - time/20));

    }

    @Override
    /**
     *
     * @param boardManager
     * @return the score calculated
     */
    public int calculateScore(BoardManager boardManager) {
        int stepScore = calStepScore(boardManager);
        int timeScore = calTimeScore(boardManager);

        if (stepScore < 0) {stepScore = 0;}
        if (timeScore < 0) {timeScore = 0;}

        return  (stepScore + timeScore)/2;
    }
}
