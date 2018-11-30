package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

abstract public class Tile implements Serializable {
    /**
     * Background photo id.
     */
    int background;

    public int getBackground() {
        return background;
    }

    abstract void setBackground();
}
