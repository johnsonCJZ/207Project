package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.ObjectOutputStream;


public class SlideDifficultyActivity extends AppCompatActivity {

    private BoardManager boardManager;
    private UserAccount user;
    private UserAccountManager users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_difficulty);
        getUser();
        addStart4x4Button();
        addStart3x3Button();
        addStart5x5Button();
    }

    private void clearResumeHistory(){
        user.getHistory().put("resumeHistory", null);
    }
    private void getUser(){
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();
        this.user=(UserAccount) extra.getSerializable("user");
        this.users = (UserAccountManager) extra.getSerializable("allUsers");
    }

    private void addStart4x4Button() {
        Button startButton = findViewById(R.id.button3);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = new BoardManager(4);
                clearResumeHistory();
                switchToGame(4);
            }
        });
    }

    private void addStart3x3Button() {
        Button startButton = findViewById(R.id.button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = new BoardManager(3);
                clearResumeHistory();
                switchToGame(3);
            }
        });
    }

    private void addStart5x5Button() {
        Button startButton = findViewById(R.id.button4);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = new BoardManager(5);
                clearResumeHistory();
                switchToGame(5);
            }
        });
    }

    private void switchToGame(int n) {
        Intent tmp = new Intent(this, MainSlideActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("user",user);
        pass.putInt("size", n);
        pass.putSerializable("allUsers", users);
        pass.putSerializable("boardManager", boardManager);
        tmp.putExtras(pass);
        startActivity(tmp);
    }


}
