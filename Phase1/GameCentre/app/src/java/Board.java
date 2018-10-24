package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.util.Observable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The sliding tiles board.
 *
 */


public class Board extends Observable implements Serializable, Iterable<Tile> {

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
        return NUM_COLS * NUM_ROWS;
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

    @NonNull

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {
        Tile tile1 = tiles[row1][col1];
        Tile tile2 = tiles[row2][col2];

        tiles[row1][col1] = tile2;
        tiles[row2][col2] = tile1;

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
        return new TileIterator();
    }


    /**
     * The TileIterator that iterates over a list of Tile.
     *
     */

    private class TileIterator implements Iterator {

        /**
         * Position of row, setting at -1.
         */
        private int row_position = -1;

        /**
         * Position of column, setting at -1.
         */
        private int col_position = -1;

        /**
         * Increase the row and column indices
         *
         */
        private void increaseindex() {
            if (row_position == -1 && col_position == -1){
                ++row_position;
                ++col_position;
            }
            else if (col_position != NUM_COLS -1 ) {
                ++col_position;
            }
            else {
                col_position = 0;
                ++row_position;
            }


        }
        @Override
        public boolean hasNext() {

            return !(row_position == NUM_ROWS - 1 && col_position == NUM_COLS - 1);
        }

        @Override
        public Tile next() {
            if (this.hasNext()){
                increaseindex();
                return tiles[row_position][col_position];
            }
            return null;
        }
}



}
