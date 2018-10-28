package fall2018.csc2017.slidingtiles;

public class SlidingTilesHardStrategy implements Strategy {
    @Override
    public int calculateScore() {
        return steps * 7.77 - undosteps ;
    }
}
