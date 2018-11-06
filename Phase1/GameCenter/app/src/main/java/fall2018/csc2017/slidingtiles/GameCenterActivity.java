package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
/**
 *The game center activity, for choosing game to play/ manage games user has
 */
public class GameCenterActivity extends AppCompatActivity {
    /**
     * The current user playing the game.
     */
    private UserAccount user;
    /**
     * UserAccountManager keep record of all users data.
     */
    private UserAccountManager users;

    /**
     * Initialize activity/view from start.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_center);
        addOpenGameStore();
        addOpenSlide();
        getUsers();
    }

    /**
     * Get user info passing from last view/activity.
     */

    private void getUsers(){
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();
        this.user=(UserAccount) extra.getSerializable("user");
        this.users = (UserAccountManager) extra.getSerializable("allUsers");
    }

    /**
     * Set up button to open slide game
     */
    public void addOpenSlide(){
        ImageButton gameStore;
        gameStore = findViewById(R.id.Slide);
        gameStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSlide();
            }

        });
    }

    /**
     *  Actions after button is triggered, jump to slide game while passing info to
     *  slide game activity.
     */
    public void openSlide(){
        Intent intent = new Intent(this, SlideGameActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("user",this.user);
        pass.putSerializable("allUsers", this.users);
        intent.putExtras(pass);
        startActivity(intent);
    }

    /**
     * Actions after button is trigger, jump to game store to allow user manage their games.
     */

    public void addOpenGameStore(){
        Button gameStore;
        gameStore = findViewById(R.id.store);
        gameStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameStore();
            }

        });
    }

    /**
     * Actions after button is triggered, jump to game store while passing info to
     * game store activity.
     */
    public void openGameStore(){
        Intent intent = new Intent(this, GameStoreActivity.class);
        startActivity(intent);
    }


}
