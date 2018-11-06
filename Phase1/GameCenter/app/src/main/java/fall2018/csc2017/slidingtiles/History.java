package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/**
 * The History linked list.
 */
public class History implements Serializable {

    /**
     * The front of the History.
     */
    public HistoryNode front = null;

    /**
     * The back of the History.
     */
    public HistoryNode back = null;

    /**
     * The size of the History.
     */
    private int size = 0;

    /**
     * Return the size of the History.
     * @return the size of the History
     */
    public int getSize() {
        return size;
    }

    /**
     * Return the HistoryNode at the index of the History.
     * @param index the index of the HistoryNode returned
     * @return the HistoryNode at the index
     */
    public HistoryNode get(int index) {
        int count = 0;
        HistoryNode curr = this.front;
        while (count < index) {
            curr = curr.next;
            count++;
        }
        return curr;
    }

    /**
     * Add a new HistoryNode node to the end of the History.
     * @param node the new HistoryNode to be added
     */
    public void add(HistoryNode node) {
        if (this.size == 0) {
            this.front = node;
            this.back = node;
            this.size++;
        }
        else {
            this.back.next = node;
            this.back = node;
            this.size++;
        }
    }

    /**
     * Clear the History.
     */
    public  void clear() {
        if (size != 0) {
            front = null;
            back = null;
            size = 0;
        }
    }

    /**
     * Remove the HistoryNode at the index of the History.
     * @param index the index of the History where the HistoryNode to be removed
     */
    public void remove(int index) {
        if (index == 0) {
            clear();
        }
        else if (index == this.size-1) {
            this.back = this.get(this.size-2);
            this.back.next = null;
            this.size--;
        }
        else {
            this.back = this.get(index-1);
            this.back.next = null;
            this.size = index;
        }
    }
}
