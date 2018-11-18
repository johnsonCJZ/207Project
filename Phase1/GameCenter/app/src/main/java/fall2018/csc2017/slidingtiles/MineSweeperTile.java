package fall2018.csc2017.slidingtiles;

public class MineSweeperTile {
    /**
     * The background id to find the tile image.
     */
    private int background;

    private boolean isMine, isFlagged, isObscured;

    private int number;

    private int position;

    public MineSweeperTile()
    {
        isMine = false;
        isFlagged = false;
        isObscured = true;
        number = 0;

    }

    public void setBackground(int background) {
        switch(number) {
            case -1:
                background = R.drawable.mine_b2;
                break;
            case 0:
                background = R.drawable.mine_base;
                break;
            case 1:
                background = R.drawable.mine_1;
                break;
            case 2:
                background = R.drawable.mine_2;
                break;
            case 3:
                background = R.drawable.mine_3;
                break;
            case 4:
                background = R.drawable.mine_4;
                break;
            case 5:
                background = R.drawable.mine_5;
                break;
            case 6:
                background = R.drawable.mine_6;
                break;
            case 7:
                background = R.drawable.mine_7;
                break;
            case 8:
                background = R.drawable.mine_8;
                break;
        }
    }

    public void setMine(){
        isMine = true;
    }

    public void flag()
    {
        isFlagged = true;
    }

    public void unFlag()
    {
        isFlagged = false;
    }

    public void reveal()
    {
        isObscured = false;
    }

    public void setNumber(int i)
    {
        number = i;
    }

    public boolean isMine()
    {
        return isMine;
    }

    public boolean isFlagged()
    {
        return isFlagged;
    }

    public boolean isObscured()
    {
        return isObscured;
    }

    public int getNumber()
    {
        return number;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
