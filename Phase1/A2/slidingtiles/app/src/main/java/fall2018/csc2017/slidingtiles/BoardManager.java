package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManager implements Serializable {

    /**
     * The board being managed.
     */
    private Board board;

    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     */
    BoardManager(Board board) {
        this.board = board;
    }

    /**
     * Return the current board.
     */
    Board getBoard() {
        return board;
    }

    /**
     * Manage a new shuffled board.
     */
    BoardManager() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = Board.NUM_ROWS * Board.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum));
        }

        Collections.shuffle(tiles);
        this.board = new Board(tiles);
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean puzzleSolved() {
        int current = 0;
        boolean solved = true;
        for (Tile aBoard : this.board) {
            int temp = aBoard.getId();
            if (current < temp) {
                current = temp;
            } else {
                solved = false;
                break;
            }
        }
        return solved;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {

        int row = position / Board.NUM_ROWS;
        int col = position % Board.NUM_COLS;
        int blankId = board.numTiles();
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == Board.NUM_ROWS - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == Board.NUM_COLS - 1 ? null : board.getTile(row, col + 1);
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

        int row = position / Board.NUM_ROWS;
        int col = position % Board.NUM_COLS;
        int blankId = board.numTiles();
        int r_row;
        int r_col;
        if (isValidTap(position)){
            int[] result = findBlankIndex(this.board, blankId);
            r_row = result[0];
            r_col = result[1];
            board.swapTiles(row,col,r_row,r_col);
        }

    }

    /**
     * once we know that there is a blank space surrounding tile, which id is targetId
     * we use iterator to find that blank tile
     *
     * @param b board
     * @param targetId id of the tile that have blank tile around
     * @return an array of coordination of blank tile
     */
    private int[] findBlankIndex(Board b, int targetId){
        int[] result = new int[2];
        int position = 0;
        for (Tile t : b){
            if (t.getId()==targetId){
                result[0] = position / Board.NUM_ROWS;
                result[1] = position % Board.NUM_COLS;
                break;
            }
            position ++;
        }
        return result;

    }

}
