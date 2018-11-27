package fall2018.csc2017.slidingtiles;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class SlidingMemory extends Memory {
    private int dimension;
    private double time;
    private String slidingTiles;
    private History history;

    public void makeCopy(SlidingBoardManager manager) {
        Gson gson1 = new Gson();
        this.dimension = manager.getSlidingBoard().getDimension();
        this.time = manager.getTime();
        Type type1 = new TypeToken<List<SlidingTile>>(){}.getType();
        this.slidingTiles  = gson1.toJson(manager.getSlidingBoard().getTiles(),type1);
        this.history=manager.getHistory();
    }

    public SlidingBoardManager copy(){
        Gson gson = new Gson();
        int dimentsion = this.dimension;
        double tile = this.time;
        String slidingTiles = this.slidingTiles;
        History history = this.history;
        Type type1 = new TypeToken<List<SlidingTile>>(){}.getType();
        List<SlidingTile> tiles = gson.fromJson(slidingTiles, type1);
        return new SlidingBoardManager(dimentsion,time, tiles, history);
    }


    public int getDimension() {
        return dimension;
    }

    @Override
    public double getTime() {
        return time;
    }

    public String getSlidingTiles() {
        return slidingTiles;
    }

    public History getHistory() {
        return history;
    }
}
