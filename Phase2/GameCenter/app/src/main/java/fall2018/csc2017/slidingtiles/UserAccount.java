package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represent a user's user account.
 */
public class UserAccount implements Serializable {

    /**
     * The username of the UserAccount
     */
    private String name;

    /**
     * The password of the UserAccount.
     */
    private String password;

    /**
     * The age of the UserAccount.
     */
    private Integer age;

    /**
     * The email of the UserAccount.
     */
    private String email;

    /**
     * The sliding slidingTiles game history of the UserAccount.
     */
    private HashMap<String, SlidingMemory> historySliding = new HashMap<>();

    private HashMap<String, MineMemory> historyMine = new HashMap<>();

    private HashMap<String, Game2048Memory> history2048 = new HashMap<>();

    /**
     * The sliding slidingTiles score list of the UserAccount
     */
    private ArrayList<Integer> userScoreList = new ArrayList<>();

    /**
     * The archive of the game sliding slidingTiles.
     */
    private HashMap<String,ScoreBoard> personalScoreBoard = new HashMap<>();

    /**
     * The games the UserAccount has purchased.
     */
    private ArrayList<String> games;
    /**
     * The UserAccount class constructor.
     * @param name the name of the UserAccount
     * @param password
     */

    public UserAccount(String name, String password){
        this.name=name;
        this.password=password;
        this.age = 0;
        this.email = "";
        games = new ArrayList<>();
        historySliding.put("history3x3", null);
        historySliding.put("history4x4", null);
        historySliding.put("history5x5", null);
        historySliding.put("resumeHistorySlide", null);
        historyMine.put("resumeHistoryMine", null);
        history2048.put("resumeHistory2048", null);
        historyMine.put("historyMine",null);
//        history2048.put("history2048",new Game2048Memory());
        personalScoreBoard.put("history3x3", new ScoreBoard("SlidingTiles"));
        personalScoreBoard.put("history4x4", new ScoreBoard("SlidingTiles"));
        personalScoreBoard.put("history5x5", new ScoreBoard("SlidingTiles"));
        personalScoreBoard.put("2048", new ScoreBoard("2048"));
        personalScoreBoard.put("Mine", new ScoreBoard("MineSweeper"));

    }

    /**
     * The setter of the field email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * The getter of the field email
     * @return the email of the UserAccount
     */
    public String getEmail() {
        return email;
    }

    /**
     * The setter of the field age.
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * The getter of the field age.
     * @return the age of the UserAccount.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Change the username of the UserAccount.
     * @param name the new name to be updated.
     */
    public void changeName(String name){
        this.name=name;
    }

    /**
     * Change the password of the UserAccount.
     * @param ps The new password to be updated.
     */
    void changePassword(String ps){
        this.password=ps;
    }

    /**
     * The getter for the username of the UserAccount.
     * @return the username
     */
    public String getName(){
        return name;
    }

    /**
     * The getter for the password of the UserAccount.
     * @return the password
     */
    public String getPassword(){
        return password;
    }

    /**
     * The getter for the UserScoreList of the UserAccount.
     * @return the UserScoreList.
     */
    public ArrayList<Integer> getUserScoreList(){return this.userScoreList;}

    public void setSlideHistory (String key, SlidingBoardManager item){
        if(item != null) {
            SlidingMemory memory = new SlidingMemory();
            memory.makeCopy(item);
            if (historySliding.get(key) == null) {
                historySliding.put(key, memory);
            } else {
                historySliding.replace(key, memory);
            }
        }
        else{historySliding.replace(key, null);}
    }

    public void setGame2048History(String key, Game2048BoardManager item){
        if(item != null) {
            Game2048Memory memory = new Game2048Memory();
            memory.makeCopy(item);
            if (history2048.get(key) == null) {
                history2048.put(key, memory);
            }
            else {
                history2048.replace(key, memory);
            }
        }
        else {
            history2048.replace(key, null);
        }
    }
    void setMineHistory(String key, MineBoardManager item){
        if(item!=null) {
            MineMemory memory = new MineMemory();
            memory.makeCopy(item);
            if (historyMine.get(key) == null) {
                historyMine.put(key, memory);
            } else {
                historyMine.replace(key, memory);
            }
        }
        else{historyMine.replace(key, null);}}
    /**
     * The getter for the History of the UserAccount.
     * @return the History
     */
    private HashMap<String, SlidingMemory> getSlideHistory(){
        return historySliding;
    }

    private HashMap<String, MineMemory> getMineHistory(){
        return historyMine;
    }

    private HashMap<String, Game2048Memory> get2048History(){
        return history2048;
    }

    /**
     * Get the right size of ScoreBoard wanted.
     * @param tag the size of the ScoreBoard.
     * @return the ScoreBoard wanted
     */
    public ScoreBoard getScoreBoard(String tag) {
        return personalScoreBoard.get(tag);
    }

    /**
     * Add a new game to the UserAccount.
     * @param game The name of the new game being added.
     */
    public void addGames(String game){
        this.games.add(game);
    }

    public SlidingBoardManager getSpecificSlideHistory(String key){
        SlidingMemory memory = this.getSlideHistory().get(key);
        if (memory==null){
            return null;
        }
        return memory.copy();}

    MineBoardManager getSpecificMineHistory(String key){
        MineMemory memory = this.getMineHistory().get(key);
        if (memory==null){
            return null;
        }
        return memory.copy();
    }

    public Game2048BoardManager getSpecific2048History(String key){
        Game2048Memory memory = this.get2048History().get(key);
        if (memory==null){
            return null;
        }
        return memory.copy();
    }

    public ArrayList<String> getGames() {
        return games;
    }
}
