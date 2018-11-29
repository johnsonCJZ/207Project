package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.List;

public class Game2048BoardManager extends BoardManager implements Serializable {
    private Game2048Board board;
    final private Integer DIMENSION = 4;
//    private Double time;

    Game2048Board getBoard() {return board;}

    public Game2048BoardManager() {
        super(4);
        this.board = new BuilderBoard().build2048Board();
        board.addTile();
        board.addTile();
    }

    Game2048BoardManager(double time, int score, List<Integer> list){
        super(4);
        this.time = time;
        this.board = new BuilderBoard().build2048Board();
        this.board.setScore(score);
        this.board.setUpTiles(list);
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

    @Override
    boolean isLost() {
        return !isWon() && !canMove();
    }

    @Override
    boolean isWon(){
        for (Game2048Tile tile : board) {
            if (tile.getValue() == 2048) {
                return true;
            }
        }
        return false;
    }

    @Override
    void move(Object direction) {
        board.merge((String) direction);
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

}
