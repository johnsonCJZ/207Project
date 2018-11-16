package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreBoardActivity extends AppCompatActivity{
    /**
     * User playing game
     */
    private UserAccount user;
    /**
     * All users in database
     */
    private UserAccountManager users;

    /**
     * Initialize activity with corresponding user and data
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        getUsers();
        getBoard();
        addBackToHomePageButton();
    }

    /**
     * get user info and data from last activity
     */
    private void getUsers(){
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();
        this.user=(UserAccount) extra.getSerializable("user");
        this.users = (UserAccountManager) extra.getSerializable("allUsers");
    }

    /**
     * get board from last activity
     */
    private void getBoard() {
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();
        ScoreBoard scoreBoard = (ScoreBoard) extra.getSerializable("scoreBoard");
        setBoard((ArrayList<Object[]>) scoreBoard.getScoreList());
    }

    /**
     * button go back to home page.
     */

    private void addBackToHomePageButton() {
        Button startButton = findViewById(R.id.homePageButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToHomePage();
            }
        });
    }

    /**
     * action go back to home page
     */

    private void switchToHomePage() {
        Intent tmp = new Intent(this, MainInfoPanelActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("user",this.user);
        pass.putSerializable("allUsers", this.users);
        pass.putString("fragment", "Slide");
        tmp.putExtras(pass);
        startActivity(tmp);
    }

    /**
     * take a list of score and return format for front end
     * @param scoreList a list of score
     */

    private void setBoard(ArrayList<Object[]> scoreList) {

        for (int i = 0; i < 11; i++) {
            String name = "Name" + i;
            String score = "Score" + i;
            TextView t1 = findViewById
                    (getResources().getIdentifier(name, "id", getPackageName()));
            TextView t2 = findViewById
                    (getResources().getIdentifier(score, "id", getPackageName()));

            if (i < scoreList.size()) {
            t1.setText((String) scoreList.get(i)[0]);
            t2.setText(String.valueOf(scoreList.get(i)[1]));
            }

            else {
                t1.setText("");
                t2.setText("");
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}


