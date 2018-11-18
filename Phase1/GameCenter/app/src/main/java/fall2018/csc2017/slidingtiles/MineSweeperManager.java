package fall2018.csc2017.slidingtiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineSweeperManager extends Manager {
    private MineSweeperBoard board;
    private MineSweeperTile[][] tiles = board.getTiles();
    private int width;
    private int height;
    private double time;
    private boolean isFirst = true;
    private boolean lost = false;
    private List<MineSweeperTile> minePosition = new ArrayList<>();

    public MineSweeperManager(int x, int y, int m) {
        board = new MineSweeperBoard(x, y, m);
        width = board.getWidth();
        height = board.getHeight();
        setUpBoard();
    }

    void setMines(int position) {
        int mine = board.getMine();
        List<MineSweeperTile> startNine = getSurround(position);
        Random r = new Random();
        List<Integer> randomNum = new ArrayList<>();
        int i = 0;

        startNine.add(board.getTile(position));

        while (i < mine) {
            Integer num = r.nextInt(width * height);

            if (!randomNum.contains(num) && !startNine.contains(board.getTile(num))) {
                randomNum.add(num);
                board.getTile(num).setMine();
                minePosition.add(board.getTile(num));
                i++;
            }
        }
    }

    List<MineSweeperTile> getSurround(int position) {
        int row = position / width;
        int col = position / width;
        List<MineSweeperTile> surround = new ArrayList<>();

        if (row != 0 && col != 0) {
            surround.add(board.getTiles()[row - 1][col - 1]);
        }
        if (row != 0 && col != width - 1) {
            surround.add(board.getTiles()[row - 1][col + 1]);
        }
        if (row != 0) {
            surround.add(board.getTiles()[row - 1][col]);
        }
        if (row != height - 1 && col != 0) {
            surround.add(board.getTiles()[row + 1][col - 1]);
        }
        if (row != height - 1 && col != width - 1) {
            surround.add(board.getTiles()[row + 1][col + 1]);
        }
        if (row != height - 1) {
            surround.add(board.getTiles()[row + 1][col]);
        }
        if (col != 0) {
            surround.add(board.getTiles()[row][col - 1]);
        }
        if (col != width - 1) {
            surround.add(board.getTiles()[row][col + 1]);
        }
        return surround;
    }

    void reveal(MineSweeperTile currTile) {
        if (currTile.getNumber() == 0) {
            currTile.reveal();
            for (MineSweeperTile tile : getSurround(currTile.getPosition())) {
                if (tile.getNumber() == 0) {
                    reveal(tile);
                }
            }
        } else {
            currTile.reveal();
        }
    }

    void touchmove(int position) {
        if (isFirst) {
            setMines(position);
            setNumbers();
            isFirst = false;
        }
        MineSweeperTile currTile = board.getTile(position);
        if (currTile.isMine()) {
            lost = true;
        } else reveal(currTile);
    }

    MineSweeperBoard getBoard() {
        return board;
    }

    double getTime() {
        return time;
    }

    void setTime(double time) {
        this.time = time;
    }

    void setUpBoard() {
        lost = false;
        isFirst = true;

        board.setTiles();
    }

    void setNumbers() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
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
                if (tiles[i][j].getNumber() == 0) {
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

    boolean isLost() {
        return lost;
    }

    boolean isWon() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (tiles[i][j].isObscured() && !tiles[i][j].isMine()) {
                    return false;
                }
            }
        }
        return true;
    }

    void mark(int position) {

        if (board.getTile(position).isFlagged()) {
            board.getTile(position).unFlag();
        } else if (board.getTile(position).isObscured()) {
            board.getTile(position).flag();
        }
    }
}
