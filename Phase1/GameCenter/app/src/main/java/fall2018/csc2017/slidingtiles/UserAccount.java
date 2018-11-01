package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class UserAccount implements Serializable {
    private String name;

    private String password;

    private HashMap<String,Board[]> history;

    private Board tempHistory;

    private ArrayList<Integer> userScoreList = new ArrayList<>();

    private ScoreBoard personalHistory3x3;

    private ScoreBoard personalHistory4x4;

    private ScoreBoard personalHistory5x5;

    public UserAccount(String name, String password){
        this.name=name;
        this.password=password;
        this.history=new HashMap<>();
        personalHistory3x3 = new ScoreBoard();
        personalHistory4x4 = new ScoreBoard();
        personalHistory5x5 = new ScoreBoard();
    }

    public void addGame(String name){
        history.put(name, new Board[3]);
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

    protected Board getTempHistory(){
        return tempHistory;
    }

    protected void setTempHistory(Board b){
        this.tempHistory=b;
    }

    public ArrayList<Integer> getUserScoreList(){return this.userScoreList;}

    public int getHistorySize(String key){
        int size = 0;
        for(int i = 0; i<3;i++){
            if(!history.get(key)[i].equals(0)){
                size++;
            }
        }
        return size;
    }
    public void setHistory(String key, Board item){
        int length = getHistorySize(key);
        if (length < 3){
            history.get(key)[length+1] = item;
        }
        else{
            System.out.println("history is full, override or give up?");
        }
    }

    public void overrideHistory(String key, Board item, Integer index){
        history.get(key)[index] = item;

    }


}
