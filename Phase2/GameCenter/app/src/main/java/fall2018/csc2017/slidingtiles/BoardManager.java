package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class BoardManager<T, K> implements Serializable {
    private Double time = (double) 0;

    private List<K> tiles = new ArrayList<>();

    private int dimension;

    private T board;

    public BoardManager(){}

    public BoardManager(int n) {
        this.dimension = n;
    }

    public List<K> getTiles() {
        return tiles;
    }

    public void setTiles(List<K> tiles) {
        this.tiles = tiles;
    }

    /**
     * @return the time recorded
     */
    public Double getTime() {
        return time;
    }

    /**
     * Set the time.
     * @param time the time to be set.
     */
    public void setTime(Double time) {
        this.time = time;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getDimension() {
        return dimension;
    }

    public T getBoard() {
        return board;
    }

    public void setBoard(T board) {
        this.board = board;
    }

    /**
     * @return Return whether the player wins according to the game rule.
     */
    abstract boolean isWon();
    abstract boolean isLost();
    // Take in a position or a slide direction.
    abstract void move(Object o);

}
