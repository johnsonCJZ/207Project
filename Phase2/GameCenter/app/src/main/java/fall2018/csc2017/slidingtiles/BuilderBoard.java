package fall2018.csc2017.slidingtiles;

import java.util.ArrayList;
import java.util.List;

public class BuilderBoard {
    private int dimension;
    private int mine;
    private int mineLeft;
    private List<Tile> tiles = new ArrayList<>();
    private List<MineTile> minePosition = new ArrayList<>();
    private int game2048Score;
    private boolean isChanged = false;

    public BuilderBoard setDimension(int dimension) {
        this.dimension = dimension;
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

    public BuilderBoard setTiles(List<Tile> tiles) {
        this.tiles = tiles;
        return this;
    }

    BuilderBoard setMineTiles(List<MineTile> tiles) {
        for (int i = 0; i < dimension * dimension; i++) {
            this.tiles.add(tiles.get(i));
            if (tiles.get(i).isMine()) {
                minePosition.add(tiles.get(i));
            }
        }
        return this;
    }

    private BuilderBoard set2048Tiles() {
        tiles = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            tiles.add(new Game2048Tile());

        }
        return this;
    }

    BuilderBoard setMineTiles() {
        for (int i = 0; i < dimension * dimension; i++) {
            MineTile tile = new MineTile();
            tiles.add(tile);
            tile.setPosition(i);
            tile.setBackground();
        }
        return this;
    }

    public SlidingBoard buildSlidingBoard() {
        SlidingBoard slidingBoard = new SlidingBoard();
        slidingBoard.setDimension(dimension);
        slidingBoard.setTiles((List<SlidingTile>) (List<?>) tiles);
        return slidingBoard;
    }

    public Game2048Board build2048Board() {
        Game2048Board game2048Board = new Game2048Board();
        set2048Tiles();
        game2048Board.setDimension(4);
        game2048Board.setTiles((List<Game2048Tile>) (List<?>) tiles);
        return game2048Board;
    }

    public MineBoard buildMineBoard() {
        MineBoard mineBoard = new MineBoard();
        mineBoard.setDimension(dimension);
        mineBoard.setMineNum(mine);
        mineBoard.setMineLeft(mineLeft);
        mineBoard.setTiles((List<MineTile>) (List<?>) tiles);
        mineBoard.setMinePosition(minePosition);
        return mineBoard;
    }
}
