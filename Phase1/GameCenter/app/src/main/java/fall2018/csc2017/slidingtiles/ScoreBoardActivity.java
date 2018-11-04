package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreBoardActivity extends AppCompatActivity {
    private BoardManager boardManager;
    private ScoreBoard scoreBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        getBoard();
    }

    private void getBoard() {
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();
        this.boardManager = (BoardManager) extra.getSerializable("boardManager");
        this.scoreBoard = (ScoreBoard) extra.getSerializable("scoreBoard");
        addBackToHomePageButton();
        setBoard((ArrayList<Object[]>) this.scoreBoard.getScoreList());
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
        int cursor = 0;
        for (Object[] i :scoreList){
            String tempName = name + String.valueOf(cursor);
            String tempScore = score + String.valueOf(cursor);
            int tempN = getResources().getIdentifier(tempName, "id",
                    getPackageName());
            TextView t1 = findViewById(tempN);
            int tempS = getResources().getIdentifier(tempScore, "id",
                    getPackageName());
            TextView t2 = findViewById(tempS);
            t1.setText((String)i[0]);
            t2.setText((Integer)i[1]);
            cursor++;

    }}

}
