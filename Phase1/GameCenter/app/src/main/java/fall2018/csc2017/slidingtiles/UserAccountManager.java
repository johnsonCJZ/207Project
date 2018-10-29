package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;

public class UserAccountManager implements Iterable<UserAccount>{
    private static ArrayList<UserAccount> userList;

    UserAccountManager(){
        userList = new ArrayList<>();
    }

    public static ArrayList<UserAccount> getUserList(){
        return userList;
    }

    public static void AddUser(UserAccount u){

        userList.add(u);
    }

    public static void RemoveUser(UserAccount u){

        userList.remove(u);
    }

    @NonNull
    @Override
    public Iterator<UserAccount> iterator() {
        return new UserAccountManagerIterator();
    }

    private class UserAccountManagerIterator implements Iterator<UserAccount> {
        int index = 0;
        @Override
        public boolean hasNext() {
            if(index < userList.size()){
                return true;
            }
            return false;
        }

        @Override
        public UserAccount next() {
            if(this.hasNext()){
                userList.get(index++);
            }
            return null;
        }
    }
}


