package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BuilderBoard {
    private int dimension;
    private int mineWidth, mineHeight;
    private int mine;
    private int mineLeft;
    private List<Tile> tiles;
    private List<MineTile> minePosition = new ArrayList<>();
    private int game2048Score;
    private boolean isChanged = false;

    public BuilderBoard setDimension(int dimension) {
        this.dimension = dimension;
        return this;
    }

    public BuilderBoard setDimension(int w, int h) {
        this.mineWidth = w;
        this.mineHeight = h;
        return this;
    }

    public BuilderBoard setMine(int mine) {
        this.mine = mine;
        return this;
    }

    public BuilderBoard setMineLeft(int mineLeft) {
        this.mineLeft = mineLeft;
        return this;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public void setSlidingTiles() {
        tiles = new ArrayList<>();
    }

    public void set2048Tiles() {
        tiles = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
                tiles.add(new Game2048Tile());

        }
    }

    public void setMineTiles(){
        tiles = new ArrayList<>();
    }

    public BuilderBoard setMinePosition(List<MineTile> minePosition) {
        this.minePosition = minePosition;
        return this;
    }

    public BuilderBoard setGame2048Score(int game2048Score) {
        this.game2048Score = game2048Score;
        return this;
    }

    public BuilderBoard setChanged(boolean changed) {
        isChanged = changed;
        return this;
    }

    public SlidingBoard buildSlidingBoard() {
        SlidingBoard slidingBoard = new SlidingBoard();
        slidingBoard.setDimension(dimension);
        slidingBoard.setSlidingTiles((List<SlidingTile>) (List<?>) tiles);
        return slidingBoard;
    }

    public Game2048Board build2048Board() {
        Game2048Board game2048Board = new Game2048Board();
        set2048Tiles();
        game2048Board.setTiles((List<Game2048Tile>) (List<?>) tiles);
        return game2048Board;
    }

//    public MineBoard buildMineBoard(){
//        MineBoard mineBoard = new MineBoard();
//        mineBoard.setMines(mine);
//        mineBoard.setMineLeft(mineLeft);
//        mineBoard.setDimension(mineHeight,mineWidth);
//        mineBoard.setTiles((MineTile[][]) tiles);
//        return mineBoard;
//    }
}
