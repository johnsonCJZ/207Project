package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

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
        pass.putSerializable("user",user);
        return pass;
    }

    private void startFragment(){
        ScoreBoardFragmentPagerAdaptor adaptor = new ScoreBoardFragmentPagerAdaptor(getSupportFragmentManager());
        ScoreBoardFragmentActivity g = new ScoreBoardFragmentActivity();
        ScoreBoardFragmentActivity p = new ScoreBoardFragmentActivity();
        g.setArguments(passInfoToFragment(this.globalScoreBoard));
        p.setArguments(passInfoToFragment(this.personalScoreBoard));
        adaptor.addFragment(g,"global");
        adaptor.addFragment(p,"Personal");
        viewPager.setAdapter(adaptor);
        tabLayout.setupWithViewPager(viewPager);
    }

    private Bundle passInfo(){
        Bundle pass = new Bundle();
        pass.putSerializable("user",this.user);
        pass.putSerializable("allUsers", this.users);
        return pass;
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
