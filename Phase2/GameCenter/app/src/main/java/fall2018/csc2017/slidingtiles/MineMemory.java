package fall2018.csc2017.slidingtiles;

import java.util.List;

public class MineMemory extends Memory {
    private int width;
    private int height;
    private int mine;
    private List<MineTile> tiles;

    public void makeCopy(MineBoardManager manager) {
        width = manager.getBoard().getW();
        height = manager.getBoard().getH();
        mine = manager.getMinePosition().size();
        this.tiles = manager.getBoard().getTiles();
    }

    public MineBoardManager copy() {
        int h = height;
        int w = width;
        int m = mine;
        List<MineTile> tiles = this.tiles;
        return new MineBoardManager(h, w, m, tiles);
    }
}
