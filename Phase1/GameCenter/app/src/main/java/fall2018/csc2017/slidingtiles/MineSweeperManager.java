package fall2018.csc2017.slidingtiles;

import java.util.Random;

public class MineSweeperManager {
    private MineSweeperTile[][] tiles;
    private MineSweeperBoard board;
    private boolean finished;
    private int width, height, difficulty;
    private double time;

    public MineSweeperManager(int x, int y, int d, int m) {
        reset();
        board = new MineSweeperBoard(x,y,d, m);
        width = board.getWidth();
        height = board.getHeight();
        difficulty = board.getDifficulty();

    }

    public MineSweeperBoard getBoard(){
        return board;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void reset()
    {
        Random random = new Random();
        finished = false;

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                MineSweeperTile c = new MineSweeperTile();
                tiles[i][j] = c;
                int r = random.nextInt(100);

                if (r < difficulty)
                {
                    tiles[i][j].setMine();
                }
            }
        }
        setNumbers();
    }

    private void setNumbers()
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

    public void select(int x, int y)
    {
        if (tiles[x][y].isFlagged()) return;
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

    private void loose()
    {
        finished = true;
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if (!tiles[i][j].isObscured()) tiles[i][j].unflag();
                tiles[i][j].reveal();
            }
        }
        reset();
    }

    private void win()
    {
        finished = true;
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                tiles[i][j].reveal();
                if (!tiles[i][j].isMine()) tiles[i][j].unflag();
            }
        }
        reset();
    }

    private boolean won()
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

    public void mark(int x, int y)
    {
        if (tiles[x][y].isFlagged()) tiles[x][y].unflag();
        else if (tiles[x][y].isObscured()) tiles[x][y].flag();

        resetMarks();
    }

    private void resetMarks()
    {
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if (!tiles[i][j].isObscured()) tiles[i][j].unflag();
            }
        }
    }

    public boolean isFinished()
    {
        return finished;
    }
}
