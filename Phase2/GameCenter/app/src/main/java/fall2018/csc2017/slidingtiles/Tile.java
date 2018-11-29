package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

abstract public class Tile implements Serializable {
    int background;

    public int getBackground() {
        return background;
    }

    abstract void setBackground();
}
