package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

public class HistoryNode implements Serializable {

    /**
     * The next HistoryNode linked.
     */
    public HistoryNode next;

    /**
     * the data that the node stores.
     */
    private int[] data;

    /**
     * A new HistoryNode with data being dat.
     * @param dat the data that the node stores
     */
    HistoryNode(int[] dat) {
        this.data = dat;
        this.next=null;
    }

    /**
     * Return the data.
     * @return the data
     */
    int[] getData() {
        return this.data;
    }
}
