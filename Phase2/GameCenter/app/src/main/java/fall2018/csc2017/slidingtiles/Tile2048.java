package fall2018.csc2017.slidingtiles;

class   Tile2048 {
    private int x;

    private int y;
    //value can only be 0, 2 or 4.
    private int value;

    Tile2048() {

    }

    boolean isEmpty() {
        return value == 0;
    }

    void random() {
        double prob = Math.random();
        if (prob <= 0.2) {
            this.value = 4;
        }
        else{
            this.value = 2;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        else if (!(obj instanceof Tile2048)) {
            return false;
        }
        else {
            return ((Tile2048) obj).getValue() == this.value;
        }
    }
}
