package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.ObjectOutputStream;


public class SlideDifficultyActivity extends AppCompatActivity {

    private BoardManager boardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_difficulty);
        addStart4x4Button();
    }
    private void addStart4x4Button() {
        Button startButton = findViewById(R.id.button3);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = new BoardManager(4);
                switchToGame();
            }
        });
    }

    private void switchToGame() {
        Intent tmp = new Intent(this, MainSlideActivity.class);
        saveToFile(SlideGameActivity.TEMP_SAVE_FILENAME);
        startActivity(tmp);
    }

    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(boardManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}
