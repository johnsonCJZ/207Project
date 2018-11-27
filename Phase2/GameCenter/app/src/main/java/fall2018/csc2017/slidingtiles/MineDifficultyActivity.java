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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper_difficulty);
        getUser();
        addStartButton();
        addResumeButton();
    }

    private void addResumeButton(){
        Button resume = findViewById(R.id.resume);
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = (MineBoardManager) user.getHistory().get("Mine");
                if (boardManager!=null){
                    switchToGame();}
                    else{
                    Toasty.info(getApplicationContext(), "No game history",Toast.LENGTH_SHORT, true).show();
                }
            }
        });
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
                        boardManager =new MineBoardManager(9, 9, 10);
                        switchToGame();
                        break;
                    case 1: // 4x4
                        boardManager = new MineBoardManager(16, 16, 40);
                        switchToGame();
                        break;
                    case 2: // 5x5
                        boardManager = new MineBoardManager(20, 20, 84);
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

    }

    private void switchToGame(){
        Intent tmp = new Intent(this, MainMineSweeperActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("boardManager", boardManager);
        tmp.putExtras(pass);
        startActivity(tmp);
    }
}

