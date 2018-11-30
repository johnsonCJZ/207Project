package fall2018.csc2017.slidingtiles;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SlidingMemory extends Memory<SlidingBoardManager> {
    private int dimension;
    private double timeTmp;
    private List<Integer> slidingTiles = new ArrayList<>();

    @Override
    public void makeCopy(SlidingBoardManager manager) {
        this.dimension = manager.getBoard().getDimension();
        this.timeTmp = manager.getTime();
        for (SlidingTile tile : manager.getTiles()) {
            this.slidingTiles.add(tile.getId());
        }

    }

    @Override
    public SlidingBoardManager copy(){

        int dimension = this.dimension;
        double time = this.timeTmp;
        List<SlidingTile> tt = new ArrayList<>();
        for (int i : this.slidingTiles){
            tt.add(new SlidingTile(i));
        }
        Factory f = new Factory();
        return (SlidingBoardManager) f.loadSlidingManager(dimension,time, tt);
    }

    int getDimension() {return dimension;}

    double getTimeTmp() {return timeTmp;}

    List<Integer> getSlidingTiles() {return slidingTiles;}

}
