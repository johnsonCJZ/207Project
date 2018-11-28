package fall2018.csc2017.slidingtiles;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
import fall2018.csc2017.slidingtiles.database.DatabaseHelper;

public class CreateNewOrLoad2048Activity extends AppCompatActivity {
    DatabaseHelper myDB;

    private Game2048BoardManager boardManager;

    private UserAccount user;

    private UserAccountManager users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.myDB = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_or_load2048);
        getUser();
        addCreateNewGameButton();
//        addResumeGameButton();
//        addLoadGameButton();
    }

//    private void addLoadGameButton(){
//        Button loadButton = findViewById(R.id.Load2048Button);
//        loadButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                boardManager = user.getSpecific2048History("history2048");
//                if (boardManager!=null){
//                    switchToGame();}
//                else{
//                    Toasty.info(getApplicationContext(), "No game history",Toast.LENGTH_SHORT, true).show();
//                }
//            }
//        });
//    }
//
//    /**
//     * add resume button
//     */
//    private void addResumeGameButton() {
//        Button resumeButton =findViewById(R.id.Resume2048Button);
//        resumeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boardManager = user.getSpecific2048History("resumeHistory2048");
//                if (boardManager != null) {
//                    user.setSlideHistory("resumeHistory2048", null);
//                    switchToGame();
//                }
//                else {
//                    AlertDialog.Builder builder = new AlertDialog.Builder
//                            (getApplicationContext());
//                    builder.setMessage("History not found");
//                    AlertDialog d = builder.create();
//                    d.show();
//                }
//            }
//        });
//    }


    private void addCreateNewGameButton() {
        Button startButton = findViewById(R.id.New2048Button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = user.getSpecific2048History("resumeHistory2048");
                if (boardManager != null) {
                    switchToGame();
                } else {
                    boardManager = new Game2048BoardManager();
                    user.setGame2048History("resumeHistory2048", null);
                    switchToGame();
                }
            }
        });
    }

    private void switchToGame() {
        Intent tmp = new Intent(this, Game2048MainActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("boardManager", boardManager);
        tmp.putExtras(pass);
        startActivity(tmp);
    }

    private void getUser() {
        String currentUser = (String)DataHolder.getInstance().retrieve("current user");
        this.user = myDB.selectUser(currentUser);
        this.users = myDB.selectAccountManager();
    }
}
