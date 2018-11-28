package fall2018.csc2017.slidingtiles;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class MineBoard extends Observable implements Serializable {
    private int width, height;
    private int mine;
    private int mineLeft;
    private List<MineTile> tiles;
    private List<MineTile> minePosition;

    MineBoard(){}

    MineBoard(int h, int w, int m) {
        mine = m;
        mineLeft = m;
        this.height = h;
        this.width = w;
        this.tiles = new ArrayList<>();
        this.minePosition = new ArrayList<>();
    }

    int getWidth() {return width;}

    int getHeight() {return height;}

    int getMine() {return mine;}

    int getMineLeft() {return mineLeft;}

    List<MineTile> getMinePosition() {return minePosition;}

    List<MineTile> getTiles() {return tiles;}


    MineTile getTile(int position) {
        return tiles.get(position);
    }

    public void setDimension(int h, int w){
        width = w;
        height = h;
    }

    void setMines(int position) {
        int mine = getMine();
        List<MineTile> startNine = getSurround(position);
        startNine.add(getTile(position));
        Random r = new Random();
        List<Integer> randomNum = new ArrayList<>();
        int i = 0;
        while (i < mine) {
            Integer num = r.nextInt(width * height);

            if (!randomNum.contains(num) && !startNine.contains(getTile(num))) {
                randomNum.add(num);
                getTile(num).setMine();
                minePosition.add(getTile(num));
                i++;
            }
        }
    }

    void setTiles() {
        for (int i = 0; i < width*height; i++) {
            MineTile tile = new MineTile();
            tiles.add(tile);
            tile.setPosition(i);
            tile.setBackground();
        }
    }

    void setTiles(List<MineTile> tiles) {
        for (int i = 0; i < width*height; i++) {
            this.tiles.add(tiles.get(i));
            if (tiles.get(i).isMine()) {
                minePosition.add(tiles.get(i));
            }
        }
    }

    public void setMineLeft(int mineLeft) {
        this.mineLeft = mineLeft;
    }

    void reveal(int position) {
        MineTile currTile = getTile(position);
        if (currTile.getNumber() == 0) {
            currTile.reveal();
            for (MineTile tile : getSurround(position)) {
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
        int row = position / width;
        int col = position % width;
        List<MineTile> surround = new ArrayList<>();

        if (row != 0 && col != 0) {
            surround.add(getTiles().get(position-width-1));
        }
        if (row != 0 && col != width - 1) {
            surround.add(getTiles().get(position-width+1));
        }
        if (row != 0) {
            surround.add(getTiles().get(position-width));
        }
        if (row != height - 1 && col != 0) {
            surround.add(getTiles().get(position+width-1));
        }
        if (row != height - 1 && col != width - 1) {
            surround.add(getTiles().get(position+width+1));
        }
        if (row != height- 1) {
            surround.add(getTiles().get(position+width));
        }
        if (col != 0) {
            surround.add(getTiles().get(position-1));
        }
        if (col != width - 1) {
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