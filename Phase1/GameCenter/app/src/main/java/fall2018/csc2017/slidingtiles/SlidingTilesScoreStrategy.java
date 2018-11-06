package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/**
 * The Strategy to calculate the score of a sliding tiles game.
 */
public class SlidingTilesScoreStrategy implements Strategy<BoardManager>, Serializable {

    /**
     * Calculate the score that is related to steps used to solve a board.
     * @param boardManager the boardManager to calculate score for
     * @return the score related to steps
     */
    private int calStepScore(BoardManager boardManager) {
        int step = boardManager.getHistory().getSize()-1;

        return Math.round(100 - step/5);
    }

    /**
     * Calculate the score that is related to time taken solve a board.
     * @param boardManager the boardManager to calculate score for
     * @return the score related to time
     */
    private int calTimeScore(BoardManager boardManager) {
        double time = boardManager.getTime();

        return (int) Math.round((100 - time/15));

    }

    @Override
    public int calculateScore(BoardManager boardManager) {
        int stepScore = calStepScore(boardManager);
        int timeScore = calTimeScore(boardManager);

        if (stepScore < 0) {stepScore = 0;}
        if (timeScore < 0) {timeScore = 0;}

        return  (stepScore + timeScore)/2;
    }
}
