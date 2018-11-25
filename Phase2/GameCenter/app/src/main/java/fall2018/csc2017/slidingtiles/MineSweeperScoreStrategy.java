package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/**
 * The Strategy to calculate the score of a mine sweeper game.
 */
class MineSweeperScoreStrategy implements ScoreStrategy, Serializable {
    @Override
    public int calculateScore(Object boardManager) {
        double time = ((MineSweeperManager) boardManager).getTime();
        return (int) time;
    }
}
