package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class GameCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_center);
        addOpenGameStore();
        addOpenSlide();


    }

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
    public void openSlide(){
        Intent intent = new Intent(this, MainSlideActivity.class);
        startActivity(intent);
    }

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
    public void openGameStore(){
        Intent intent = new Intent(this, GameStoreActivity.class);
        startActivity(intent);
    }


}
