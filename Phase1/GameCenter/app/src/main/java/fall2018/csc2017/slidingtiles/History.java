package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

public class History implements Serializable {

    public HistoryNode front = null;

    public HistoryNode back = null;

    public int size = 0;

    public int getSize() {
        return size;
    }

    public HistoryNode get(int index) {
        int count = 0;
        HistoryNode curr = this.front;
        while (count < index) {
            curr = curr.next;
            count++;
        }
        return curr;
    }

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

    public void remove(int index) {
        if (this.size == 1) {
            this.front = null;
            this.back = null;
            this.size--;
        }
        else if (index == 0) {
            this.front = this.front.next;
            this.size--;
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
