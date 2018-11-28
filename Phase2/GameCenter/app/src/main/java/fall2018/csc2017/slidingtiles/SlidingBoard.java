package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The sliding slidingTiles board.
 */
public class SlidingBoard extends Observable implements Serializable {

    /**
     * The number of rows and columns.
     */
    private int dimension;

    /**
     * The slidingTiles on the board in row-major order.
     */
    private List<SlidingTile> slidingTiles;

//    public boolean changed;

//    private ArrayList<Observer> observers;

    SlidingBoard(){}

    /**
     * Set the slidingTiles to slidingTiles.
     * @param slidingTiles the slidingTiles for the board
     */
    void setSlidingTiles(List<SlidingTile> slidingTiles) {
        this.slidingTiles = slidingTiles;
    }

    /**
     * Set the number of rows and columns to int d.
     * @param d the number of rows/columns
     */
    void setDimension(int d) { dimension = d;}

    /**
     * Return the number of rows/columns the board.
     * @return the number of rows/columns on the board
     */
    int getDimension() {
        return dimension; // no need to check empty tile on board
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    SlidingTile getTile(int row, int col) {
        return slidingTiles.get(row * dimension + col);
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
        slidingTiles.set(row1 * dimension + col1, temp2);
        slidingTiles.set(row2 * dimension + col2, temp1);

        setChanged();
        notifyObservers();
    }

//    @Override
//    public String toString() {
//        return "SlidingBoard{" +
//                "slidingTiles=" + Arrays.toString(slidingTiles) +
//                '}';
//    }



//        @Override
//    public void subscribe(Observer o) {
//        observers.add(o);
//    }
//
//    @Override
//    public void unsubscribe(Observer o) {
//        observers.remove(o);
//    }
//
//    @Override
//    public ArrayList<Observer> getAllObservers() {
//        return observers;
//    }
//
//    @Override
//    public void notifyChanged(Object initiator) {
//
//    }
//
//    @Override
//    public void notifyChanged(Collection<Object> initiators) {
//
//    }
//
//    @Override
//    public void notifyChanged() {
//
//    }
//
//    @Override
//    public void set(SlidingTile[][] newValue, Collection<Object> initiators) {
//
//    }
//
//    @Override
//    public void set(SlidingTile[][] newValue) {
//
//    }
//
//    @Override
//    public void setObject(Object newValue, Collection<Object> initiators) {
//
//    }
//
//    @Override
//    public SlidingTile[][] get() {
//        return new SlidingTile[0][];
//    }
//
//    @Override
//    public boolean isNull() {
//        return false;
//    }

}




