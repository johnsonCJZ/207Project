package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

public class Board2048Manager extends Manager implements Cloneable, Serializable {
    private Board2048 board;

    private Integer dimension;

    Board2048 getBoard() {
        return board;
    }

    Board2048Manager() {
        this.board = new Board2048();
        this.dimension = board.getDimension();
        board.setUpTiles();
        board.addTile();
    }

    void cheat() {
        board.getTile(0,0).setValue(2048);
    }

    private boolean isFull() {
        return board.findEmpty().size() == 0;
    }

    boolean isLose() {
        return !canMove();
    }

    boolean isWon(){
        for (Tile2048 tile : board) {
            if (tile.getValue() == 2048) {
                return true;
            }
        }
        return false;
    }

    void moveUp() {
        board.merge("UP");
    }

    void moveDown() {
        board.merge("DOWN");
    }

    void moveLeft() {
        board.merge("LEFT");
    }

    void moveRight() {
        board.merge("RIGHT");
    }

    private boolean canMove() {
        if (!isFull()) {
            return true;
        } else {
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    Tile2048 tile = board.getTile(i, j);
                if ((i < dimension - 1 && tile.equals(board.getTile(i + 1, j)))
                    || (j < dimension - 1 && tile.equals(board.getTile(i, j+1)))) {
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
