package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

public class Game2048ScoreStrategy implements ScoreStrategy, Serializable {
    Game2048ScoreStrategy() {
    }

    @Override
    public int calculateScore(Object o) {
        Board2048Manager boardManager = (Board2048Manager) o;
        //        for (Tile2048 tile: ((Board2048Manager)boardManager).getBoard()) {
//            score += tile.getValue();
//        }
        return boardManager.getScore();
    }
}
