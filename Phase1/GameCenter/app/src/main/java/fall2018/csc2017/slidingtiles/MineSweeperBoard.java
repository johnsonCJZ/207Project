package fall2018.csc2017.slidingtiles;

public class MineSweeperBoard {
    private int width, height;
    private int mine;
    private MineSweeperTile[][] tiles;

    public MineSweeperBoard(int x, int y, int m) {
        mine = m;
        width = x;
        height = y;
        tiles = new MineSweeperTile[width][height];
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
}