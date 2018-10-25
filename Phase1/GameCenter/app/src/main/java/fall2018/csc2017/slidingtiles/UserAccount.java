package fall2018.csc2017.slidingtiles;

import java.util.HashMap;

public class UserAccount {
    private String name;

    private String password;

    private HashMap<String, Board> history;

    private Board tempHistory;

    public UserAccount(String name, String password){
        this.name=name;
        this.password=password;
        this.history=new HashMap<String, Board>(3);
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

    public void setHistory(String key, Board item){
        if (history.size()<3){
            history.put(key, item);
        }
        else{
            System.out.println("history is full, override or give up?");
            System.out.println(history.keySet());
            // suposed to be connected with UI, trigger UserAccountManager

        }
    }

}
