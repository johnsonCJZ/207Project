package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Represent a manager class for UserAccounts.
 */
public class UserAccountManager implements Iterable<UserAccount>, Serializable {
    /**
     * all users stored
     */
    private ArrayList<UserAccount> userList = new ArrayList<>();

    /**
     * global scoreBoard
     */
    private HashMap<String,ScoreBoard> slideTilesGlobalScoreBoard = new HashMap<>();

    /**
     * The serialized file for the users information.
     */
    public final static String USERS = "save_user_info.ser";

    /**
     * The getter for a list of all the users.
     * @return a list of all the users.
     */
    public ArrayList<UserAccount> getUserList(){
        return userList;
    }

    /**
     * Add a new UserAccount.
     * @param u the new UserAccount to be added
     */
    void AddUser(UserAccount u){

        userList.add(u);
    }

    /**
     * create a new UserAccountManager with empty memory
     */
    UserAccountManager() {
        slideTilesGlobalScoreBoard.put("history3x3", new ScoreBoard("SlidingTiles"));
        slideTilesGlobalScoreBoard.put("history4x4", new ScoreBoard("SlidingTiles"));
        slideTilesGlobalScoreBoard.put("history5x5", new ScoreBoard("SlidingTiles"));
    }
    /**
     * Return a iterator of the UserAccountManager class.
     * @return a UserAccountManager Iterator.
     */
    @NonNull
    @Override
    public Iterator<UserAccount> iterator() {
        return new UserAccountManagerIterator();
    }

    /**
     * Represent a UserAccountManagerIterator.
     */
    private class UserAccountManagerIterator implements Iterator<UserAccount> {

        /**
         * The index of the current position.
         */
        int index = 0;

        /**
         * Check whether there is a next UserAccount.
         * @return a boolean that whether there is the next UserAccount.
         */
        @Override
        public boolean hasNext() {
            return index < userList.size();
        }

        /**
         *  Return the next UserAccount.
         * @return the next UserAccount.
         */
        @Override
        public UserAccount next() {
            if(this.hasNext()){
                userList.get(index++);
            }
            return null;
        }
    }

    public ScoreBoard getSlideTilesGlobalScoreBoard(String tag) {
        return slideTilesGlobalScoreBoard.get(tag);
    }
}


