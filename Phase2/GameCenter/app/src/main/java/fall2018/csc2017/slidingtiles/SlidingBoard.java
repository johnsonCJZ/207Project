package fall2018.csc2017.slidingtiles;

/**
 * The sliding slidingTiles board.
 */
class SlidingBoard extends Board<SlidingTile> {
    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    SlidingTile getTile(int row, int col) {
        return getTiles().get(row * getDimension() + col);
    }

    /**
     * Swap the slidingTiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {
        SlidingTile temp1 = getTile(row1, col1);
        SlidingTile temp2 = getTile(row2, col2);
        getTiles().set(row1 * getDimension() + col1, temp2);
        getTiles().set(row2 * getDimension() + col2, temp1);

        setChanged();
        notifyObservers();
    }

}




