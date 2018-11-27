package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/**
 * The Strategy to calculate the score of a mine sweeper game.
 */
class MineSweeperScoreStrategy implements ScoreStrategy, Serializable {
    @Override
    public int calculateScore(Object boardManager) {
        MineSweeperBoard board =  ((MineSweeperBoardManager) boardManager).getBoard();
        int boardScore=0;
        if (((MineSweeperBoardManager)boardManager).isWon()){
            boardScore = board.getH()*board.getW();
        }
        else{
            for (MineSweeperTile t : board.getMinePosition()){
                if (t.isFlagged()){
                    boardScore++;
                }
            }
        }
        double time = ((MineSweeperBoardManager)boardManager).getTime();
        int timeScore = 10*(int)(1/time);
        return timeScore+boardScore;
    }
}
