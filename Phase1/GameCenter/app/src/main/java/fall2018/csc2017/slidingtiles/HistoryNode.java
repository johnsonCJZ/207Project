package fall2018.csc2017.slidingtiles;

public class HistoryNode {

    /**
     * The next HistoryNode linked.
     */
    public HistoryNode next = null;

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
    }

    /**
     * Return the data.
     * @return the data
     */
    int[] getData() {
        return this.data;
    }
}
