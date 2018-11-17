package fall2018.csc2017.slidingtiles;

public class Board2048Manager implements Cloneable{
    private Board2048 board;

    private int dimension;

    Board2048 getBoard() {
        return board;
    }

    private Board2048Manager() {
        this.board = new Board2048();
        this.dimension = board.getDimension();
        board.addTile();
        board.addTile();
    }

    private boolean isFull() {
        return board.findEmpty().size() == 0;
    }

    boolean isValidMove(String direction) {
        if (!isFull()) {
            return true;
        } else {
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    Tile2048 tile = board.tileAtPosition(i, j);
                if ((i < dimension - 1 && tile.equals(board.tileAtPosition(i + 1, j)))
                    || (j < dimension - 1 && tile.equals(board.tileAtPosition(i, j+1)))) {
                    return true;
                }
                }
            }
        }
        return false;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
