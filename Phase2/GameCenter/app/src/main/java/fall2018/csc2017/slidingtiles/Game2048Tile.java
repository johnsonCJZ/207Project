package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

public class Game2048Tile extends Tile implements Serializable {
    private int x;

    private int y;
    //value can only be 0, 2 or 4.
    private int value;

    int background;

    private boolean fadeIn;

    public Game2048Tile() {
        this.background = R.drawable.tile_2048_0;
        this.value = 0;
        fadeIn = false;
    }

    boolean isEmpty() {
        return value == 0;
    }

    void random() {
        double prob = Math.random();
        if (prob <= 0.2) {
            this.value = 4;
            updateBackground();
        } else {
            this.value = 2;
            updateBackground();
        }
    }

    public int getBackground() {
        return background;
    }

    void updateBackground() {
        switch (value) {
            case 0:
                background = R.drawable.tile_2048_0;
                break;

            case 2:
                background = R.drawable.tile_2048_2;
                break;

            case 4:
                background = R.drawable.tile_2048_4;
                break;

            case 8:
                background = R.drawable.tile_2048_8;
                break;

            case 16:
                background = R.drawable.tile_2048_16;
                break;

            case 32:
                background = R.drawable.tile_2048_32;
                break;

            case 64:
                background = R.drawable.tile_2048_64;
                break;

            case 128:
                background = R.drawable.tile_2048_128;
                break;

            case 256:
                background = R.drawable.tile_2048_256;
                break;

            case 512:
                background = R.drawable.tile_2048_512;
                break;

            case 1024:
                background = R.drawable.tile_2048_1024;
                break;

            case 2048:
                background = R.drawable.tile_2048_2048;
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
        } else if (!(obj instanceof Game2048Tile)) {
            return false;
        } else {
            return ((Game2048Tile) obj).getValue() == this.value;
        }
    }

    public void setAnimation() {
        fadeIn = true;
    }

    public boolean getFadeIn() {
        return fadeIn;
    }


    public void removeFadeIn() {
        fadeIn = false;
    }
}
