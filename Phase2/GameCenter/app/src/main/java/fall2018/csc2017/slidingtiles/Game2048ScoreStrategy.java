package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

public class Game2048ScoreStrategy implements ScoreStrategy, Serializable {
    Game2048ScoreStrategy() {
    }

    @Override
    public int calculateScore(Object o) {
        Board2048BoardManager boardManager = (Board2048BoardManager) o;
        //        for (Tile2048 tile: ((Board2048BoardManager)boardManager).getSlidingBoard()) {
//            score += tile.getValue();
//        }
        return boardManager.getScore();
    }
}
