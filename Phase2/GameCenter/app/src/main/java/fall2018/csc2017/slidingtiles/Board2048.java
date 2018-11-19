package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observable;

public class Board2048 extends Observable implements Serializable, Iterable<Tile2048> {
    final static int dimension = 4;

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
        ArrayList<Tile2048> result = new ArrayList<>();
        for (Tile2048 tile : this) {
            if (tile.isEmpty()) {
                result.add(tile);
            }
        }
        return result;
    }

    private Tile2048[] getColumn(int col) {
        Tile2048[] result = new Tile2048[dimension];
        for (int i = 0; i < dimension; i++){
            result[i] = tiles[i][col];
        }
        return result;
    }

    static void moveEmpty(Tile2048[] tileArray, String direction){
        ArrayList<Integer> temp = new ArrayList<>(dimension);
        switch (direction) {
            case "LEFT":
                for (int i = 0; i < dimension; i++) {
                    Tile2048 tile = tileArray[i];
                    Integer value = tile.getValue();
                    if (!value.equals(0)) {
                        temp.add(value);
                    }
                }
                while (temp.size() != dimension) {
                    temp.add(0);
                }
                for (int i = 0; i < dimension; i++) {
                    tileArray[i].setValue(temp.get(i));
                }
            case "RIGHT" :
                for (int i = dimension - 1; i == 0; i--) {
                    Tile2048 tile = tileArray[i];
                    Integer value = tile.getValue();
                    if (!value.equals(0)) {
                        temp.add(value);
                    }
                }
                while (temp.size() != dimension) {
                    temp.add(0);
                }
                for (int i = dimension - 1; i == 0; i--) {
                    tileArray[i].setValue(temp.get(i));
                }
            default:
                throw new NoSuchElementException("No Such Direction");

        }
    }

    void merge(String direction) {
        switch (direction) {
            case "LEFT":
                for (int row = 0; row < dimension - 1; row++) {
                    mergeToLeft(tiles[row]);
                }
                break;
            case "UP":
                for (int col = 0; col < dimension - 1; col++) {
                    mergeToLeft(getColumn(col));
                }
                break;
            case "RIGHT":
                for (int row = 0; row < dimension - 1; row++) {
                    mergeToRight(tiles[row]);
                }
                break;
            case "DOWN":
                for (int col = 0; col < dimension - 1; col++) {
                    mergeToRight(getColumn(col));
                }
                break;
            default:
                throw new NoSuchElementException("No Such Direction");
        }

        setChanged();
        notifyObservers();
    }

    private void mergeToRight(Tile2048[] tileArray) {
        moveEmpty(tileArray, "RIGHT");
        for (int j = dimension - 1; j > 0; j--) {
            Tile2048 tile = tileArray[j];
            if (tile.equals(tileArray[j - 1])) {
                tile.setValue(tile.getValue() * 2);
                move(tileArray, j - 1, "RIGHT");
                break;
            }
        }
    }

    private void mergeToLeft(Tile2048[] tileArray) {
        moveEmpty(tileArray, "LEFT");
        for (int j = 0; j < dimension - 1; j++) {
            Tile2048 tile = tileArray[j];
            if (tile.equals(tileArray[j + 1])) {
                tile.setValue(tile.getValue() * 2);
                move(tileArray, j + 1, "LEFT");
                break;
            }
        }
    }


    private void move(Tile2048[] tileArray, int col, String direction) {
        int i = col;
        Integer value = tileArray[i].getValue();
        if (direction.equals("LEFT")) {
            while (!value.equals(0) && i != dimension - 1) {
                tileArray[i].setValue(tileArray[i + 1].getValue());
                i++;
                value = tileArray[i].getValue();
            }
            tileArray[dimension].setValue(0);
        } else {
            while (!value.equals(0) && i != 0) {
                tileArray[i].setValue(tileArray[i - 1].getValue());
                i--;
                value = tileArray[i].getValue();
            }
            tileArray[0].setValue(0);
        }
    }

    Tile2048 getTile(int x, int y) {
        return tiles[x][y];
    }

    int getDimension() {
        return dimension;
    }

    Tile2048[][] getTiles() {
        return tiles;
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