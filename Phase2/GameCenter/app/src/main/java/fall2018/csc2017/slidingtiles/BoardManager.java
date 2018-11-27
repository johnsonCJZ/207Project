package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

public abstract class BoardManager implements Serializable {
    String type;

    public BoardManager(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
