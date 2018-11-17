package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;

public class Board2048 extends Observable implements Serializable, Iterable<Tile2048> {
    final private int dimension = 4;

    private Tile2048[][] tiles;

    /**
     * A new empty board of 4*4 tiles.
     */
    Board2048() {
        this.tiles = new Tile2048[dimension][dimension];
    }

    private void addTile() {
        ArrayList<Tile2048> empty = findEmpty();
        Tile2048 randomTile = empty.get((int)(Math.random()*empty.size()));
        randomTile.random();

    }

    void setTiles() {
        for (int row = 0; row != this.dimension; row++) {
            for (int col = 0; col != this.dimension; col++) {
                this.tiles[row][col] = Tile.ZERO;
            }
        }

    }

    ArrayList<Tile2048> findEmpty() {
        ArrayList result = new ArrayList<>();
        for (Tile2048 tile:this){
        if (tile.isEmpty()) {
            result.add(tile);
        }
        return result;
        }
    }

    Tile2048 tileAtPosition(int x, int y){
    return tiles[x][y];
    }

    void swapTiles(int row1, int col1, int row2, int col2) {
        Tile2048 temp1 = this.tiles[row1][col1];
        Tile2048 temp2 = this.tiles[row2][col2];
        this.tiles[row1][col1] = temp2;
        this.tiles[row2][col2] = temp1;

        setChanged();
        notifyObservers();
    }

    public int getDimension() {
        return dimension;
    }

    @NonNull
    @Override
    public Iterator<Tile2048> iterator() {
        return new Tile2048Iterator(tiles);
    }

    /**
     * internal nested iterator iterates through 2-D array tiles
     *
     */
    private class Tile2048Iterator implements Iterator<Tile2048> {
        int currentRow = 0;
        int currentCol = 0;
        int currentPosition = -1;
        Tile2048[][] tiles;
        // eg. (row, col) = (3, 2), then it is indeed at row 3, column 2 (start from 0 ......> 3)
        // we have 3 complete rows, and to make complete: + 2
        // current position = 3*NUM_COLS + 2

        /**
         * a new TileIterator takes tiles nd process it
         *
         * @param tiles tiles from board
         */
        Tile2048Iterator(Tile2048[][] tiles){
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
         *
         * @return the next tile object
         */
        @Override
        public Tile2048 next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            } else {
                currentPosition++;
                currentRow = currentPosition / dimension;
                currentCol = currentPosition % dimension;
                return this.tiles[currentRow][currentCol];
            }
        }
}