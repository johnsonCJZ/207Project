package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

public abstract class BoardManager<T> implements Serializable {
    Double time = (double) 0;

    int dimension;

    T board;

    BoardManager(){}

    public BoardManager(int n) {
        this.dimension = n;
    }

    /**
     * @return the time recorded
     */
    public Double getTime() {
        return time;
    }

    /**
     * Set the time.
     * @param time the time to be set.
     */
    public void setTime(Double time) {
        this.time = time;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getDimension() {
        return dimension;
    }

    public T getBoard() {
        return board;
    }

    public void setBoard(T board) {
        this.board = board;
    }

    /**
     * @return Return whether the player wins according to the game rule.
     */
    abstract boolean isWon();
    abstract boolean isLost();
    // Take in a position or a slide direction.
    abstract void move(Object o);

}
