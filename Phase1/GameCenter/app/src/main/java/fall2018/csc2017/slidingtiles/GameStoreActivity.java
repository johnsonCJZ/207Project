package fall2018.csc2017.slidingtiles;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class GameStoreActivity extends AppCompatActivity {
    /**
     * The game store activity, for managing users' games, click pic to buy game
     * A user can have 0 or many games in hist possession
     * In progress, for phase 2
     * In progress
     * @param savedInstanceState
     */

    /**
     *  Initialize game store.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_store_acivity);
        addOpenPong();
        addOpenSlide();
    }

    /**
     * add pong game to user's possession
     * In progress
     */
    private void addOpenPong() {
        ImageButton gamePong;
        gamePong = findViewById(R.id.Pong);
        gamePong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GameStoreActivity.this);
                builder.setMessage("Coming Soon!");

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    /**
     * add slide game to user's possession
     * In progress
     */
    private void addOpenSlide() {
        ImageButton gamePong;
        gamePong = findViewById(R.id.Slide);
        gamePong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GameStoreActivity.this);
                builder.setMessage("You have this game already!");

                AlertDialog alert = builder.create();
                alert.show();
            }
        });}



}
