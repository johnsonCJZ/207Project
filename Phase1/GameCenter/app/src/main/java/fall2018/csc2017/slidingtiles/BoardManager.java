package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class BoardManager implements Serializable {

    /**
     * The board being managed.
     */
    private Board board;

    /**
     * The linkedlist of history moves of the board.
     */
    private History history = new History();

    /**
     * The index at which the element in history represents the current location of blank tile.
     */
    private int currIndex = 0;

    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     */
    public BoardManager(Board board) {
        this.board = board;
        int[] blank = this.findBlankIndex(0);
        this.history.add(new HistoryNode(blank));
    }

    /**
     * Return the current board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Manage a new shuffled n*n board.
     * @param n the number of rows and columns
     */
    public BoardManager(int n) {
        List<Tile> tiles = new ArrayList<>();
        this.board = new Board(n);
        final int numTiles = board.getDimension() * board.getDimension();
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum));
        }

        Collections.shuffle(tiles);
        board.setTiles(tiles);
        this.history.add(new HistoryNode(this.findBlankIndex(0)));
    }

    public History getHistory() {
        return history;
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    public boolean puzzleSolved() {
        Iterator iter = board.iterator();
        Tile previous = (Tile) iter.next();

        while (iter.hasNext()){
            Tile next = (Tile) iter.next();
            if (previous.getId() < next.getId()) {
                previous = next;
            }
            else {
                if (next.getId() == 0 && !iter.hasNext()) {
                    return true;
                }
                return false;
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
    public boolean isValidTap(int position) {

        int row = position / board.getDimension();
        int col = position % board.getDimension();
        int blankId = 0;
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == board.getDimension() - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == board.getDimension() - 1 ? null : board.getTile(row, col + 1);
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
    public void touchMove(int position) {

        int row = position / board.getDimension();
        int col = position % board.getDimension();
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
            if (currIndex == history.size-1) {history.add(new HistoryNode(result)); currIndex++;}
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
                result[0] = position / board.getDimension();
                result[1] = position % board.getDimension();
                break;
            }
            position ++;
        }
        return result;

    }

    public void undo() {
        if (history.size > 1) {
            int[] currPosition = history.get(currIndex).getData();
            int[] prePosition = history.get(currIndex-1).getData();
            board.swapTiles(prePosition[0], prePosition[1], currPosition[0], currPosition[1]);
            currIndex--;
        }
    }

    public void redo() {
        if (history.get(currIndex).next != null) {
            int[] currPosition = history.get(currIndex).getData();
            int[] postPosition = history.get(currIndex+1).getData();
            board.swapTiles(postPosition[0], postPosition[1], currPosition[0], currPosition[1]);
            currIndex++;
        }
    }
}
