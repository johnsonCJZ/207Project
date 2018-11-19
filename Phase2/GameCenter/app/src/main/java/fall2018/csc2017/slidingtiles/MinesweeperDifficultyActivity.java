package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MinesweeperDifficultyActivity extends AppCompatActivity {
    UserAccount user;
    UserAccountManager users;
    MineSweeperManager boardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper_difficulty);
        getUser();
        addEasyButton();
        addMediumButton();
        addHardButton();
        addExtremeButton();
        addCustomButton();
    }

    private void addEasyButton() {
        Button Easy = findViewById(R.id.Easy);
        Easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager=new MineSweeperManager(9, 9, 10);
                switchToGame();
            }
        });
    }

    private void addMediumButton(){
        Button Medium = findViewById(R.id.Medium);
        Medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager=new MineSweeperManager(16, 16, 40);
                switchToGame();
            }
        });
    }

    private void addHardButton(){
        Button Hard = findViewById(R.id.Hard);
        Hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager=new MineSweeperManager(16, 30, 99);
                switchToGame();
            }
        });
    }

    private void addExtremeButton(){
        Button Extreme = findViewById(R.id.Extreme);
        Extreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager=new MineSweeperManager(30, 30, 180);
                switchToGame();
            }
        });
    }

    private void addCustomButton(){
        Button Custom = findViewById(R.id.Custom);
        // with a dialog to create a boardmanager


    }

    /**
     * Receive UserAccount and UserAccountManager from the previous activity/view.
     */
    private void getUser(){
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();
        this.user=(UserAccount) extra.getSerializable("user");
        this.users = (UserAccountManager) extra.getSerializable("allUsers");
    }

    private void switchToGame(){
        Intent tmp = new Intent(this, MineSweeperActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("user",user);
        pass.putSerializable("allUsers", users);
        pass.putSerializable("boardManager", boardManager);
        tmp.putExtras(pass);
        startActivity(tmp);
    }
}

