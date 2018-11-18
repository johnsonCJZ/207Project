package fall2018.csc2017.slidingtiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineSweeperManager {
    private MineSweeperBoard board;
    private MineSweeperTile[][] tiles = board.getTiles();
    private boolean finished;
    private int width;
    private int height;
    private double time;
    private boolean isFirst = true;

    public MineSweeperManager(int x, int y, int m) {
        board = new MineSweeperBoard(x,y,m);
        width = board.getWidth();
        height = board.getHeight();
        setUpBoard();
    }

    void setMines(int position){
        int mine = board.getMine();
        List<MineSweeperTile> startNine = getSurround(position);
        Random r = new Random();
        List<Integer> randomNum = new ArrayList<>();
        int i = 0;

        startNine.add(board.getTile(position));

        while (i < mine) {
            Integer num = r.nextInt(width*height);

            if (!randomNum.contains(num) && !startNine.contains(board.getTile(num))){
                randomNum.add(num);
                board.getTile(num).setMine();
                i++;
            }
        }
    }

    List<MineSweeperTile> getSurround(int position) {
        int row = position / width;
        int col = position / width;
        List<MineSweeperTile> surround = new ArrayList<>();

        if (row != 0 && col != 0){
            surround.add(board.getTiles()[row-1][col-1]);
        }
        if (row != 0 && col != width-1){
            surround.add(board.getTiles()[row-1][col+1]);
        }
        if (row != 0){surround.add(board.getTiles()[row-1][col]);}
        if (row != height-1 && col != 0){
            surround.add(board.getTiles()[row+1][col-1]);
        }
        if (row != height-1 && col != width-1){
            surround.add(board.getTiles()[row+1][col+1]);
        }
        if (row != height-1){surround.add(board.getTiles()[row+1][col]);}
        if (col != 0){surround.add(board.getTiles()[row][col-1]);}
        if (col != width-1){surround.add(board.getTiles()[row][col+1]);}
        return surround;
    }

    void reveal(MineSweeperTile currTile){
        if (currTile.getNumber() == 0){
            currTile.reveal();
            for (MineSweeperTile tile : getSurround(currTile.getPosition())){
                if (tile.getNumber() == 0){
                    reveal(tile);}
            }
        }
        else {currTile.reveal();}
    }

    void touchmove(int position){
        if (isFirst){
            setMines(position);
            setNumbers();
            isFirst = false;
        }
        MineSweeperTile currTile = board.getTile(position);
        if (currTile.isMine()){}
        else reveal(currTile);
    }
    MineSweeperBoard getBoard(){
        return board;
    }

    double getTime() {
        return time;
    }

    void setTime(double time) {
        this.time = time;
    }

    void setUpBoard()
    {
        finished = false;
        isFirst = true;

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                MineSweeperTile c = new MineSweeperTile();
                tiles[i][j] = c;
                c.setPosition((i*width + j));
            }
        }
    }

    void setNumbers()
    {
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                //if the tile is a mine, set the number to be -1
                if (tiles[i][j].isMine()) {
                    tiles[i][j].setNumber(-1);
                }

                //if the tile isn't a mine, set the number to be the number of mines surrounds it.
                else {
                    int count = 0;
                    if (i > 0 && j > 0 && tiles[i - 1][j - 1].isMine()) count++; //upper-left tile
                    if (j > 0 && tiles[i][j - 1].isMine()) count++; //left tile
                    if (i < width - 1 && j > 0 && tiles[i + 1][j - 1].isMine())
                        count++; //lower-left
                    if (i > 0 && tiles[i - 1][j].isMine()) count++; // upper tile
                    if (i < width - 1 && tiles[i + 1][j].isMine()) count++; // lower tile
                    if (i > 0 && j < height - 1 && tiles[i - 1][j + 1].isMine())
                        count++; //upper-right
                    if (j < height - 1 && tiles[i][j + 1].isMine()) count++; //right tile
                    if (i < width - 1 && j < height - 1 && tiles[i + 1][j + 1].isMine())
                        count++; //lower-right tile
                    tiles[i][j].setNumber(count);
                }

                //if the tile is not surrounded by any mine.
                if (tiles[i][j].getNumber() == 0)
                {
//                    tiles[i][j].reveal();
                }
            }
        }

//        for (int i = 0; i < width; i++)
//        {
//            for (int j = 0; j < height; j++)
//            {
//                if (i > 0 &&	j > 0 && tiles[i - 1][j - 1].getNumber() == 0) tiles[i][j].reveal();
//                if (j > 0 && tiles[i][j - 1].getNumber() == 0) tiles[i][j].reveal();
//                if (i < width - 1 && j > 0 && tiles[i + 1][j - 1].getNumber() == 0) tiles[i][j].reveal();
//                if (i > 0 && tiles[i - 1][j].getNumber() == 0) tiles[i][j].reveal();
//                if (i < width - 1 && tiles[i + 1]	[j]		.getNumber() == 0) tiles[i][j].reveal();
//                if (i > 0 && j < height - 1 && tiles[i - 1][j + 1].getNumber() == 0) tiles[i][j].reveal();
//                if (j < height - 1 && tiles[i][j + 1].getNumber() == 0) tiles[i][j].reveal();
//                if (i < width - 1 && j < height - 1 && tiles[i + 1][j + 1]	.getNumber() == 0) tiles[i][j].reveal();
//            }
//        }
    }

    void select(int x, int y)
    {
        if (tiles[x][y].isFlagged())
            return;
        tiles[x][y].reveal();
        resetMarks();

        if (tiles[x][y].isMine())
        {
            loose();
        }
        else if (won())
        {
            win();
        }
    }

    void loose()
    {
        finished = true;
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if (!tiles[i][j].isObscured()) tiles[i][j].unFlag();
                tiles[i][j].reveal();
            }
        }
        setUpBoard();
    }

    void win()
    {
        finished = true;
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                tiles[i][j].reveal();
                if (!tiles[i][j].isMine()) tiles[i][j].unFlag();
            }
        }
        setUpBoard();
    }

    boolean won()
    {
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if (tiles[i][j].isObscured() && !tiles[i][j].isMine())
                {
                    return false;
                }
            }
        }
        return true;
    }

    void mark(int x, int y)
    {
        if (tiles[x][y].isFlagged()) tiles[x][y].unFlag();
        else if (tiles[x][y].isObscured()) tiles[x][y].flag();

        resetMarks();
    }

    void resetMarks()
    {
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if (!tiles[i][j].isObscured()) tiles[i][j].unFlag();
            }
        }
    }

    boolean isFinished()
    {
        return finished;
    }
}
