package fall2018.csc2017.slidingtiles;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Memory2048 extends Memory {
    private double time;
    private List<Integer> tileValueList = new ArrayList<>();

    public void makeCopy(Game2048BoardManager manager) {
        this.time = manager.getTime();
        for (Game2048Tile t: manager.getBoard()){
            this.tileValueList.add(t.getValue());
        }
        System.out.println(this.tileValueList);
    }

    public Game2048BoardManager copy(){
        return new Game2048BoardManager(time, tileValueList);
    }

    public double getTime() {
        return time;
    }
}
