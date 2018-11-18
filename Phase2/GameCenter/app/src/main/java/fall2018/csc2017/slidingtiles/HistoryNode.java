package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/**
 * The HistoryNode.
 */
class HistoryNode implements Serializable {

    /**
     * The next HistoryNode linked.
     */
    HistoryNode next;

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
