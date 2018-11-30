package fall2018.csc2017.slidingtiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Factory {

    public Factory(){}

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
        Factory f = new Factory();
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
        Factory f = new Factory();
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
        Factory f = new Factory();
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

    public UserAccount createUserAccount(String name, String password){
        UserAccount u = new UserAccount();
        u.setName(name);
        u.setPassword(password);
        u.setEmail("");
        HashMap<String, SlidingMemory> historySliding = u.getHistorySliding();
        historySliding.put("history3x3", null);
        historySliding.put("history4x4", null);
        historySliding.put("history5x5", null);
        historySliding.put("resumeHistorySlide", null);
        HashMap<String, MineMemory> historyMine = u.getHistoryMine();
        historyMine.put("resumeHistoryMine", null);
        historyMine.put("historyMine",null);
        HashMap<String, Game2048Memory> history2048 = u.getHistory2048();
        history2048.put("resumeHistory2048", null);
        HashMap<String,ScoreBoard> personalScoreBoard = u.getPersonalScoreBoard();
        personalScoreBoard.put("history3x3", new ScoreBoard("SlidingTiles"));
        personalScoreBoard.put("history4x4", new ScoreBoard("SlidingTiles"));
        personalScoreBoard.put("history5x5", new ScoreBoard("SlidingTiles"));
        personalScoreBoard.put("2048", new ScoreBoard("2048"));
        personalScoreBoard.put("Mine", new ScoreBoard("MineSweeper"));
        return u;
    }

    public UserAccountManager createUserManager() {
        UserAccountManager m = new UserAccountManager();
        m.getGlobalScoreBoard().put("history3x3", new ScoreBoard("SlidingTiles"));
        m.getGlobalScoreBoard().put("history4x4", new ScoreBoard("SlidingTiles"));
        m.getGlobalScoreBoard().put("history5x5", new ScoreBoard("SlidingTiles"));
        m.getGlobalScoreBoard().put("2048", new ScoreBoard("2048"));
        m.getGlobalScoreBoard().put("Mine", new ScoreBoard("Mine"));
        return m;
    }
}

