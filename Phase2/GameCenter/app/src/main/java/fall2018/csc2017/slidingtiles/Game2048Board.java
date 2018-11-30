package fall2018.csc2017.slidingtiles;
import android.support.annotation.NonNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;

public class Game2048Board extends Board<Game2048Tile> implements Iterable<Game2048Tile> {
    private int score = 0;
    private boolean isChanged = false;

    /**
     * A new empty board of 4*4 slidingTiles.
     */
    Game2048Board() {
    }

    void setUpTiles() {
        for (int row = 0; row != dimension; row++) {
            for (int col = 0; col != dimension; col++) {
                this.setTile(row, col, new Game2048Tile());
            }
        }
    }

    // precondition: list.size() == 16
    void setUpTiles(List<Integer> list) {
        int i = 0;
        for (Game2048Tile tile:this) {
            tile.setValue(list.get(i));
            i++;
        }
    }

    boolean isChanged() {return isChanged;}

    int getScore() {return score;}

    void setScore(int score) {this.score = score;}

    Game2048Tile addTile() {
        ArrayList<Game2048Tile> empty = findEmpty();
        System.out.println(findEmpty().size());
        Game2048Tile randomTile = empty.get((int) (Math.random() * empty.size()));
        randomTile.random();
        isChanged = false;
        return randomTile;
        //return null;
    }

    ArrayList<Game2048Tile> findEmpty() {
        ArrayList<Game2048Tile> result = new ArrayList<>();
        for (Game2048Tile tile : this.tiles) {
            if (tile.isEmpty()) {
                result.add(tile);
            }
        }
        return result;
    }

    Game2048Tile[] getRow(int row) {
        Game2048Tile[] result = new Game2048Tile[dimension];
        for (int i = 0; i < dimension; i++) {
            result[i] = tiles.get(row * dimension + i);
        }
        return result;
    }

    private Game2048Tile[] getColumn(int col) {
        Game2048Tile[] result = new Game2048Tile[dimension];

        for (int i = 0; i < dimension; i++) {
            result[i] = tiles.get(i * dimension + col);
        }
        return result;
    }

    void mergeList(Game2048Tile[] tileArray, String direction){
        ArrayList<Integer> temp = new ArrayList<>(dimension);
        int i;
        switch (direction) {
            case "LEFT":
                i = 0;
                while (i <= dimension -1) {
                    if (i == dimension -1) {
                        temp.add(tileArray[i].getValue());
                        i++;
                    }
                    else if (tileArray[i].getValue() == 0){i++;}
                    else {
                        int j = i+1;
                        while (j< dimension -1 && tileArray[j].getValue() == 0) {
                            j++;
                        }
                        Integer valueI = tileArray[i].getValue();
                        Integer valueJ = tileArray[j].getValue();
                        if (valueI.equals(valueJ)){
                            temp.add(valueI*2);
                            score = score + valueI * 2;
                            i=j+1;
                        }
                        else{
                            temp.add(valueI);
                            i = j;
                        }
                    }
                }

                while (temp.size() != dimension) {
                    temp.add(0);
                }

                for (i = 0; i < dimension; i++){
                    if (tileArray[i].getValue() != temp.get(i)){
                        isChanged = true;
                        tileArray[i].setValue(temp.get(i));
                    }
                }
                break;

            case "RIGHT" :
                i = dimension - 1;
                while (i >= 0) {
                    if (i == 0) {
                        temp.add(tileArray[i].getValue());
                        i--;
                    }
                    else if (tileArray[i].getValue() == 0){i--;}
                    else {
                        int j = i-1;
                        while (j>0 && tileArray[j].getValue() == 0) {
                            j--;
                        }
                        Integer valueI = tileArray[i].getValue();
                        Integer valueJ = tileArray[j].getValue();
                        if (valueI.equals(valueJ)){
                            temp.add(valueI*2);
                            i=j-1;
                        }
                        else{
                            temp.add(valueI);
                            i = j;
                        }
                    }
                }

                while (temp.size() != dimension) {
                    temp.add(0);
                }

                for (i = dimension - 1; i >= 0; i--) {
                    if (tileArray[i].getValue() != temp.get(temp.size() - 1 - i)) {
                        isChanged = true;
                        tileArray[i].setValue(temp.get(temp.size() - 1 - i));
                    }
                }
                break;

            default:
                throw new NoSuchElementException("No Such Direction");

        }
    }

    void merge(String direction) {
        switch (direction) {
            case "LEFT":
                for (int row = 0; row <= dimension - 1; row++) {
                    mergeList(getRow(row), "LEFT");
                }
                break;
            case "UP":
                for (int col = 0; col <= dimension - 1; col++) {
                    mergeList(getColumn(col), "LEFT");
                }
                break;
            case "RIGHT":
                for (int row = 0; row <= dimension - 1; row++) {
                    mergeList(getRow(row), "RIGHT");
                }
                break;
            case "DOWN":
                for (int col = 0; col <= dimension - 1; col++) {
                    mergeList(getColumn(col), "RIGHT");
                }
                break;
            default:
                throw new NoSuchElementException("No Such Direction");
        }


        setChanged();
        notifyObservers();
    }

    Game2048Tile getTile(int x, int y) {
        return tiles.get(x * dimension + y);
    }

    void setTile(int x, int y, Game2048Tile tile){
        tiles.set(x * dimension + y, tile);
    }

    @NonNull
    @Override
    public Iterator<Game2048Tile> iterator() {
        return new Tile2048Iterator(tiles);
    }


    /**
     * internal nested iterator iterates through 2-D array slidingTiles
     */
    private class Tile2048Iterator implements Iterator<Game2048Tile> {
        int currentPosition = -1;
        List<Game2048Tile> tiles;
        // eg. (row, col) = (3, 2), then it is indeed at row 3, column 2 (start from 0 ......> 3)
        // we have 3 complete rows, and to make complete: + 2
        // current position = 3*NUM_COLS + 2

        /**
         * a new TileIterator takes slidingTiles nd process it
         *
         * @param tiles slidingTiles from board
         */
        Tile2048Iterator(List<Game2048Tile> tiles) {
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

            return currentPosition < dimension * dimension -1;
        }

        /**
         * Return the next tile to be processed
         *
         * @return the next tile object
         */
        @Override
        public Game2048Tile next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            } else {
                currentPosition++;
                return this.tiles.get(currentPosition);
            }
        }
    }
}