package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/**
 * The Strategy to calculate the score of a mine sweeper game.
 */
class MineSweeperScoreStrategy implements ScoreStrategy<MineSweeperManager>, Serializable {
    @Override
    public int calculateScore(MineSweeperManager boardManager) {
        double time = boardManager.getTime();
        return (int) time;
    }
}
