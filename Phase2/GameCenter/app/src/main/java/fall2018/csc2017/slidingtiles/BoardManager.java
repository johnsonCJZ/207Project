package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class BoardManager extends Manager implements Serializable, Cloneable {

    /**
     * The time for how long the board has been played.
     */
    private Double time;

    /**
     * The board being managed.
     */
    private Board board;
    
    private int dimension;

    private List<Tile> tiles = new ArrayList<>();

    /**
     * The linked list of history moves of the board.
     */
    private History history = new History();

    /**
     * The index at which the element in history represents the current location of blank tile.
     */
    private int currIndex = 0;

    /**
     * Create a new BoardManager to manage a new shuffled n*n board.
     * @param n the number of rows and columns
     */
    BoardManager(int n) {
        this.time = 0.0;
        this.board = new Board(n);
        this.dimension = board.getDimension();
        final int numTiles = dimension * dimension;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum));
        }
        shuffle();
        board.setTiles(tiles);
        this.history.add(new HistoryNode(this.findBlankIndex(0)));
    }

    /**
     * Return the current board.
     */
    Board getBoard() {
        return board;
    }

    /**
     * Return a copy of the BoardManager.
     * @return a copy of the BoardManager.
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Return how many inversions occur in the list of tiles.
     * An inversion is when a tile precedes another tile with a lower number on it.
     * @return the number of inversion
     */
    int findInversion() {
        int count = 0;
        for (int i = 0; i < dimension -1; i++) {
            if (tiles.get(i).getId() != 0){
                for (int j = i+1; j < dimension; j++){
                    if (tiles.get(j).getId() < tiles.get(i).getId() && tiles.get(j).getId() != 0){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Shuffle the list of tiles until the state is solvable.
     */
    void shuffle() {
        Collections.shuffle(tiles);
        while (!isSolvable()) {
            Collections.shuffle(tiles);
        }
    }

    /**
     * Return if the shuffled state can be solved.
     * @return if the state is solvable.
     */
    boolean isSolvable() {
        int inversion = findInversion();
        if (dimension % 2 == 1) {
            return inversion % 2 == 0;
        }
        else {
            int i = 0;
            while(tiles.get(i).getId() != 0){
                i++;
            }
            int blankAtRowFromBottom = dimension- (i/dimension);
            return inversion % 2 != blankAtRowFromBottom % 2;
        }
    }

    /**
     * Return the time recorded.
     * @return the time recorded
     */
    public double getTime() {
        return time;
    }

    /**
     * Set the time to time.
     * @param time the time to be set
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     * Return the History.
     * @return the History
     */
    History getHistory() {
        return history;
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean isWon() {
        Iterator iter = board.iterator();
        Tile previous = (Tile) iter.next();

        while (iter.hasNext()){
            Tile next = (Tile) iter.next();
            if (previous.getId() < next.getId()) {
                previous = next;
            }
            else {
                return next.getId() == 0 && !iter.hasNext();
            }
        }
        return false;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {

        int row = position / dimension;
        int col = position % dimension;
        int blankId = 0;
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == dimension - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == dimension - 1 ? null : board.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    void touchMove(int position) {

        int row = position / dimension;
        int col = position % dimension;
        int blankId = 0;
        int r_row;
        int r_col;
        if (isValidTap(position)){
            int[] result = findBlankIndex(blankId);
            r_row = result[0];
            r_col = result[1];
            board.swapTiles(row,col,r_row,r_col);
            result[0] = row;
            result[1] = col;
            if (currIndex == history.getSize()-1) {history.add(new HistoryNode(result)); currIndex++;}
            else {
                history.remove(currIndex+1);
                history.add(new HistoryNode((result)));
                currIndex++;
            }
        }


    }

    /**
     * once we know that there is a blank space surrounding tile, which id is targetId
     * we use iterator to find that blank tile
     *
     * @param targetId id of the tile that have blank tile around
     * @return an array of coordination of blank tile
     */
    private int[] findBlankIndex(int targetId){
        int[] result = new int[2];
        int position = 0;
        for (Tile t : this.board){
            if (t.getId()==targetId){
                result[0] = position / dimension;
                result[1] = position % dimension;
                break;
            }
            position ++;
        }
        return result;

    }

    /**
     * Undo swipe operations.
     */
    public void undo() {
        if (history.getSize() > 1) {
            int[] currPosition = history.get(currIndex).getData();
            int[] prePosition = history.get(currIndex-1).getData();
            board.swapTiles(prePosition[0], prePosition[1], currPosition[0], currPosition[1]);
            currIndex--;
        }
    }

    /**
     * Redo the swipe operations that have been undone.
     */
    public void redo() {
        if (history.get(currIndex).next != null) {
            int[] currPosition = history.get(currIndex).getData();
            int[] postPosition = history.get(currIndex+1).getData();
            board.swapTiles(postPosition[0], postPosition[1], currPosition[0], currPosition[1]);
            currIndex++;
        }
    }
}
