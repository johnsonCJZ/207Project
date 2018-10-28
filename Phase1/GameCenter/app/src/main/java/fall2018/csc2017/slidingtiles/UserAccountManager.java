package fall2018.csc2017.slidingtiles;

import java.util.ArrayList;
import java.util.Iterator;

public class UserAccountManager {
    private ArrayList<UserAccount> userList;

    UserAccountManager(){
        userList = new ArrayList<>();
    }

    public ArrayList<UserAccount> getAcounts(){
        return userList;
    }

    public void AddUser(UserAccount u){

        userList.add(u);
    }

    public void RemoveUser(UserAccount u){

        userList.remove(u);
    }






}


