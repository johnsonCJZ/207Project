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
 *
 */
public class Board extends Observable implements Serializable, Iterable<Tile>{

    /**
     * The number of rows.
     */
    final static int NUM_ROWS = 4;

    /**
     * The number of rows.
     */
    final static int NUM_COLS = 4;

    /**
     * The tiles on the board in row-major order.
     */
    private Tile[][] tiles = new Tile[NUM_ROWS][NUM_COLS];

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    Board(List<Tile> tiles) {
        Iterator<Tile> iter = tiles.iterator();

        for (int row = 0; row != Board.NUM_ROWS; row++) {
            for (int col = 0; col != Board.NUM_COLS; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    /**
     * Return the number of tiles on the board.
     * @return the number of tiles on the board
     */
    int numTiles() {
        return NUM_ROWS*NUM_COLS; // no need to check empty tile on board
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
    /**
     * implement iterator for Iterable<Tile> interface
     *
     */
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
         * a new TileIterator takes tiles nd process it
         *
         * @param tiles tiles from board
         */
        public TileIterator (Tile[][] tiles){
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
            currentRow = temp / NUM_COLS;
            currentCol = temp % NUM_COLS;
            return currentRow < NUM_ROWS;
        }

        /**
         * Return the next tile to be processed
         *
         * @return the next tile object
         */
        @Override
        public Tile next() {
            if (this.hasNext()) {
                currentPosition++;
                currentRow = currentPosition / NUM_COLS;
                currentCol = currentPosition % NUM_COLS;
                return this.tiles[currentRow][currentCol];
            } else {
                throw new NoSuchElementException();
            }
        }
    }}





