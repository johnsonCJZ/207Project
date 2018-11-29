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

    MineBoardManager(int d, int m) {
        BuilderBoard builderBoard = new BuilderBoard();
        builderBoard.setMine(m);
        builderBoard.setMineLeft(m);
        builderBoard.setDimension(d);
        builderBoard.setMineTiles();
        board = builderBoard.buildMineBoard();
        tiles=board.getTiles();
        dimension = d;
        minePosition = board.getMinePosition();
        setUpBoard();
    }

    MineBoardManager(int d, int m, int mLeft, int time, List<MineTile> tiles) {
        BuilderBoard builderBoard = new BuilderBoard();
        builderBoard.setMine(m);
        builderBoard.setMineLeft(mLeft);
        builderBoard.setDimension(d);
        builderBoard.setMineTiles(tiles);
        board = builderBoard.buildMineBoard();
        isFirst = false;
        this.tiles = board.getTiles();
        dimension = d;
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
