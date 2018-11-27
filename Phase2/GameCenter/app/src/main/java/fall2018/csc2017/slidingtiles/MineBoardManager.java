package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.List;

public class MineBoardManager extends BoardManager implements Serializable {
    private MineBoard board;
    private List<MineTile> tiles;
    private int width;
    private int height;
    private int time;
    private boolean isFirst = true;
    private boolean lost = false;
    private List<MineTile> minePosition;

    MineBoardManager(int x, int y, int m) {
        super("Mine");
        board = new MineBoard(x, y, m);
        tiles=board.getTiles();
        width = board.getW();
        height = board.getH();
        minePosition = board.getMinePosition();
        setUpBoard();
    }

    MineBoardManager(int x, int y, int m, List<MineTile> tiles) {
        super("Mine");
        board = new MineBoard(x, y, m);
        board.setTiles(tiles);
        this.tiles = board.getTiles();
        width = board.getW();
        height = board.getH();
        minePosition = board.getMinePosition();
    }

    MineBoard getBoard() {
        return board;
    }

    int getTime() { return time; }

    void setTime(int time) {this.time = time;}

    private void setUpBoard() {
        lost = false;
        isFirst = true;
        board.setTiles();
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
        for (int pos = 0; pos < height*width; pos++) {
            //if the tile is a mine, set the number to -1.
            if (tiles.get(pos).isMine()) {
                tiles.get(pos).setNumber(-1);
            }

            //if the tile isn't a mine, set the number to be the number of mines surrounds it.
            else {
                int count = 0;
                int i = pos / width;
                int j = pos % width;

                if (i > 0 && j > 0 && tiles.get(pos-width-1).isMine()) count++; //upper-left tile
                if (j > 0 && tiles.get(pos-1).isMine()) count++; //left tile
                if (i < height - 1 && j > 0 && tiles.get(pos+width-1).isMine())
                    count++; //lower-left
                if (i > 0 && tiles.get(pos-width).isMine()) count++; // upper tile
                if (i < height - 1 && tiles.get(pos+width).isMine()) count++; // lower tile
                if (i > 0 && j < width - 1 && tiles.get(pos-width+1).isMine())
                    count++; //upper-right
                if (j < width - 1 && tiles.get(pos+1).isMine()) count++; //right tile
                if (i < height - 1 && j < width - 1 && tiles.get(pos+width+1).isMine())
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
        for (int i = 0; i < height * width; i++){
            if (board.getTile(i).isObscured() && !board.getTile(i).isMine()){
                return false;
            }
        }
        return true;
    }
}
