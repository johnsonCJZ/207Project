package fall2018.csc2017.slidingtiles;

public class MineSweeperBoard {
    private int width, height;
    private int difficulty;
    private int mine;
    private MineSweeperTile[][] tiles;

    public MineSweeperBoard(int x, int y, int d, int m) {
        mine = m;
        width = x;
        height = y;
        difficulty = d;
        tiles = new MineSweeperTile[width][height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getMine() {
        return mine;
    }

    public MineSweeperTile[][] getTiles() {
        return tiles;
    }
}