package fall2018.csc2017.slidingtiles;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Main2048Activity extends AppCompatActivity {
    private ScoreBoard personalScoreBoard;
    private Board2048Manager boardManager;

    static boolean isPaused;

    boolean isWin;

    double tempCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2048);
        createTileButtons(this);
        isPaused = false;
        Thread t = new Thread(){
            @Override
            public void run(){
                while(!isInterrupted()) {
                    if (!isPaused) {
                        try {
                            Thread.sleep(500);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (isWin && !isPaused) {
                                        isPaused = true;
//                                        user.getHistory().put("resumeHistory", null);
                                        winAlert();
                                    }
                                    else {
                                        if (tempCount < 2) {
                                            tempCount += 0.5;
                                        } else {
                                            tempCount = 0;
                                            autoSave();
                                            }
                                        }
                                    }
                                }
                            );
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        t.start();
    }

    private void autoSave() {
    }
    private void createTileButtons(Context context) {
        Board2048 board = boardManager.getBoard();
        ArrayList tileButtons = new ArrayList<>();
    }

    private void winAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Main2048Activity.this);
        int score = getScore();
        builder.setMessage("you got " + String.valueOf(score) + " !")

                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Main2048Activity.this.finish();
                        //switchToScoreBoard();
                    }
                })
                .setNegativeButton("Back To Home Page", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Main2048Activity.this.finish();
                        //user.getHistory().put("resumeHistory", null);
                        //switchToGameCenter();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private int getScore() {
        int score;
        //score = personalScoreBoard.calculateScore(boardManager);
        return 0;

    }
}
