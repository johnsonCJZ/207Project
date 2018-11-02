package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class UserAccount implements Serializable {

    private String name;

    private String password;

    private HashMap<String, BoardManager> history;

    private ArrayList<Integer> userScoreList = new ArrayList<>();

    private ScoreBoard personalHistory3x3;

    private ScoreBoard personalHistory4x4;

    private ScoreBoard personalHistory5x5;

    public UserAccount(String name, String password){
        this.name=name;
        this.password=password;
        this.history=new HashMap<String, BoardManager>();
        personalHistory3x3 = new ScoreBoard();
        personalHistory4x4 = new ScoreBoard();
        personalHistory5x5 = new ScoreBoard();
        history.put("history3x3", null);
        history.put("history4x4", null);
        history.put("history5x5",null);
        history.put("resumeHistory", null);

    }

    public void addGame(String name){
        history.put(name, null);
    }

    public void changeName(String name){
        this.name=name;
    }

    public void changePassword(String ps){
        this.password=ps;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }


    public ArrayList<Integer> getUserScoreList(){return this.userScoreList;}

    public boolean setHistory(String key, BoardManager item){
        if (history.get(key)==null){
            history.put(key,item);
            return true;
        }
            return false;
    }

    public HashMap<String, BoardManager> getHistory(){
        return history;
    }


}
