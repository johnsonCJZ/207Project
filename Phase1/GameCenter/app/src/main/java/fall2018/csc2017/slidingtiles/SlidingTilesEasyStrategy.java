package fall2018.csc2017.slidingtiles;

public class SlidingTilesEasyStrategy implements Strategy {

    @Override
    public int calculateScore(Object boardManager) {
        return Math.round(boardManager.step * 6.66);
    }
}
