package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SlideDifficultyActivity extends AppCompatActivity {

    private BoardManager boardManager;
    private UserAccount user;
    private UserAccountManager users;
    private String direction;
    private ScoreBoard scoreBoard;

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
        this.direction = (String) extra.getSerializable("direction");
    }

    private void addStart4x4Button() {
        Button startButton = findViewById(R.id.button3);
        if (this.direction == "ToGame") {
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boardManager = new BoardManager(4);
                    clearResumeHistory();
                    switchToGame();
                }
            });
        }
        else {
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scoreBoard = user.getScoreBoard("history4x4");
                    switchToScoreBoard();
                }
            });
        }
    }

    private void addStart3x3Button() {
        Button startButton = findViewById(R.id.button);
        if (this.direction == "ToGame") {
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boardManager = new BoardManager(3);
                    clearResumeHistory();
                    switchToGame();
                }
            });
        }
        else {
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scoreBoard = user.getScoreBoard("history3x3");
                    switchToScoreBoard();
                }
            });
        }
    }

    private void switchToScoreBoard() {
        Intent tmp = new Intent(this, ScoreBoardActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("user",user);
        pass.putSerializable("allUsers", users);
        pass.putSerializable("scoreBoard", scoreBoard);
        tmp.putExtras(pass);
        startActivity(tmp);
    }
    private void addStart5x5Button() {
        Button startButton = findViewById(R.id.button4);
        if (this.direction == "ToGame") {
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boardManager = new BoardManager(5);
                    clearResumeHistory();
                    switchToGame();
                }
            });
        }
        else {
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scoreBoard = user.getScoreBoard("history5x5");
                    switchToScoreBoard();
                }
            });
        }
    }
    private void switchToGame() {
        Intent tmp = new Intent(this, MainSlideActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("user",user);
        pass.putSerializable("allUsers", users);
        pass.putSerializable("boardManager", boardManager);
        tmp.putExtras(pass);
        startActivity(tmp);
    }


}
