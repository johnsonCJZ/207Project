package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The sliding slidingTiles board.
 */
public class SlidingBoard extends Board<SlidingTile> implements Serializable, IObservable<SlidingBoard> {

    private boolean changed = false;

    private ArrayList<IObserver> observers = new ArrayList<>();

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    SlidingTile getTile(int row, int col) {
        return tiles.get(row * dimension + col);
    }

    /**
     * Swap the slidingTiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {
        SlidingTile temp1 = getTile(row1, col1);
        SlidingTile temp2 = getTile(row2, col2);
        tiles.set(row1 * dimension + col1, temp2);
        tiles.set(row2 * dimension + col2, temp1);

        setChanged();
        notifyObservers();
    }

    /**
     * Set the slidingTiles to slidingTiles.
     * @param slidingTiles the slidingTiles for the board
     */
    void setSlidingTiles(List<SlidingTile> slidingTiles) {
        this.tiles = slidingTiles;
    }

    @Override
    public void addObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void deleteObserver(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        if(changed){
            int i = observers.size();
            while(--i >= 0){
                observers.get(i).update(this);
            }

        }
    }

    @Override
    public void clearChanged() {
        changed = false;
    }

    @Override
    public boolean hasChanged() {
        return changed;
    }

    @Override
    public void setChanged() {
        changed = true;
    }

}




