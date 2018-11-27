package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class Game2048BoardManager extends BoardManager implements Serializable {
    private Game2048Board board;
    private Integer dimension;

    Game2048Board getBoard() {return board;}

    Game2048BoardManager() {
        this.board = new Game2048Board();
        this.dimension = board.getDimension();
        board.setUpTiles();
        board.addTile();
        board.addTile();
    }

    Game2048BoardManager(List<Game2048Tile> list){
        Iterator itr = this.board.iterator();
        while (itr.hasNext()){
            itr.next
        }
    }

    int getScore() {return board.getScore();}

    void cheat() {
        board.getTile(0,0).setValue(2048);
    }

    private boolean isFull() {
        return board.findEmpty().size() == 0;
    }

    boolean isLose() {
        return !isWon() && !canMove();
    }

    boolean isWon(){
        for (Game2048Tile tile : board) {
            if (tile.getValue() == 2048) {
                return true;
            }
        }
        return false;
    }

    void move(String direction) {
        board.merge(direction);
    }

    private boolean canMove() {
        if (!isFull()) {
            return true;
        } else {
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    Game2048Tile tile = board.getTile(i, j);
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
