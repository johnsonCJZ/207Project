package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Memory<T> implements Serializable {

    /**
     * Takes in a BoardManager and store all the information needed for clone the BoardManager.
     * @param manager the BoardManager to be cloned
     */
    public abstract void makeCopy(T manager);

    /**
     * Return a new BoardManager cloned from the manager in makeCopy().
     * @return a new BoardManager cloned from the manager in makeCopy()
     */
    public abstract T copy();


}
