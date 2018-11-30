package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineBoard extends Board<MineTile> implements Serializable, IObservable<MineBoard> {
    private int mineNum;
    private int mineLeft;
    private List<MineTile> minePosition = new ArrayList<>();
    private boolean changed = false;
    private ArrayList<IObserver> observers = new ArrayList<>();

    MineBoard(){}

    int getMineNum() {return mineNum;}

    int getMineLeft() {return mineLeft;}

    List<MineTile> getMinePosition() {return minePosition;}

    MineTile getTile(int position) {
        return tiles.get(position);
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
            Integer num = r.nextInt(dimension * dimension);

            if (!randomNums.contains(num) && !startNine.contains(getTile(num))) {
                randomNums.add(num);
                getTile(num).setMine();
                minePosition.add(getTile(num));
                i++;
            }
        }
    }

    public void setMineNum(int mineNum) {
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

    @Override
    public void addObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void deleteObserver(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        if(changed){
            int i = observers.size();
            while(--i >= 0){
                observers.get(i).update(this);
            }

        }
    }

    @Override
    public void clearChanged() {
        changed = false;
    }

    @Override
    public boolean hasChanged() {
        return changed;
    }

    @Override
    public void setChanged() {
        changed = true;
    }
}