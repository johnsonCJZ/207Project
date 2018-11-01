package fall2018.csc2017.slidingtiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ScoreBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        //setBoard();
    }

    private void setBoard(ArrayList<Pair<String,Integer>> board){
        String name = "Name";
        String score = "Score";
        int cursor = 0;
        for (Pair<String, Integer> i :board){
            String tempName = name + String.valueOf(cursor);
            String tempScore = score + String.valueOf(cursor);
            int tempN = getResources().getIdentifier(tempName, "id",
                    getPackageName());
            TextView t1 = findViewById(tempN);
            int tempS = getResources().getIdentifier(tempScore, "id",
                    getPackageName());
            TextView t2 = findViewById(tempS);
            t1.setText(i.first);
            t2.setText(i.second);


    }}

}
