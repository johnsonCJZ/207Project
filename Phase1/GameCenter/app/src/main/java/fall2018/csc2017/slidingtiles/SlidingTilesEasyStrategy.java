package fall2018.csc2017.slidingtiles;

public class SlidingTilesEasyStrategy implements Strategy {

    @Override
    public int calculateScore() {
        return Math.round(step * 6.66);
    }
}
