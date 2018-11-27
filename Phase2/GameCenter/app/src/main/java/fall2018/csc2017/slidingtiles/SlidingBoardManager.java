package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Manage a slidingBoard, including swapping slidingTiles, checking for a win, and managing taps.
 */
public class SlidingBoardManager extends BoardManager implements Serializable, Cloneable {

    /**
     * The time for how long the slidingBoard has been played.
     */
    private Double time;

    /**
     * The slidingBoard being managed.
     */
    private SlidingBoard slidingBoard;
    
    private int dimension;

    private List<SlidingTile> slidingTiles = new ArrayList<>();

    /**
     * The linked list of history moves of the slidingBoard.
     */
    private History history = new History();

    /**
     * The index at which the element in history represents the current location of blank tile.
     */
    private int currIndex = 0;

    /**
     * Create a new SlidingBoardManager to manage a new shuffled n*n slidingBoard.
     * @param n the number of rows and columns
     */
    SlidingBoardManager(int n) {
        this.time = 0.0;
        this.slidingBoard = new SlidingBoard(n);
        this.dimension = slidingBoard.getDimension();
        final int numTiles = dimension * dimension;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            slidingTiles.add(new SlidingTile(tileNum));
        }
        shuffle();
        slidingBoard.setSlidingTiles(slidingTiles);
        this.history.add(new HistoryNode(this.findBlankIndex(0)));
    }

    /**
     * Return the current slidingBoard.
     */
    SlidingBoard getSlidingBoard() {
        return slidingBoard;
    }

    /**
     * Return a copy of the SlidingBoardManager.
     * @return a copy of the SlidingBoardManager.
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Return how many inversions occur in the list of slidingTiles.
     * An inversion is when a tile precedes another tile with a lower number on it.
     * @return the number of inversion
     */
    int findInversion() {
        int count = 0;
        for (int i = 0; i < dimension -1; i++) {
            if (slidingTiles.get(i).getId() != 0){
                for (int j = i+1; j < dimension; j++){
                    if (slidingTiles.get(j).getId() < slidingTiles.get(i).getId() && slidingTiles.get(j).getId() != 0){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Shuffle the list of slidingTiles until the state is solvable.
     */
    void shuffle() {
        Collections.shuffle(slidingTiles);
        while (!isSolvable()) {
            Collections.shuffle(slidingTiles);
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
            while(slidingTiles.get(i).getId() != 0){
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
     * Return whether the slidingTiles are in row-major order.
     *
     * @return whether the slidingTiles are in row-major order
     */
    boolean isWon() {
        Iterator iter = slidingBoard.iterator();
        SlidingTile previous = (SlidingTile) iter.next();

        while (iter.hasNext()){
            SlidingTile next = (SlidingTile) iter.next();
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
     * Return whether any of the four surrounding slidingTiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {

        int row = position / dimension;
        int col = position % dimension;
        int blankId = 0;
        // Are any of the 4 the blank tile?
        SlidingTile above = row == 0 ? null : slidingBoard.getTile(row - 1, col);
        SlidingTile below = row == dimension - 1 ? null : slidingBoard.getTile(row + 1, col);
        SlidingTile left = col == 0 ? null : slidingBoard.getTile(row, col - 1);
        SlidingTile right = col == dimension - 1 ? null : slidingBoard.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }

    /**
     * Process a touch at position in the slidingBoard, swapping slidingTiles as appropriate.
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
            slidingBoard.swapTiles(row,col,r_row,r_col);
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
        for (SlidingTile t : this.slidingBoard){
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
     * Perform undo operation if i is -1 and perform redo operation if i is 1
     * @param i is -1 or 1 to indicate performing undo or redo
     */
    public void readHistory(int i) {
        int[] currPosition = new int[2];
        int[] postPosition = new int[2];
        //redo
        if (i == 1 && history.get(currIndex).next != null) {
            currPosition = history.get(currIndex).getData();
            postPosition = history.get(currIndex+1).getData();
            currIndex++;
        }
        // undo
        else if (i == -1 && history.getSize() > 1){
            currPosition = history.get(currIndex).getData();
            postPosition = history.get(currIndex-1).getData();
            currIndex--;
        }
        slidingBoard.swapTiles(postPosition[0], postPosition[1], currPosition[0], currPosition[1]);
    }
}
