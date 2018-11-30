package fall2018.csc2017.slidingtiles;
import android.support.annotation.NonNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;

public class Game2048Board extends Board implements Serializable, Iterable<Game2048Tile>, IObservable<Game2048Board> {
    final static int DIMENSION = 4;
    private int score = 0;
    private boolean isChanged = false;
    public boolean changed = false;
    private ArrayList<IObserver> observers = new ArrayList<>();

    /**
     * A new empty board of 4*4 slidingTiles.
     */
    Game2048Board() {
        this.tiles = new ArrayList<>();

    }

    void setUpTiles() {
        for (int row = 0; row != DIMENSION; row++) {
            for (int col = 0; col != DIMENSION; col++) {
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
        //if (!empty.isEmpty()){ // add if not empty
        Game2048Tile randomTile = empty.get((int) (Math.random() * empty.size()));
        randomTile.random();
        isChanged = false;
        return randomTile;
        //}
        //return null;
    }

    ArrayList<Game2048Tile> findEmpty() {
        ArrayList<Game2048Tile> result = new ArrayList<>();
        for (Tile tile : this.tiles) {
            Game2048Tile t = (Game2048Tile) tile;
            if (t.isEmpty()) {
                result.add(t);
            }
        }
        return result;
    }

    Game2048Tile[] getRow(int row) {
        Game2048Tile[] result = new Game2048Tile[DIMENSION];
        for (int i = 0; i < DIMENSION; i++) {
            result[i] = (Game2048Tile) tiles.get(row * DIMENSION + i);
        }
        return result;
    }

    private Game2048Tile[] getColumn(int col) {
        Game2048Tile[] result = new Game2048Tile[DIMENSION];

        for (int i = 0; i < DIMENSION; i++) {
            result[i] = (Game2048Tile) tiles.get(i * DIMENSION + col);
        }
        return result;
    }

    void mergeList(Game2048Tile[] tileArray, String direction){
        ArrayList<Integer> temp = new ArrayList<>(DIMENSION);
        int i;
        switch (direction) {
            case "LEFT":
                i = 0;
                while (i <= DIMENSION -1) {
                    if (i == DIMENSION -1) {
                        temp.add(tileArray[i].getValue());
                        i++;
                    }
                    else if (tileArray[i].getValue() == 0){i++;}
                    else {
                        int j = i+1;
                        while (j< DIMENSION -1 && tileArray[j].getValue() == 0) {
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

                while (temp.size() != DIMENSION) {
                    temp.add(0);
                }

                for (int m = 0; m < DIMENSION; m++){
                    if (tileArray[m].getValue() != temp.get(m)){
                        isChanged = true;
                    }
                }

                for (int k = 0; k < DIMENSION; k++) {
                    tileArray[k].setValue(temp.get(k));
                }
                break;

            case "RIGHT" :
                i = DIMENSION - 1;
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

                while (temp.size() != DIMENSION) {
                    temp.add(0);
                }

                for (int m = 0; m < DIMENSION; m++) {
                    if (tileArray[m].getValue() != temp.get(temp.size() - 1 - m)) {
                        isChanged = true;
                    }
                }

                for (i = DIMENSION - 1; i >= 0; i--) {
                    tileArray[i].setValue(temp.get(temp.size()-1-i));
                }
                break;

            default:
                throw new NoSuchElementException("No Such Direction");

        }
    }

    void merge(String direction) {
        switch (direction) {
            case "LEFT":
                for (int row = 0; row <= DIMENSION - 1; row++) {
                    mergeList(getRow(row), "LEFT");
                }
                break;
            case "UP":
                for (int col = 0; col <= DIMENSION - 1; col++) {
                    mergeList(getColumn(col), "LEFT");
                }
                break;
            case "RIGHT":
                for (int row = 0; row <= DIMENSION - 1; row++) {
                    mergeList(getRow(row), "RIGHT");
                }
                break;
            case "DOWN":
                for (int col = 0; col <= DIMENSION - 1; col++) {
                    mergeList(getColumn(col), "RIGHT");
                }
                break;
            default:
                throw new NoSuchElementException("No Such Direction");
        }


        setChanged();
        notifyObservers();
    }

//    private void mergeToRight(Game2048Tile[] tileArray) {
//        moveEmpty(tileArray, "RIGHT");
//        for (int j = DIMENSION - 1; j > 0; j--) {
//            Game2048Tile tile = tileArray[j];
//            if (tile.getValue() != 0 && tile.equals(tileArray[j - 1])) {
//                tile.setValue(tile.getValue() * 2);
//                move(tileArray, j - 1, "RIGHT");
//                break;
//            }
//        }
//    }

//    private void mergeToLeft(Game2048Tile[] tileArray) {
//        moveEmpty(tileArray, "LEFT");
//        for (int j = 0; j < DIMENSION - 1; j++) {
//            Game2048Tile tile = tileArray[j];
//            if (tile.getValue() != 0 && tile.equals(tileArray[j + 1])) {
//                tile.setValue(tile.getValue() * 2);
//                move(tileArray, j + 1, "LEFT");
//                break;
//            }
//        }
//    }


//    private void move(Game2048Tile[] tileArray, int col, String direction) {
//        int i = col;
//        Integer value = tileArray[i].getValue();
//        if (direction.equals("LEFT")) {
//            while (!value.equals(0) && i != DIMENSION - 1) {
//                tileArray[i].setValue(tileArray[i + 1].getValue());
//                i++;
//                value = tileArray[i].getValue();
//            }
//            tileArray[DIMENSION].setValue(0);
//            addTile();
//        } else {
//            while (!value.equals(0) && i != 0) {
//                tileArray[i].setValue(tileArray[i - 1].getValue());
//                i--;
//                value = tileArray[i].getValue();
//            }
//            tileArray[0].setValue(0);
//            addTile();
//        }
//    }

    Game2048Tile getTile(int x, int y) {
        return (Game2048Tile) tiles.get(x * DIMENSION + y);
    }

    int getDimension() {
        return DIMENSION;
    }

    void setTile(int x, int y, Game2048Tile tile){
        tiles.set(x * DIMENSION + y, tile);
    }

    @NonNull
    @Override
    public Iterator<Game2048Tile> iterator() {
        return new Tile2048Iterator((List<Game2048Tile>)(List<?>)tiles);
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

            return currentPosition < DIMENSION * DIMENSION -1;
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