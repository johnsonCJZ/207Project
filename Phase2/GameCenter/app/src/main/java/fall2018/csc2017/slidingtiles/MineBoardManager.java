package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.List;

public class MineBoardManager extends BoardManager implements Serializable {
    private MineBoard board;
    private List<MineTile> tiles;
    private int dimension;
    private int time;
    private boolean isFirst = true;
    private boolean lost = false;
    private List<MineTile> minePosition;

    MineBoardManager(int size, int mineNum) {
        board = new BuilderBoard().setMine(mineNum).setMineLeft(mineNum).setDimension(size).setMineTiles().buildMineBoard();
        tiles=board.getTiles();
        dimension = size;
        minePosition = board.getMinePosition();
        setUpBoard();
    }

    MineBoardManager(int size, int mineNum, int mineLeft, int time, List<MineTile> tiles) {
        board = new BuilderBoard().setMine(mineNum).setMineLeft(mineLeft).setDimension(size).setMineTiles(tiles).buildMineBoard();
        isFirst = false;
        this.tiles = board.getTiles();
        dimension = size;
        this.time = time;
        minePosition = board.getMinePosition();
    }

    MineBoard getBoard() {
        return board;
    }

    List<MineTile> getMinePosition() {
        return minePosition;
    }

    int getTime() { return time; }

    void setTime(int time) {this.time = time;}

    private void setUpBoard() {
        lost = false;
        isFirst = true;
//        board.setTiles();
    }


    void touchMove(int position) {
        if (isFirst) {
            board.setMines(position);
            setNumbers();
            isFirst = false;
        }
        MineTile currTile = board.getTile(position);
        if (!currTile.isFlagged()) {
            board.reveal(position);
            if (currTile.isMine()) {
                lost = true;
            }
        }
    }

    void mark(int position) {board.flag(position);}

    private void setNumbers() {
        for (int pos = 0; pos < dimension *dimension ; pos++) {
            //if the tile is a mine, set the number to -1.
            if (tiles.get(pos).isMine()) {
                tiles.get(pos).setNumber(-1);
            }

            //if the tile isn't a mine, set the number to be the number of mines surrounds it.
            else {
                int count = 0;
                int i = pos / dimension ;
                int j = pos % dimension ;

                if (i > 0 && j > 0 && tiles.get(pos-dimension -1).isMine()) count++; //upper-left tile
                if (j > 0 && tiles.get(pos-1).isMine()) count++; //left tile
                if (i < dimension  - 1 && j > 0 && tiles.get(pos+dimension -1).isMine())
                    count++; //lower-left
                if (i > 0 && tiles.get(pos-dimension ).isMine()) count++; // upper tile
                if (i < dimension  - 1 && tiles.get(pos+dimension ).isMine()) count++; // lower tile
                if (i > 0 && j < dimension  - 1 && tiles.get(pos-dimension +1).isMine())
                    count++; //upper-right
                if (j < dimension  - 1 && tiles.get(pos+1).isMine()) count++; //right tile
                if (i < dimension  - 1 && j < dimension  - 1 && tiles.get(pos+dimension +1).isMine())
                    count++; //lower-right tile
                tiles.get(pos).setNumber(count);
            }
        }
    }

    boolean isLost() {
        if (lost){
            board.showMines();
        }
        return lost;
    }

    boolean isWon() {
        for (int i = 0; i < dimension  * dimension ; i++){
            if (board.getTile(i).isObscured() && !board.getTile(i).isMine()){
                return false;
            }
        }
        return true;
    }
}
