package fall2018.csc2017.slidingtiles;

import java.util.Random;

public class MineSweeper {
    private static final long serialVersionUID = 1L;
    private int width, height;
    private MineSweeperTile[][] tiles;
    private int difficulty;
    private MineSweeperBoard board;
//    private JButton reset;
    private boolean finished;

    public MineSweeper(int x, int y, int d) {
        width = x;
        height = y;
        difficulty = d;
        tiles = new MineSweeperTile[width][height];

        reset();

        board = new MineSweeperBoard(this);
//        reset = new JButton("Reset");

//        add(board, BorderLayout.CENTER);
//        add(reset, BorderLayout.SOUTH);

//        reset.addActionListener(new Actions(this));
//
//        setTitle("Minesweeper");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setResizable(false);
//        pack();
//        setVisible(true);
    }

    public int getx()
    {
        return width;
    }

    public int gety()
    {
        return height;
    }

    public MineSweeperTile[][] getTiles()
    {
        return tiles;
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
                int count = 0;

                if (i > 0 &&	j > 0 && tiles[i - 1]	[j - 1]	.isMine()) count++;
                if (j > 0 && tiles[i][j - 1].isMine()) count++;
                if (i < width - 1 && j > 0 && tiles[i + 1][j - 1].isMine()) count++;

                if (i > 0 && tiles[i - 1][j].isMine()) count++;
                if (i < width - 1 && tiles[i + 1][j].isMine()) count++;

                if (i > 0 && j < height - 1 && tiles[i - 1][j + 1].isMine()) count++;
                if (j < height - 1	&& tiles[i] [j + 1].isMine()) count++;
                if (i < width - 1 && j < height - 1 && tiles[i + 1][j + 1].isMine()) count++;

                tiles[i][j].setNumber(count);

                if (tiles[i][j].isMine())
                {
                    tiles[i][j].setNumber(-1);
                }

                if (tiles[i][j].getNumber() == 0)
                {
                    tiles[i][j].reveal();
                }
            }
        }

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if (i > 0 &&	j > 0 && tiles[i - 1][j - 1].getNumber() == 0) tiles[i][j].reveal();
                if (j > 0 && tiles[i][j - 1].getNumber() == 0) tiles[i][j].reveal();
                if (i < width - 1 && j > 0 && tiles[i + 1][j - 1].getNumber() == 0) tiles[i][j].reveal();

                if (i > 0 && tiles[i - 1][j].getNumber() == 0) tiles[i][j].reveal();
                if (i < width - 1 && tiles[i + 1]	[j]		.getNumber() == 0) tiles[i][j].reveal();

                if (i > 0 && j < height - 1 && tiles[i - 1][j + 1].getNumber() == 0) tiles[i][j].reveal();
                if (j < height - 1 && tiles[i][j + 1].getNumber() == 0) tiles[i][j].reveal();
                if (i < width - 1 && j < height - 1 && tiles[i + 1][j + 1]	.getNumber() == 0) tiles[i][j].reveal();
            }
        }
    }

//    public void refresh()
//    {
//        board.repaint();
//    }

    public void select(int x, int y)
    {
        if (tiles[x][y].isFlagged()) return;
        tiles[x][y].reveal();
        resetMarks();
//        refresh();

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
//        refresh();
//        JOptionPane.showMessageDialog(null, "BOOOOM!");
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

//        refresh();
//        JOptionPane.showMessageDialog(null, "Congratulations! You won!");
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
