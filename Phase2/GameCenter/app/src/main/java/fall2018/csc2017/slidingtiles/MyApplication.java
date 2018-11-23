package fall2018.csc2017.slidingtiles;

import android.app.Application;

public class MyApplication extends Application {
    private String currentUser;
    public String getCurrentUser() {return currentUser;}
    public void setUser(String user) {this.currentUser = user;
}}
