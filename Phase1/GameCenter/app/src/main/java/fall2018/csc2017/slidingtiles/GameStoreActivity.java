package fall2018.csc2017.slidingtiles;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

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
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context= getApplicationContext();
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
                Toasty.warning(context, "Coming Soon!", Toast.LENGTH_SHORT, true).show();
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
                Toasty.warning(context, "You have this game already!", Toast.LENGTH_SHORT, true).show();
            }
        });}



}
