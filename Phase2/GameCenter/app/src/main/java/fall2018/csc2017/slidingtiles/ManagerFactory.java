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

    public BoardManager Load2048Manager(double time, int score, List<Integer> list){
        ManagerFactory f = new ManagerFactory();
        Game2048Board board = new BuilderBoard().build2048Board();
        Game2048BoardManager manager = (Game2048BoardManager) f.createNewManager(board);
        manager.setDimension(4);
        manager.setTime(time);
        manager.setBoard(board);
        manager.getBoard().setScore(score);
        manager.getBoard().setUpTiles(list);
        return manager;
    }

    public BoardManager loadMineManager(int d, int mineNum, int mineLeft, double time, List<MineTile> tiles){
        ManagerFactory f = new ManagerFactory();
        MineBoard b = new BuilderBoard()
                .setMine(mineNum)
                .setMineLeft(mineNum)
                .setDimension(d)
                .setMineTiles(tiles)
                .buildMineBoard();
        MineBoardManager m = (MineBoardManager) f.createNewManager(b);
        m.setDimension(d);
        m.setBoard(b);
        m.setTime(time);
        m.setMinePosition(b.getMinePosition());
        return m;
    }

    public BoardManager loadSlidingManager(int dimension, double time, List<SlidingTile> slidingTiles){
        ManagerFactory f = new ManagerFactory();
        SlidingBoard b =new BuilderBoard()
                .setDimension(dimension)
                .setTiles((List<Tile>)(List<?>)slidingTiles)
                .buildSlidingBoard();
        SlidingBoardManager m = (SlidingBoardManager)f.createNewManager(b);

        m.setDimension(dimension);
        m.setTime(time);
        m.setBoard(new BuilderBoard()
                .setDimension(dimension)
                .buildSlidingBoard());
        m.setTiles(slidingTiles);
        m.setHistory(new History());
        m.getHistory().add(new HistoryNode(m.findBlankIndex()));
        return m;
    }
}

