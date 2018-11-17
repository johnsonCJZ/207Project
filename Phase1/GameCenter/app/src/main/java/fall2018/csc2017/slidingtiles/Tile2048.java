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
}
