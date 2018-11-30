package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game2048Memory extends Memory implements Serializable {
    private double time2048;
    private int score;
    private List<Integer> tileValueList = new ArrayList<>();

    public void makeCopy(Game2048BoardManager manager) {
        this.time2048 = manager.getTime();
        score = manager.getScore();
        for (Game2048Tile t: manager.getBoard()){
            this.tileValueList.add(t.getValue());
        }
        System.out.println(this.tileValueList);
    }

    public Game2048BoardManager copy(){
        ManagerFactory f = new ManagerFactory();
        return (Game2048BoardManager) f.Load2048Manager(time2048, score, tileValueList);
    }

}
