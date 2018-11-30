package fall2018.csc2017.slidingtiles;

import java.util.ArrayList;
import java.util.List;

public class SlidingMemory extends Memory<SlidingBoardManager> {

    /**
     * The dimension of the SlidingBoard cloned.
     */
    private int dimension;

    /**
     * The time of the SlidingBoard cloned.
     */
    private double timeSliding;
    private List<Integer> slidingTiles = new ArrayList<>();

    @Override
    public void makeCopy(SlidingBoardManager manager) {
        this.dimension = manager.getBoard().getDimension();
        this.timeSliding = manager.getTime();
        for (SlidingTile tile : manager.getTiles()) {
            this.slidingTiles.add(tile.getId());
        }
    }

    @Override
    public SlidingBoardManager copy(){

        int dimension = this.dimension;
        double time = this.timeSliding;
        List<SlidingTile> tt = new ArrayList<>();
        for (int i : this.slidingTiles){
            tt.add(new SlidingTile(i));
        }
        Factory f = new Factory();
        return (SlidingBoardManager) f.loadSlidingManager(dimension,time, tt);
    }

    int getDimension() {return dimension;}

    double getTimeTmp() {return timeSliding;}

    List<Integer> getSlidingTiles() {return slidingTiles;}

}
