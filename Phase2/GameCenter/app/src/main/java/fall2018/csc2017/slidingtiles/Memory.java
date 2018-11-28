package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Memory implements Serializable {
    private List<Integer> board;
    private int size;
    private double time;
    private List<Integer> mines;

    public Memory(){
        board = new ArrayList<>();
    }

//    public Memory makeCopy(BoardManager manager, String boardType){
//        switch (boardType){
//            case "Slide":
//                Iterator itr = ((SlidingBoardManager) manager).getSlidingBoard().iterator();
//                while(itr.hasNext()){
//                    SlidingTile tile = (SlidingTile) itr.next();
//                    board.add(tile.getId());
//                }
//                size = ((SlidingBoardManager) manager).getSlidingBoard().getDimension();
//                time = ((SlidingBoardManager) manager).getTime2048();
//                break;
//            case "Mine":
//                List<MineTile> tempMine = ((MineBoardManager)manager).getBoard().getMinePosition();
//                List<MineTile> tempBoard = ((MineBoardManager)manager).getBoard().getTiles();
//                for (MineTile i: tempMine){
//                    board.add(i.get)
//                }
//                size = board.size();
//                time = ((SlidingBoardManager) manager).getTimeTmp();
//                break;
//            case "2048":
//                Iterator itr3 = ((Game2048BoardManager)manager).getBoard().iterator();
//                while(itr3.hasNext()){
//                    Game2048Tile tile = (Game2048Tile) itr3.next();
//                    board.add(tile.getValue());
//                }
//                size = ((Game2048BoardManager) manager).getBoard().getDimension();
//                break;
//
//        }
//        return this;
//    }
//
//    public BoardManager copy(String type){
//        switch(type){
//            case "Slide":
//                SlidingBoardManager slidingBoardManager  =new SlidingBoardManager(this.getSize());
//                List<SlidingTile> temp1 = new ArrayList<>();
//                for (int i : this.getBoard()){
//                    temp1.add(new SlidingTile(i));
//                }
//                slidingBoardManager.setSlidingTiles(temp1);
//                slidingBoardManager.setTime(this.getTimeTmp());
//                return slidingBoardManager;
//            case "2048":
//                Game2048BoardManager game2048BoardManager = new Game2048BoardManager();
//                List<Game2048Tile> temp2 = new ArrayList<>();
//
//            case "Mine":
//                break;
//        }
//        return null;
//    }
//
//    public double getTimeTmp() {
//        return time;
//    }
//
//    public void setTime(double time) {
//        this.time = time;
//    }
//
//    public int getSize() {
//        return size;
//    }
//
//    public List<Integer> getBoard() {
//        return board;
//    }
//
//    public List<Integer> getMines() {
//        return mines;
//    }
}
