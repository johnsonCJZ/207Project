package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreBoardActivity extends AppCompatActivity {
    private ArrayList<TextView[]> textViewsArrayList = new ArrayList<>();
    private UserAccount user;
    private UserAccountManager users;
    private BoardManager boardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        getUsers();
        getBoard();
        addBackToHomePageButton();
    }

    private void getUsers(){
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();
        this.user=(UserAccount) extra.getSerializable("user");
        this.users = (UserAccountManager) extra.getSerializable("allUsers");
    }

    private void getBoard() {
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();
        this.boardManager = (BoardManager) extra.getSerializable("boardManager");
        ScoreBoard scoreBoard = (ScoreBoard) extra.getSerializable("scoreBoard");
        setBoard((ArrayList<Object[]>) scoreBoard.getScoreList());
    }

    private void addBackToHomePageButton() {
        Button startButton = findViewById(R.id.button16);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToHomePage();
            }
        });
    }

    private void switchToHomePage() {
        Intent tmp = new Intent(this, GameCenterActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("user",this.user);
        pass.putSerializable("allUsers", this.users);
        pass.putSerializable("boardManager", this.boardManager);

        tmp.putExtras(pass);
        startActivity(tmp);
    }

    private void setBoard(ArrayList<Object[]> scoreList) {
        String name = "Name";
        String score = "Score";

        int[] name_list = {
                R.id.Name0,
                R.id.Name1,
                R.id.Name2,
                R.id.Name3,
                R.id.Name4,
                R.id.Name5,
                R.id.Name6,
                R.id.Name7,
                R.id.Name8,
                R.id.Name9,
                R.id.Name10
        };

        int[] score_list = {
                R.id.Score0,
                R.id.Score1,
                R.id.Score2,
                R.id.Score3,
                R.id.Score4,
                R.id.Score5,
                R.id.Score6,
                R.id.Score7,
                R.id.Score8,
                R.id.Score9,
                R.id.Score10
        };

        //int cursor = 0;
        for (int i = 0; i < scoreList.size(); i++) {
//                String tempName = name + String.valueOf(cursor);
//                String tempScore = score + String.valueOf(cursor);
////                int tempN = getResources().getIdentifier(tempName, "id",
////                        getPackageName());
            TextView t1 = findViewById(name_list[i]);
////                int tempS = getResources().getIdentifier(tempScore, "id",
////                        getPackageName());
            TextView t2 = findViewById(score_list[i]);
//                if (i != null) {
            t1.setText((String) scoreList.get(i)[0]);
            t2.setText(String.valueOf(scoreList.get(i)[1]));
//                }
//                else {
//                    t1.setText("N/A");
//                    t2.setText("N/A");
//                }
//                cursor++;
        }
    }
}


