package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.Observable;

public class MineSweeperBoard extends Observable implements Serializable {
    private int width, height;
    private int mine;
    private MineSweeperTile[][] tiles;

    public MineSweeperBoard(int x, int y, int m) {
        mine = m;
        height = x;
        width = y;
        tiles = new MineSweeperTile[height][width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMine() {
        return mine;
    }

    public MineSweeperTile[][] getTiles() {
        return tiles;
    }

    public MineSweeperTile getTile(int position) {
        int row = position / width;
        int col = position % width;
        return tiles[row][col];
    }

    public void setTiles(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                MineSweeperTile tile = new MineSweeperTile();
                tiles[i][j] = tile;
                tile.setPosition((i * width + j));
                tile.setBackground();
            }
        }
    }

}