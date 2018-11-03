package fall2018.csc2017.slidingtiles;

public class SlidingTilesScoreStrategy implements Strategy {

    @Override
    public int calculateScore(BoardManager boardManager, double time) {
        int step = boardManager.getHistory().getSize();
        return step * 6;
        //should also consider time
    }
}
