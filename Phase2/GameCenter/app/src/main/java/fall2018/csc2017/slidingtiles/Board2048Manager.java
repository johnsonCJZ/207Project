package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

public class Board2048Manager extends Manager implements Cloneable, Serializable {
    private Board2048 board;

    private int dimension;

    Board2048 getBoard() {
        return board;
    }

    private Board2048Manager() {
        this.board = new Board2048();
        this.dimension = board.getDimension();
        setTiles();
        board.addTile();
        board.addTile();
    }

    void setTiles() {
        for (int row = 0; row != this.dimension; row++) {
            for (int col = 0; col != this.dimension; col++) {
                Tile2048 tile = this.board.getTiles()[row][col];
                tile.setX(col);
                tile.setY(row);
                tile.setValue(0);
            }
        }

    }

    private boolean isFull() {
        return board.findEmpty().size() == 0;
    }

    boolean isLose() {
        return !canMove();
    }

    boolean isWin(){
        for (Tile2048 tile : board) {
            if (tile.getValue() == 2048) {
                return true;
            }
        }
        return false;
    }

    void moveUp() {
        board.mergeVertical("UP");
    }

    void moveDown() {
        board.mergeVertical("DOWN");
    }

    void moveLeft() {
        board.mergeHorizontal("LEFT");
    }

    void moveRight() {
        board.mergeHorizontal("RIGHT");
    }

    boolean canMove() {
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
