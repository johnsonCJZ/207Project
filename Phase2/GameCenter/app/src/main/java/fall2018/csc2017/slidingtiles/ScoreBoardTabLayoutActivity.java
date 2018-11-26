package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import fall2018.csc2017.slidingtiles.database.DatabaseHelper;

public class ScoreBoardTabLayoutActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    private ScoreBoard personalScoreBoard;

    private ScoreBoard globalScoreBoard;

    /**
     * User playing game
     */
    private UserAccount user;

    /**
     * Initialize activity with corresponding user and data
     * @param savedInstanceState
     */

    private DatabaseHelper myDB;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myDB = new DatabaseHelper(this);
        username = (String)DataHolder.getInstance().retrieve("current user");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board_tab_layout);
        tabLayout =(TabLayout) findViewById(R.id.tabs);
        appBarLayout = findViewById(R.id.appBar);
        viewPager = (ViewPager)findViewById(R.id.ScoreView);
        getUsers();
        getBoard();
        startFragment();

    }

    private Bundle passInfoToFragment(ScoreBoard s){
        Bundle pass = new Bundle();
        pass.putSerializable("board",s);
        return pass;
    }

    private void startFragment(){
        ScoreBoardFragmentPagerAdaptor adaptor = new ScoreBoardFragmentPagerAdaptor(getSupportFragmentManager());
        ScoreBoardFragmentActivity g = new ScoreBoardFragmentActivity();
        ScoreBoardFragmentActivity p = new ScoreBoardFragmentActivity();
        g.setArguments(passInfoToFragment(this.globalScoreBoard));
        p.setArguments(passInfoToFragment(this.personalScoreBoard));
        adaptor.addFragment(g,"global");
        adaptor.addFragment(p,"personal");
        viewPager.setAdapter(adaptor);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * get user info and data from last activity
     */
    private void getUsers(){
        this.user=myDB.selectUser(username);
    }

    /**
     * get board from last activity
     */
    private void getBoard() {
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();
        personalScoreBoard = (ScoreBoard) extra.getSerializable("personalScoreBoard");
        globalScoreBoard =  (ScoreBoard) extra.getSerializable("globalScoreBoard");
    }

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


}
