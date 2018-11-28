package fall2018.csc2017.slidingtiles;

import java.util.ArrayList;
import java.util.List;

public class MineMemory extends Memory {
    private int width;
    private int height;
    private int mine;
    private List<Boolean> isObscuredOfTiles= new ArrayList<>();
    private List<Integer> numberOfTiles = new ArrayList<>();
    private List<Boolean> isMineOfTiles = new ArrayList<>();
    private List<Boolean> isFlaggedOfTiles = new ArrayList<>();

    public void makeCopy(MineBoardManager manager) {
        width = manager.getBoard().getWidth();
        height = manager.getBoard().getHeight();
        mine = manager.getMinePosition().size();
        for (MineTile tile : manager.getBoard().getTiles()) {
            isObscuredOfTiles.add(tile.isObscured());
            numberOfTiles.add(tile.getNumber());
            isMineOfTiles.add(tile.isMine());
            isFlaggedOfTiles.add(tile.isFlagged());
        }
    }

    public MineBoardManager copy() {
        int h = height;
        int w = width;
        int m = mine;
        List<MineTile> tiles = new ArrayList<>();
        for (int i = 0; i < height*width; i++) {
            tiles.add(new MineTile(isObscuredOfTiles.get(i), numberOfTiles.get(i), isMineOfTiles.get(i), isFlaggedOfTiles.get(i)));
        }
        return new MineBoardManager(h, w, m, tiles);
    }
}
