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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        getBoard();
        addBackToHomePageButton();
    }

    private void getBoard() {
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();
//        BoardManager boardManager = (BoardManager) extra.getSerializable("boardManager");
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
        startActivity(tmp);
    }
    private void setBoard(ArrayList<Object[]> scoreList){
        String name = "Name";
        String score = "Score";

//        int cursor = 0;
//        for (int i=0; i < scoreList.size(); i++){
//                String tempName = name + String.valueOf(cursor);
//                String tempScore = score + String.valueOf(cursor);
////                int tempN = getResources().getIdentifier(tempName, "id",
////                        getPackageName());
                TextView t1 = findViewById(R.id.Name0);
////                int tempS = getResources().getIdentifier(tempScore, "id",
////                        getPackageName());
                TextView t2 = findViewById(R.id.Score0);
//                if (i != null) {
                    t1.setText((String) scoreList.get(0)[0]);
                    t2.setText((String) scoreList.get(0)[1]);
//                }
//                else {
//                    t1.setText("N/A");
//                    t2.setText("N/A");
//                }
//                cursor++;
    }}


