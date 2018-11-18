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


public class SlideGameFragment extends Fragment {

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
        user= (UserAccount) getArguments().getSerializable("user");
        users= (UserAccountManager) getArguments().getSerializable("allUsers");

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
                        globalScoreBoard = users.getSlideTilesGlobalScoreBoard("history3x3");
                        break;
                    case 1: // 4x4
                        personalScoreBoard = user.getScoreBoard("history4x4");
                        globalScoreBoard = users.getSlideTilesGlobalScoreBoard("history4x4");
                        break;
                    case 2: // 5x5
                        personalScoreBoard = user.getScoreBoard("history5x5");
                        globalScoreBoard = users.getSlideTilesGlobalScoreBoard("history5x5");
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
        pass.putSerializable("user",this.user);
        pass.putSerializable("allUsers", this.users);
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
                boardManager = user.getHistory().get("resumeHistory");
                if (boardManager != null) {
                    try {
                        boardManager = (BoardManager) user.getHistory().get("resumeHistory").clone();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
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
        Bundle pass = new Bundle();
        pass.putSerializable("user",this.user);
        pass.putSerializable("allUsers", this.users);
        tmp.putExtras(pass);
        startActivity(tmp);}

    /**
     * start playing game with MainSlideActivity
     */
    private void switchToGame() {
        Intent tmp = new Intent(getActivity(), MainSlideActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("user",this.user);
        pass.putSerializable("allUsers", this.users);
        pass.putSerializable("boardManager", this.boardManager);
        tmp.putExtras(pass);
        startActivity(tmp);
    }

    /**
     * get correct user info
     */
    private void updateUser(){
        loadFromFile(UserAccountManager.USERS);
        for (UserAccount u: users.getUserList())
            if (u.getName().equals(user.getName())) {
                user = u;
            }
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

    /**
     * load from saved database
     * @param fileName name of database
     */
    private void loadFromFile(String fileName) {

        try {
            InputStream inputStream = getActivity().openFileInput(fileName);
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
