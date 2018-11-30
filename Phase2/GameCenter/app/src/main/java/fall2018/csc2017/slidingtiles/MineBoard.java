package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineBoard extends Board<MineTile> {
    private int mineNum;
    private int mineLeft;
    private List<MineTile> minePosition = new ArrayList<>();

    int getMineNum() {return mineNum;}

    int getMineLeft() {return mineLeft;}

    List<MineTile> getMinePosition() {return minePosition;}

    MineTile getTile(int position) {
        int row = position / getDimension();
        int col = position % getDimension();
        return super.getTile(row, col);
    }

    void setMinePosition(List<MineTile> minePosition) {
        this.minePosition = minePosition;
    }

    void setMines(int position) {
        int mineNum = getMineNum();
        List<MineTile> startNine = getSurround(position);
        startNine.add(getTile(position));
        Random r = new Random();
        List<Integer> randomNums = new ArrayList<>();
        int i = 0;
        while (i < mineNum) {
            Integer num = r.nextInt(getDimension() * getDimension());

            if (!randomNums.contains(num) && !startNine.contains(getTile(num))) {
                randomNums.add(num);
                getTile(num).setMine();
                minePosition.add(getTile(num));
                i++;
            }
        }
    }

    void setMineNum(int mineNum) {
        this.mineNum = mineNum;
    }

    public void setMineLeft(int mineLeft) {
        this.mineLeft = mineLeft;
    }

    void reveal(int position) {
        MineTile currTile = getTile(position);
        if (currTile.getNumber() == 0) {
            currTile.reveal();
            for (Tile tileT : getSurround(position)) {
                MineTile tile = (MineTile) tileT;
                if(tile.isObscured() && !tile.isFlagged()) {
                    tile.reveal();
                    if (tile.getNumber() == 0) {
                        reveal(tile.getPosition());
                    }
                }
            }
        }
        else {
            currTile.reveal();
        }

        setChanged();
        notifyObservers();
    }

    void flag(int position) {
        getTile(position).flag();
        if (getTile(position).isFlagged()){mineLeft--;}
        else {mineLeft++;}

        setChanged();
        notifyObservers();
    }

    List<MineTile> getSurround(int position) {
        int dimension = getDimension();
        int row = position / dimension ;
        int col = position % dimension ;
        List<MineTile> surround = new ArrayList<>();

        if (row != 0 && col != 0) {
            surround.add(getTiles().get(position-dimension -1));
        }
        if (row != 0 && col != dimension  - 1) {
            surround.add(getTiles().get(position-dimension +1));
        }
        if (row != 0) {
            surround.add(getTiles().get(position-dimension ));
        }
        if (row != dimension  - 1 && col != 0) {
            surround.add(getTiles().get(position+dimension -1));
        }
        if (row != dimension  - 1 && col != dimension  - 1) {
            surround.add(getTiles().get(position+dimension +1));
        }
        if (row != dimension - 1) {
            surround.add(getTiles().get(position+dimension ));
        }
        if (col != 0) {
            surround.add(getTiles().get(position-1));
        }
        if (col != dimension  - 1) {
            surround.add(getTiles().get(position+1));
        }
        return surround;
    }

    void showMines(){
        for (MineTile tile : minePosition){
            tile.showMine();
        }

        setChanged();
        notifyObservers();
    }
}