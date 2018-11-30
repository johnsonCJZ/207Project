package fall2018.csc2017.slidingtiles;

import java.util.ArrayList;
import java.util.List;

public abstract class Board {
    List<Tile> tiles = new ArrayList<>();

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public List<Tile> getTiles(){
        return tiles;
    }

}

