package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Board<T> implements Serializable {
    int dimension;

    List<T> tiles = new ArrayList<>();

    public void setTiles(List<T> tiles) {
        this.tiles = tiles;
    }

    public List<T> getTiles(){
        return tiles;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
}

