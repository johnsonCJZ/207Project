package fall2018.csc2017.slidingtiles.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import fall2018.csc2017.slidingtiles.BoardManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import fall2018.csc2017.slidingtiles.InterfaceAdapter;
import fall2018.csc2017.slidingtiles.LoginActivity;
import fall2018.csc2017.slidingtiles.ScoreStrategy;
import fall2018.csc2017.slidingtiles.UserAccount;
import fall2018.csc2017.slidingtiles.UserAccountManager;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE_NAME = "user_table";
    public static final String KEY_NAME = "USERNAME";
    public static final String KEY_USER = "USERACCOUNT";
    private final String USER_ACCOUNT_MANAGER = "UserAccountManager";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //
    //NOTE: haven't updated this method in change password and profile fragment
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + " (USERNAME TEXT PRIMARY KEY, " +
                "USERACCOUNT OBJECT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean ifAccountManagerExists() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select count(*) from " + TABLE_NAME + " where " + KEY_NAME + " = '" + USER_ACCOUNT_MANAGER+ "'";
        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        return res.getInt(0) == 1;
    }

    public boolean createUserAccountManager() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USER, convertToJson(new UserAccountManager()));
        contentValues.put(KEY_NAME, USER_ACCOUNT_MANAGER);
        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;

    }

    public String convertToJson(Object o) {
        Gson gson = new GsonBuilder().registerTypeAdapter(ScoreStrategy.class, new InterfaceAdapter<ScoreStrategy>())
                .create();
        return gson.toJson(o);
    }
    public boolean createAndInsertNew(String username, UserAccount userAccount) throws SQLException {
        boolean found;
        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, username);
        contentValues.put(KEY_USER, convertToJson(userAccount)); //change default value of age = 0 and email = "" in regist activity
        try{result = db.insertOrThrow(TABLE_NAME, null, contentValues);}
        catch(Exception e){
            e.printStackTrace();
            result = -1;
        }
        found = result != -1;
        return found;
    }

    public UserAccountManager selectAccountManager(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select " + KEY_USER + " from " + TABLE_NAME  + " where " + KEY_NAME
                + " = '" + USER_ACCOUNT_MANAGER + "'";
        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        return convertToAccountManager(res.getString(0));
    }

    // return null if there is no result

    // query may has problem
    public UserAccount selectUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select " + KEY_USER + " from " + TABLE_NAME  + " where " + KEY_NAME + " = '" + username + "'";
        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        return convertToUserAccount(res.getString(0));
    }
    public void updateUser(String username, UserAccount userAccount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, username);
        contentValues.put(KEY_USER, convertToJson(userAccount));
        db.update(TABLE_NAME, contentValues, "USERNAME = ?",new String[] { username });

    }

    public boolean deleteAndInsertUser(String oldUserName, String newUserName, UserAccount userAccount){
        SQLiteDatabase db = this.getWritableDatabase();
        long result;
        boolean found;
        db.delete(TABLE_NAME,"USERNAME = ?", new String[] { oldUserName });
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, newUserName);
        contentValues.put(KEY_USER, convertToJson(userAccount));
        try{result = db.insertOrThrow(TABLE_NAME, null, contentValues);}
        catch(Exception e){
            e.printStackTrace();
            result = -1;
        }
        found = result != -1;
        return found;
    }

    public void updateAccountManager(UserAccountManager userAccountManager) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, USER_ACCOUNT_MANAGER);
        contentValues.put(KEY_USER, convertToJson(userAccountManager));
        db.update(TABLE_NAME, contentValues, "USERNAME = ?",new String[] { USER_ACCOUNT_MANAGER });
        
    }

    public String convertBoardManagerToJson(BoardManager board){
        Type type = new TypeToken<BoardManager>(){}.getType();
        Gson gson = new Gson();
        return gson.toJson(board, type);
    }


//    public boolean updateScoreBoard(String colName, String username, ScoreBoard scoreBoard){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(colName, convertToJson(scoreBoard));
//        db.update(TABLE_NAME, contentValues, "USERNAME = ?", new String[] { username});
//        db.close();
//
//        return true;
//    }
    private UserAccount convertToUserAccount(String jsonString){
        Gson gson = new GsonBuilder().registerTypeAdapter(ScoreStrategy.class, new InterfaceAdapter<ScoreStrategy>())
                .create();
//        Gson gson = new Gson();
        return gson.fromJson(jsonString, UserAccount.class);
    }

    private UserAccountManager convertToAccountManager(String jsonString){
        Gson gson = new GsonBuilder().registerTypeAdapter(ScoreStrategy.class, new InterfaceAdapter<ScoreStrategy>())
                .create();
//        Gson gson = new Gson();
        return gson.fromJson(jsonString, UserAccountManager.class);
    }

    public boolean insertScoreboard(){return true;}

    public Integer deleteData (String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "USERNAME = ?",new String[] {username});
    }


    public boolean hasUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select count(*) from " + TABLE_NAME + " where " + KEY_NAME + " = '" + username+ "'";
        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        return res.getInt(0) == 1;
    }
}
