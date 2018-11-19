package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

public class Tile2048 implements Serializable {
    private int x;

    private int y;
    //value can only be 0, 2 or 4.
    private int value;

    int background;

    public Tile2048() {
        this.background = R.drawable.tile_0;
        this.value = 0;
    }

    boolean isEmpty() {
        return value == 0;
    }

    void random() {
        double prob = Math.random();
        if (prob <= 0.2) {
            this.value = 4;
            updateBackground();
        }
        else{
            this.value = 2;
            updateBackground();
        }
    }

    public int getBackground() {
        return background;
    }

    void updateBackground() {
        switch (value) {
            case 0: background = R.drawable.tile_0;
            break;

            case 2: background = R.drawable.tile_2;
            break;

            case 4: background = R.drawable.tile_4;
            break;

            case 8: background = R.drawable.tile_8;
            break;

            case 16: background = R.drawable.tile_16;
            break;

            case 32: background = R.drawable.tile_32;
            break;

            case 64: background = R.drawable.tile_64;
            break;

            case 128: background = R.drawable.tile_128;
            break;

            case 512: background = R.drawable.tile_512;
            break;

            case 1024: background = R.drawable.tile_1024;
            break;

            case 2048: background = R.drawable.tile_2048;
            break;
        }
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        updateBackground();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        else if (!(obj instanceof Tile2048)) {
            return false;
        }
        else {
            return ((Tile2048) obj).getValue() == this.value;
        }
    }
}
