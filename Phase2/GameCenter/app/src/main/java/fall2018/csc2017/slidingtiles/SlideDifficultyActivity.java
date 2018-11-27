package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fall2018.csc2017.slidingtiles.database.DatabaseHelper;


public class SlideDifficultyActivity extends AppCompatActivity {

    /**
     * The SlidingBoardManager
     */
    private SlidingBoardManager slidingBoardManager;

    /**
     * The UserAccount
     */
    private UserAccount user;


    private DatabaseHelper myDB;

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myDB = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_difficulty);
        getUser();
        addStart4x4Button();
        addStart3x3Button();
        addStart5x5Button();
    }

    /**
     * Clear the autosave history.
     */
    private void clearResumeHistory(){
        user.getHistory().put("resumeHistory", null);
    }

    /**
     * Receive UserAccount and UserAccountManager from the previous activity/view.
     */
    private void getUser(){
        username = (String)DataHolder.getInstance().retrieve("current user");
        this.user=myDB.selectUser(username);
    }

    /**
     * Add the 4x4 button for initializing a 4x4 game.
     */
    private void addStart4x4Button() {
        Button startButton = findViewById(R.id.button3);
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    slidingBoardManager = new SlidingBoardManager(4);
                    clearResumeHistory();
                    switchToGame();
                }
            });
    }

    /**
     * Add the 3x3 button for initializing a 4x4 game.
     */
    private void addStart3x3Button() {
        Button startButton = findViewById(R.id.button);
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    slidingBoardManager = new SlidingBoardManager(3);
                    clearResumeHistory();
                    switchToGame();
                }
            });
        }

    /**
     * Add the 5x5 button for initializing a 4x4 game.
     */
    private void addStart5x5Button() {
        Button startButton = findViewById(R.id.button4);
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    slidingBoardManager = new SlidingBoardManager(5);
                    clearResumeHistory();
                    switchToGame();
                }
            });
        }

    /**
     * Switch to game activity/view.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, MainSlideActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("slidingBoardManager", slidingBoardManager);
        tmp.putExtras(pass);
        startActivity(tmp);
    }


}
