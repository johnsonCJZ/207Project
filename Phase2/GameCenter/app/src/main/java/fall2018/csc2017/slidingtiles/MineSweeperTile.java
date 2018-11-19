package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

public class MineSweeperTile implements Serializable {
    /**
     * The background id to find the tile image.
     */
    private int background;

    /**
     * Whether the tile is a mine or flagged or obscured.
     */
    private boolean isMine, isFlagged, isObscured;

    /**
     * The number of mines around the tile. -1 if the tile is a mine.
     */
    private int number;

    /**
     * The position of the tile in a MineSweeperBoard.
     */
    private int position;

    MineSweeperTile(){
        isMine = false;
        isFlagged = false;
        isObscured = true;
        number = 0;
    }

    void setBackground() {
        if(!isObscured()) {
            switch (number) {
                case -1:
                    background = R.drawable.mine_b;
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
        else if (isFlagged()){
            background = R.drawable.mine_d;

        }
        else {background = R.drawable.mine_mask;}
    }

    void showMine(){
        if (isObscured()){
            background = R.drawable.mine_b2;
        }
        else {
            background = R.drawable.mine_b;
        }
    }

    public int getBackground() {
        return background;
    }

    void setMine(){
        isMine = true;
    }

    void flag()
    {
        isFlagged = true;
        setBackground();
    }

    void unFlag()
    {
        isFlagged = false;
        setBackground();
    }

    void reveal()
    {
        isObscured = false;
        setBackground();
    }

    public void setNumber(int i)
    {
        number = i;
        setBackground();
    }

    boolean isMine()
    {
        return isMine;
    }

    boolean isFlagged()
    {
        return isFlagged;
    }

    boolean isObscured()
    {
        return isObscured;
    }

    int getNumber()
    {
        return number;
    }

    int getPosition() {
        return position;
    }

    void setPosition(int position) {
        this.position = position;
    }
}
