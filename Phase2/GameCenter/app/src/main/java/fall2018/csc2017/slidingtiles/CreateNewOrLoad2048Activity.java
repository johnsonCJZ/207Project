package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fall2018.csc2017.slidingtiles.database.DatabaseHelper;

public class CreateNewOrLoad2048Activity extends AppCompatActivity {
    DatabaseHelper myDB;

    private Board2048Manager boardManager;

    private UserAccount user;

    private UserAccountManager users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.myDB = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_or_load2048);
        getUser();
        addCreateNewGameButton();
        addResumeGameButton();
    }

    private void addResumeGameButton() {
//        Button startButton = findViewById(R.id.Resume2048Button);
//        startButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boardManager = new Board2048Manager();
//                clearResumeHistory();
//                switchToGame();
//            }
//        });
    }

    private void addCreateNewGameButton() {
        Button startButton = findViewById(R.id.New2048Button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = new Board2048Manager();
//                clearResumeHistory();
                switchToGame();
            }
        });
    }

    private void switchToGame() {
        Intent tmp = new Intent(this, Main2048Activity.class);
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
