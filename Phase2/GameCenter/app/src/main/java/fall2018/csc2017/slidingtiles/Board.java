package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.util.NoSuchElementException;
import java.util.Observable;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The sliding tiles board.
 */
public class Board extends Observable implements Serializable, Iterable<Tile>{

    /**
     * The number of rows and columns.
     */
    private int dimension;

    /**
     * The tiles on the board in row-major order.
     */
    private Tile[][] tiles;

    /**
     * A new empty board of n*n tiles.
     * @param n the number of rows and columns of the board
     */
    Board(int n) {
        this.dimension = n;
        tiles= new Tile[dimension][dimension];
    }

    /**
     * Set the tiles to tiles.
     * @param tiles the tiles for the board
     */
    void setTiles(List<Tile> tiles) {
        Iterator<Tile> iter = tiles.iterator();

        for (int row = 0; row != this.dimension; row++) {
            for (int col = 0; col != this.dimension; col++) {
                this.tiles[row][col] = iter.next();
            }
        }

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
    Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {
        Tile temp1 = this.tiles[row1][col1];
        Tile temp2 = this.tiles[row2][col2];
        this.tiles[row1][col1] = temp2;
        this.tiles[row2][col2] = temp1;

        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    @NonNull
    @Override
    public Iterator<Tile> iterator() {
        return new TileIterator(tiles);

    }

    /**
     * internal nested iterator iterates through 2-D array tiles
     *
     */
    private class TileIterator implements Iterator<Tile> {
        int currentRow = 0;
        int currentCol = 0;
        int currentPosition = -1;
        Tile[][] tiles;
        // eg. (row, col) = (3, 2), then it is indeed at row 3, column 2 (start from 0 ......> 3)
        // we have 3 complete rows, and to make complete: + 2
        // current position = 3*NUM_COLS + 2

        /**
         * a new TileIterator takes tiles and process it
         *
         * @param tiles tiles from board
         */
        TileIterator(Tile[][] tiles){
            this.tiles = tiles;
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
        public Tile next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            } else {
                currentPosition++;
                currentRow = currentPosition / dimension;
                currentCol = currentPosition % dimension;
                return this.tiles[currentRow][currentCol];
            }
        }
    }}





