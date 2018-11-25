package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

public class Game2048ScoreStrategy implements ScoreStrategy, Serializable {
    public Game2048ScoreStrategy() {
    }

    @Override
    public int calculateScore(Object o) {
        int score = 0;
        for (Tile2048 tile: ((Board2048Manager)o).getBoard()) {
            score += tile.getValue();
        }
        return score;
    }
}
