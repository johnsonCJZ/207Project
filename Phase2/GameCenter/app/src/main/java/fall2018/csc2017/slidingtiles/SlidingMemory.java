package fall2018.csc2017.slidingtiles;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SlidingMemory extends Memory {
    private int dimension;
    private double timeTmp;
    private List<Integer> slidingTiles = new ArrayList<>();
//    private History history;

    public void makeCopy(SlidingBoardManager manager) {
        Gson gson1 = new Gson();
        this.dimension = manager.getSlidingBoard().getDimension();
        this.timeTmp = manager.getTime();
//        Type type1 = new TypeToken<List<SlidingTile>>(){}.getType();
//        this.slidingTiles  = gson1.toJson(manager.getSlidingTiles(),type1);
//        System.out.println(this.slidingTiles);
        for (SlidingTile t: manager.getSlidingTiles()){
            this.slidingTiles.add(t.getId());
        }
        System.out.println(this.slidingTiles);
//        this.history=manager.getHistory();
    }

    public SlidingBoardManager copy(){
        Gson gson = new Gson();
        int dimension = this.dimension;
        double time = this.timeTmp;
//        History history = this.history;
//        Type type1 = new TypeToken<List<SlidingTile>>(){}.getType();
//        List<SlidingTile> tiles = gson.fromJson(slidingTiles, type1);
        System.out.println(this.slidingTiles);
        List<SlidingTile> tt = new ArrayList<>();
        for (int i : this.slidingTiles){
            tt.add(new SlidingTile(i));
        }
        return new SlidingBoardManager(dimension,time, tt);
    }


    public int getDimension() {
        return dimension;
    }

    public double getTimeTmp() {
        return timeTmp;
    }
//
//    public List<SlidingTile> getSlidingTiles() {
//        return slidingTiles;
//    }

//    public History getHistory() {
//        return history;
//    }
}
