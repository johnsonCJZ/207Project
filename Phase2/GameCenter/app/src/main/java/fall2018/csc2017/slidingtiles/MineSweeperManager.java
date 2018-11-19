package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineSweeperManager extends Manager implements Serializable {
    private MineSweeperBoard board;
    private MineSweeperTile[][] tiles;
    private int width;
    private int height;
    private double time;
    private boolean isFirst = true;
    private boolean lost = false;
    private List<MineSweeperTile> minePosition = new ArrayList<>();

    MineSweeperManager(int x, int y, int m) {
        board = new MineSweeperBoard(x, y, m);
        tiles=board.getTiles();
        width = board.getW();
        height = board.getH();
        setUpBoard();
    }

    MineSweeperBoard getBoard() {
        return board;
    }

    double getTime() { return time; }

    void setTime(double time) {
        this.time = time;
    }

    private void setUpBoard() {
        lost = false;
        isFirst = true;
        board.setTiles();
    }

    private void setMines(int position) {
        int mine = board.getMine();
        List<MineSweeperTile> startNine = board.getSurround(position);
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

    void touchMove(int position) {
        if (isFirst) {
            setMines(position);
            setNumbers();
            isFirst = false;
        }
        MineSweeperTile currTile = board.getTile(position);
        if (!currTile.isFlagged()) {
            if (currTile.isMine()) {
                lost = true;
            } else board.reveal(position);
        }
    }

    void mark(int position) {
        board.flag(position);
    }

    private void setNumbers() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                //if the tile is a mine, set the number to -1.
                if (tiles[i][j].isMine()) {
                    tiles[i][j].setNumber(-1);
                }

                //if the tile isn't a mine, set the number to be the number of mines surrounds it.
                else {
                    int count = 0;
                    if (i > 0 && j > 0 && tiles[i - 1][j - 1].isMine()) count++; //upper-left tile
                    if (j > 0 && tiles[i][j - 1].isMine()) count++; //left tile
                    if (i < height - 1 && j > 0 && tiles[i + 1][j - 1].isMine())
                        count++; //lower-left
                    if (i > 0 && tiles[i - 1][j].isMine()) count++; // upper tile
                    if (i < height - 1 && tiles[i + 1][j].isMine()) count++; // lower tile
                    if (i > 0 && j < width - 1 && tiles[i - 1][j + 1].isMine())
                        count++; //upper-right
                    if (j < width - 1 && tiles[i][j + 1].isMine()) count++; //right tile
                    if (i < height - 1 && j < width - 1 && tiles[i + 1][j + 1].isMine())
                        count++; //lower-right tile
                    tiles[i][j].setNumber(count);
                }
            }
        }
    }

    boolean isLost() {
        return lost;
    }

    List<MineSweeperTile> getMinePosition() {return minePosition;}

    boolean isWon() {
        for (int i = 0; i < height * width; i++){
            if (board.getTile(i).isObscured() && !board.getTile(i).isMine()){
                return false;
            }
        }
        return true;
    }
}
