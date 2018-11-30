package fall2018.csc2017.slidingtiles;

import java.util.ArrayList;
import java.util.List;

public class ManagerFactory {

    public ManagerFactory(){};

    public BoardManager createNewManager(Board b){
        if(b instanceof MineBoard){
            MineBoardManager m = new MineBoardManager();
            m.setTiles(b.getTiles());
            m.setDimension(b.getDimension());
            m.setMinePosition(((MineBoard) b).getMinePosition());
            m.setBoard((MineBoard) b);
            m.setUpBoard();
            return m;
        }
        if(b instanceof Game2048Board){
            Game2048BoardManager m = new Game2048BoardManager();
            m.setDimension(4);
            m.setBoard((Game2048Board) b);
            ((Game2048Board)b).addTile();
            ((Game2048Board)b).addTile();
            return m;
        }
        if(b instanceof SlidingBoard){
            SlidingBoardManager m = new SlidingBoardManager();
            int dimension = (b.getDimension());
            m.setDimension(dimension);
            m.setBoard((SlidingBoard) b);
            m.addTile();
            m.shuffle();
            b.setTiles(m.getTiles());
            m.getHistory().add(new HistoryNode(m.findBlankIndex()));
            return m;
        }
        return null;
    }
}

