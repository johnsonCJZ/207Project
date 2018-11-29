package fall2018.csc2017.slidingtiles;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import es.dmoral.toasty.Toasty;
import fall2018.csc2017.slidingtiles.database.DatabaseHelper;

public class MineDifficultyActivity extends AppCompatActivity {
    UserAccount user;
    MineBoardManager boardManager;
    DatabaseHelper myDB;
    String username;
    private UserAccountManager users;

    /**
     * scoreboard of user
     */
    private ScoreBoard personalScoreBoard;

    private ScoreBoard globalScoreBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper_difficulty);
        getUser();
        addStartButton();
        addLoadButton();
        addRankButton();
    }

    private void addStartButton(){
        Button start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDifficulty();
            }
        });
    }

    private void addLoadButton(){
        Button load = findViewById(R.id.load);
        load.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boardManager = user.getSpecificMineHistory("historyMine");
                if (boardManager!=null){
                    switchToGame();}
                else{
                    Toasty.info(getApplicationContext(), "No game history",Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }

    void addRankButton() {
        Button rank = findViewById(R.id.rank);
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personalScoreBoard = user.getScoreBoard("Mine");
                globalScoreBoard = users.getGlobalScoreBoard("Mine");
                if(personalScoreBoard != null && globalScoreBoard!=null){
                    switchToScoreBoard();
                }
            }
        });
    }

    private void switchToScoreBoard() {
        Intent tmp = new Intent(this, ScoreBoardTabLayoutActivity.class);
        Bundle pass = new Bundle();
        myDB.updateUser(username, this.user);
        myDB.updateAccountManager(this.users);
        pass.putSerializable("personalScoreBoard", this.personalScoreBoard);
        pass.putSerializable("globalScoreBoard", this.globalScoreBoard);
        tmp.putExtras(pass);
        startActivity(tmp);
    }



    private void selectDifficulty(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an difficulty");
        // add a list
        String[] memoryList = {"Easy", "Medium","Hard"};
        builder.setItems(memoryList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: // 3x3
                        boardManager =new MineBoardManager(9, 10);
                        switchToGame();
                        break;
                    case 1: // 4x4
                        boardManager = new MineBoardManager(16, 40);
                        switchToGame();
                        break;
                    case 2: // 5x5
                        boardManager = new MineBoardManager(20, 80);
                        switchToGame();
                        break;
                }

            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }



    /**
     * Receive UserAccount and UserAccountManager from the previous activity/view.
     */
    private void getUser(){
        myDB = new DatabaseHelper(this);
        username=(String) DataHolder.getInstance().retrieve("current user");
        this.user=myDB.selectUser(username);
        this.users= myDB.selectAccountManager();

    }

    private void switchToGame(){
        Intent tmp = new Intent(this, MineMainActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("boardManager", boardManager);
        tmp.putExtras(pass);
        startActivity(tmp);
    }
}

