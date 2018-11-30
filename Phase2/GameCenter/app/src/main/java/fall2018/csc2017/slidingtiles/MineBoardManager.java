package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.List;

public class MineBoardManager extends BoardManager<MineBoard>{
    private List<MineTile> tiles;
    private boolean isFirst = true;
    private boolean lost = false;
    private List<MineTile> minePosition;

    MineBoardManager(){}

    MineBoardManager(int dimension, int numOfMine) {
        super(dimension);
        board = new BuilderBoard().setMine(numOfMine).setMineLeft(numOfMine).setDimension(dimension).setMineTiles().buildMineBoard();
        tiles = board.getTiles();
        this.dimension = dimension;
        minePosition = board.getMinePosition();
        setUpBoard();
    }

    MineBoardManager(int dimension, int mineNum, int mineLeft, double time, List<MineTile> tiles) {
        super(dimension);
        board = new BuilderBoard().setMine(mineNum).setMineLeft(mineLeft).setDimension(dimension).setMineTiles(tiles).buildMineBoard();
        isFirst = false;
        this.dimension = dimension;
        this.time = time;
        minePosition = board.getMinePosition();
    }

    List<MineTile> getMinePosition() {
        return minePosition;
    }

     void setUpBoard() {
        lost = false;
        isFirst = true;
    }

    void mark(int position) {
        board.flag(position);
    }

    public void setTiles(List<MineTile> tiles) {
        this.tiles = tiles;
    }

    public void setDimension(int n){
        dimension = n;
    }

    void setMinePosition(List<MineTile> minePosition) {
        this.minePosition = minePosition;
    }

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

    @Override
    void move(Object position) {
        int po = (int) position;
        if (isFirst) {
            board.setMines(po);
            setNumbers();
            isFirst = false;
        }
        MineTile currTile = board.getTile(po);
        if (!currTile.isFlagged()) {
            board.reveal(po);
            if (currTile.isMine()) {
                lost = true;
            }
        }
    }

    @Override
    boolean isLost() {
        if (lost){
            board.showMines();
        }
        return lost;
    }

    @Override
    boolean isWon() {
        for (int i = 0; i < dimension  * dimension ; i++){
            if (board.getTile(i).isObscured() && !board.getTile(i).isMine()){
                return false;
            }
        }
        return true;
    }
}
