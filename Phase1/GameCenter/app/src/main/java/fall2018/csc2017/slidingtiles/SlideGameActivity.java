package fall2018.csc2017.slidingtiles;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SlideGameActivity extends AppCompatActivity {
    private UserAccount user;
    private UserAccountManager users;
    /**
     * The main save file.
     */
    /**
     * A temporary save file.
     */
    private BoardManager boardManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUsers();
        setContentView(R.layout.activity_slide_game);
        addStartButtonListener();
        addLoadButtonListener();
        addResumeButtonListener();
    }

    private void getUsers(){
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();
        this.user=(UserAccount) extra.getSerializable("user");
        this.users = (UserAccountManager) extra.getSerializable("allUsers");
    }



    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.NewGameButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToDifficulty();
            }
        });
    }

    private void addResumeButtonListener() {
        Button startButton = findViewById(R.id.ResumeButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResume();
            }
        });
    }

    private void addLoadButtonListener() {
        Button startButton = findViewById(R.id.LoadButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
            }
        });
    }


    private void switchToDifficulty(){
        Intent tmp = new Intent(this, SlideDifficultyActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("user",this.user);
        pass.putSerializable("allUsers", this.users);
        tmp.putExtras(pass);
        startActivity(tmp);}

    private void switchToGame() {
        Intent tmp = new Intent(this, MainSlideActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("user",this.user);
        pass.putSerializable("allUsers", this.users);
        pass.putSerializable("boardManager", boardManager);
        tmp.putExtras(pass);
        startActivity(tmp);
    }

    private void updateUser(){
        loadFromFile(UserAccountManager.USERS);
        for (UserAccount u: users.getUserList()){
            if(u.getName().equals(user.getName())){
                user = u;
            }
        }
    }

    private void load(){
        updateUser();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an memory");
        // add a list
        String[] memoryList = {"3x3", "4x4","5x5"};
        builder.setItems(memoryList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: // 3x3
                        boardManager = user.getHistory().get("history3x3");
                        break;
                    case 1: // 4x4
                        boardManager = user.getHistory().get("history4x4");
                        break;
                    case 2: // 5x5
                        boardManager = user.getHistory().get("history5x5");
                        break;
                }
                if(boardManager!=null){
                switchToGame();
                }
                else{
                    builder.setMessage("History not found");
                    AlertDialog d = builder.create();
                    d.show();
                }

            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    protected void onResume() {
        updateUser();
        super.onResume();
        boardManager=user.getHistory().get("resumeHistory");
        if (boardManager ==null){
                    // can not resume for no temp
                }
        else {
            switchToGame();
            }}

    private void loadFromFile(String fileName) {

        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                users = (UserAccountManager) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }
}






