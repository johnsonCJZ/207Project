package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/**
 * The Strategy to calculate the score of a sliding slidingTiles game.
 */
public class SlidingTilesScoreStrategy implements ScoreStrategy, Serializable {
    public SlidingTilesScoreStrategy() {
    }

    /**
     * Calculate the score that is related to steps used to solve a board.
     * @param slidingBoardManager the slidingBoardManager to calculate score for
     * @return the score related to steps
     */
    private int calStepScore(SlidingBoardBoardManager slidingBoardManager) {
        int step = slidingBoardManager.getHistory().getSize()-1;

        return Math.round(100 - step/5);
    }

    /**
     * Calculate the score that is related to time taken solve a board.
     * @param slidingBoardManager the slidingBoardManager to calculate score for
     * @return the score related to time
     */
    private int calTimeScore(SlidingBoardBoardManager slidingBoardManager) {
        double time = slidingBoardManager.getTime();

        return (int) Math.round((100 - time/15));

    }

    /**
     * Calculate score of game
     * @param boardManager the boardManager to calculate score for
     * @return the score with respect to both time and steps
     */
    @Override
    public int calculateScore(Object boardManager) {
        int stepScore = calStepScore((SlidingBoardBoardManager) boardManager);
        int timeScore = calTimeScore((SlidingBoardBoardManager) boardManager);

        if (stepScore < 0) {stepScore = 0;}
        if (timeScore < 0) {timeScore = 0;}

        return  (stepScore + timeScore)/2;
    }
}
