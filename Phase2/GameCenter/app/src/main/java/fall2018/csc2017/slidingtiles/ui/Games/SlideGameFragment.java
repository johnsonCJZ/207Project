package fall2018.csc2017.slidingtiles.ui.Games;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.*;
import fall2018.csc2017.slidingtiles.database.DatabaseHelper;


public class SlideGameFragment extends Fragment {
    DatabaseHelper myDB;
    String username;
    /**
     * user that is operating system
     */
    private UserAccount user;

    /**
     * all users from database
     */
    private UserAccountManager users;

    /**
     * scoreboard of user
     */
    private ScoreBoard personalScoreBoard;

    private ScoreBoard globalScoreBoard;

    /**
     * boardManager in user
     */
    private BoardManager boardManager;

    /**
     * board type judgement
     */


    private Button Rank;

    private Button startButton;

    private Button resumeButton;

    private Button loadButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        myDB = new DatabaseHelper(this.getContext());
        username = (String) DataHolder.getInstance().retrieve("current user");
        getUser();
        View rootView = inflater.inflate(R.layout.slide_game_fragment, container, false);
        Rank=rootView.findViewById(R.id.Rank);
        startButton=rootView.findViewById(R.id.NewGameButton1);
        resumeButton=rootView.findViewById(R.id.ResumeButton1);
        loadButton=rootView.findViewById(R.id.LoadButton1);
        addStartButtonListener();
        addLoadButtonListener();
        addResumeButtonListener();
        addScoreBoardListener();
        return rootView;
    }

    private void getUser(){
        user= myDB.selectUser(username);
        users= myDB.selectAccountManager();
    }

    /**
     * add global scoreboard button
     */
    private void addScoreBoardListener() {
        Rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choseLevel();
            }
        });
    }



    /**
     * choose game difficulty to view scoreboard
     */
    private void choseLevel() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose an level");
        // add a list
        String[] memoryList = {"3x3", "4x4","5x5"};
        builder.setItems(memoryList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: // 3x3
                        personalScoreBoard = user.getScoreBoard("history3x3");
                        globalScoreBoard = users.getGlobalScoreBoard("history3x3");
                        break;
                    case 1: // 4x4
                        personalScoreBoard = user.getScoreBoard("history4x4");
                        globalScoreBoard = users.getGlobalScoreBoard("history4x4");
                        break;
                    case 2: // 5x5
                        personalScoreBoard = user.getScoreBoard("history5x5");
                        globalScoreBoard = users.getGlobalScoreBoard("history5x5");
                        break;
                }
                if(personalScoreBoard != null && globalScoreBoard!=null){
                    switchToScoreBoard();
                }
                else{
                    builder.setMessage("No record :)");
                    AlertDialog d = builder.create();
                    d.show();
                }

            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * start scoreboard activity
     */
    private void switchToScoreBoard() {
        Intent tmp = new Intent(getActivity(), ScoreBoardTabLayoutActivity.class);
        Bundle pass = new Bundle();
        myDB.updateUser(username, this.user);
        myDB.updateAccountManager(this.users);
        pass.putSerializable("personalScoreBoard", this.personalScoreBoard);
        pass.putSerializable("globalScoreBoard", this.globalScoreBoard);
        tmp.putExtras(pass);
        startActivity(tmp);
    }

    /**
     * add start button
     */
    private void addStartButtonListener() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToDifficulty();
            }
        });
    }

    /**
     * add resume button
     */
    private void addResumeButtonListener() {
        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = (BoardManager) user.getHistory().get("resumeHistory");
                if (boardManager != null) {
                        boardManager = (BoardManager) user.getHistory().get("resumeHistory");
                    user.getHistory().put("resumeHistory", null);
                    switchToGame();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder
                            (getActivity());
                    builder.setMessage("History not found");
                    AlertDialog d = builder.create();
                    d.show();
                }
            }
        });
    }

    /**
     * add load button
     */
    private void addLoadButtonListener() {
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
            }
        });
    }

    /**
     * start SlideDifficultActivity and pass useful info and data to next activity
     */
    private void switchToDifficulty(){
        Intent tmp = new Intent(getActivity(), SlideDifficultyActivity.class);
        myDB.updateUser(username, this.user);
        myDB.updateAccountManager(this.users);
        startActivity(tmp);}

    /**
     * start playing game with MainSlideActivity
     */
    private void switchToGame() {
        Intent tmp = new Intent(getActivity(), MainSlideActivity.class);
        Bundle pass = new Bundle();
        myDB.updateUser(username, this.user);
        myDB.updateAccountManager(this.users);
        pass.putSerializable("boardManager", this.boardManager);
        tmp.putExtras(pass);
        startActivity(tmp);
    }

    /**
     * get correct user info
     */
    private void updateUser(){
        this.user = myDB.selectUser(username);
    }

    /**
     * load game
     */
    private void load(){
        updateUser();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose an memory");
        // add a list
        String[] memoryList = {"3x3", "4x4","5x5"};
        builder.setItems(memoryList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: // 3x3
                        boardManager = (BoardManager) user.getHistory().get("history3x3");
                        break;
                    case 1: // 4x4
                        boardManager = (BoardManager) user.getHistory().get("history4x4");
                        break;
                    case 2: // 5x5
                        boardManager = (BoardManager) user.getHistory().get("history5x5");
                        break;
                }
                if(boardManager!=null){
                    try {
                        boardManager = (BoardManager) boardManager.clone();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
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

}
