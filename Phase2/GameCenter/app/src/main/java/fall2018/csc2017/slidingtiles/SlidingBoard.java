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
public class SlidingBoard extends Observable implements Serializable, Iterable<SlidingTile>{

    /**
     * The number of rows and columns.
     */
    private int dimension;

    /**
     * The slidingTiles on the board in row-major order.
     */
    private SlidingTile[][] slidingTiles;

    SlidingBoard(){}

    /**
     * A new empty board of n*n slidingTiles.
     * @param n the number of rows and columns of the board
     */
    SlidingBoard(int n) {
        this.dimension = n;
        slidingTiles = new SlidingTile[dimension][dimension];
    }

    /**
     * Set the slidingTiles to slidingTiles.
     * @param slidingTiles the slidingTiles for the board
     */
    void setSlidingTiles(List<SlidingTile> slidingTiles) {
        Iterator<SlidingTile> iter = slidingTiles.iterator();

        for (int row = 0; row != this.dimension; row++) {
            for (int col = 0; col != this.dimension; col++) {
                this.slidingTiles[row][col] = iter.next();
            }
        }
    }

    public void setSlidingTiles(SlidingTile[][] tiles) {
        this.slidingTiles = tiles;
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
        return slidingTiles[row][col];
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
        SlidingTile temp1 = this.slidingTiles[row1][col1];
        SlidingTile temp2 = this.slidingTiles[row2][col2];
        this.slidingTiles[row1][col1] = temp2;
        this.slidingTiles[row2][col2] = temp1;

        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "SlidingBoard{" +
                "slidingTiles=" + Arrays.toString(slidingTiles) +
                '}';
    }

    @NonNull
    @Override
    public Iterator<SlidingTile> iterator() {
        return new TileIterator(slidingTiles);

    }

    /**
     * internal nested iterator iterates through 2-D array slidingTiles
     *
     */
    private class TileIterator implements Iterator<SlidingTile> {
        int currentRow = 0;
        int currentCol = 0;
        int currentPosition = -1;
        SlidingTile[][] slidingTiles;
        // eg. (row, col) = (3, 2), then it is indeed at row 3, column 2 (start from 0 ......> 3)
        // we have 3 complete rows, and to make complete: + 2
        // current position = 3*NUM_COLS + 2

        /**
         * a new TileIterator takes slidingTiles and process it
         *
         * @param slidingTiles slidingTiles from board
         */
        TileIterator(SlidingTile[][] slidingTiles){
            this.slidingTiles = slidingTiles;
        }

        /**
         * Return true if array has next tile
         * false otherwise
         *
         * @return if there is more tile
         */
        @Override
        public boolean hasNext() {
            int temp = currentPosition + 1;
            currentRow = temp / dimension;
            currentCol = temp % dimension;
            return currentRow < dimension;
        }

        /**
         * Return the next tile to be processed
         * @return the next tile object
         */
        @Override
        public SlidingTile next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            } else {
                currentPosition++;
                currentRow = currentPosition / dimension;
                currentCol = currentPosition % dimension;
                return this.slidingTiles[currentRow][currentCol];
            }
        }
    }

    public SlidingTile[][] getTiles(){
        return this.slidingTiles;
    }
}





