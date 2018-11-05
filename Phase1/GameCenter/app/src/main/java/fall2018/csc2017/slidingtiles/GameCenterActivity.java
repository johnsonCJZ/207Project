package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class GameCenterActivity extends AppCompatActivity {
    private UserAccount user;
    private UserAccountManager users;
    private BoardManager boardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_center);
        addOpenGameStore();
        addOpenSlide();
        getUsers();
        getBoard();


    }
    private void getBoard() {
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();
        this.boardManager = (BoardManager) extra.getSerializable("boardManager");
    }

    private void getUsers(){
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();
        this.user=(UserAccount) extra.getSerializable("user");
        this.users = (UserAccountManager) extra.getSerializable("allUsers");
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
        Intent intent = new Intent(this, SlideGameActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("user",this.user);
        pass.putSerializable("allUsers", this.users);
        pass.putSerializable("boardManager", this.boardManager);
        intent.putExtras(pass);
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
