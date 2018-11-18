package fall2018.csc2017.slidingtiles;

public class Game2048ScoreStrategy implements ScoreStrategy<Board2048Manager> {
    @Override
    public int calculateScore(Board2048Manager o) {
        int score = 0;
        for (Tile2048 tile: o.getBoard()) {
            score += tile.getValue();
        }
        return score;
    }
}
