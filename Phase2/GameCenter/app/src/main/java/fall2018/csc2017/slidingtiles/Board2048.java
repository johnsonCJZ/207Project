package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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

    void addTile() {
        ArrayList<Tile2048> empty = findEmpty();
        Tile2048 randomTile = empty.get((int) (Math.random() * empty.size()));
        randomTile.random();

    }

    ArrayList<Tile2048> findEmpty() {
        ArrayList result = new ArrayList<>();
        for (Tile2048 tile : this) {
            if (tile.isEmpty()) {
                result.add(tile);
            }
        }
        return result;
    }


    Tile2048 getTile(int x, int y) {
        return tiles[x][y];
    }
    //direction = up or down here
    void mergeVertical(String direction) {
        if (direction.equals("DOWN")) {
            for (int col = 0; col < dimension - 1; col++) {
                for (int row = 0; row < dimension - 1; row++) {
                    Tile2048 tile = tiles[row][col];
                    if (tile.equals(tiles[row + 1][col])) {
                        tile.setValue(tile.getValue() * 2);
                        moveVertical(row + 1, col, "DOWN");
                        break;
                    }
                }
            }
        }
        else {
            for (int col = 0; col < dimension - 1; col++) {
                for (int row = dimension - 1; row > 0; row--) {
                    Tile2048 tile = tiles[row][col];
                    if (tile.equals(tiles[row - 1][col])) {
                        tile.setValue(tile.getValue() * 2);
                        moveHorizontal(row - 1, col, "UP");
                        break;
                    }
                }
            }
        }

        setChanged();
        notifyObservers();
    }

    public Tile2048[][] getTiles() {
        return tiles;
    }

    private void moveVertical(int row, int col, String direction) {
        if (direction.equals("DOWN")) {
            for (int index = col; index < dimension - 1; index++) {
                tiles[row][col].setValue(tiles[row + 1][col].getValue());
            }
            tiles[dimension][col].setValue(0);
        } else {
            for (int index = col; index > 0; index--) {
                tiles[row][col].setValue(tiles[row][col - 1].getValue());
            }
            tiles[row][col].setValue(0);
        }

        setChanged();
        notifyObservers();
    }

    //direction = left or right here
    void mergeHorizontal(String direction) {
        if (direction.equals("LEFT")) {
            for (int i = 0; i < dimension - 1; i++) {
                for (int j = 0; j < dimension - 1; j++) {
                    Tile2048 tile = tiles[i][j];
                    if (tile.equals(tiles[i][j + 1])) {
                        tile.setValue(tile.getValue() * 2);
                        moveHorizontal(i, j + 1, "LEFT");
                        break;
                    }
                }
            }
        } else {
            for (int i = 0; i < dimension - 1; i++) {
                for (int j = dimension - 1; j > 0; j--) {
                    Tile2048 tile = tiles[i][j];
                    if (tile.equals(tiles[i][j - 1])) {
                        tile.setValue(tile.getValue() * 2);
                        moveHorizontal(i, j - 1, "RIGHT");
                        break;
                    }
                }
            }
        }

        setChanged();
        notifyObservers();
    }

    private void moveHorizontal(int row, int col, String direction) {
        if (direction.equals("LEFT")) {
            for (int index = col; index < dimension - 1; index++) {
                tiles[row][col].setValue(tiles[row][col + 1].getValue());
            }
            tiles[row][dimension].setValue(0);
        } else {
            for (int index = col; index > 0; index--) {
                tiles[row][col].setValue(tiles[row][col - 1].getValue());
            }
            tiles[row][0].setValue(0);
        }

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
        Tile2048Iterator(Tile2048[][] tiles) {
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
}