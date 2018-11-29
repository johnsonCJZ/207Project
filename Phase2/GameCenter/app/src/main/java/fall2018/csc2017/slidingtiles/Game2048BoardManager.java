package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.List;

public class Game2048BoardManager extends BoardManager implements Serializable {
    private Game2048Board board;
    final private Integer DIMENSION = 4;
    private Double time;

    Game2048Board getBoard() {return board;}

    public Game2048BoardManager() {
        this.time = 0.0;
        BuilderBoard builder = new BuilderBoard();
        this.board = builder.build2048Board();
        board.addTile();
        board.addTile();
    }

    Game2048BoardManager(double time, List<Integer> list){
        this.time = time;
        BuilderBoard builder = new BuilderBoard();
        this.board = builder.build2048Board();
        this.board.setUpTiles(list);
    }

    public Double getTime() {
        return time;
    }

    int getScore() {
        return board.getScore();
    }

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
            for (int i = 0; i < DIMENSION; i++) {
                for (int j = 0; j < DIMENSION; j++) {
                    Game2048Tile tile = board.getTile(i, j);
                    if ((i < DIMENSION - 1 && tile.equals(board.getTile(i + 1, j)))
                            || (j < DIMENSION - 1 && tile.equals(board.getTile(i, j+1)))) {
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
