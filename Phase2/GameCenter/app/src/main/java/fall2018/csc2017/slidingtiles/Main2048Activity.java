package fall2018.csc2017.slidingtiles;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import java.util.ArrayList;

public class Main2048Activity extends AppCompatActivity {
    private ScoreBoard personalScoreBoard;
    private ScoreBoard globalScoreBoard;
    private Board2048Manager boardManager;
    private GestureDetectGridView2048 gridView;
    private ArrayList<Button> tileButtons;
    private UserAccount user;
    private UserAccountManager users;

    static boolean isPaused;
    private int columnHeight;
    private int columnWidth;

    double tempCount;
    private boolean isWin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2048);
        createTileButtons(this);
        isPaused = false;
        Thread t = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    if (!isPaused) {
                        try {
                            Thread.sleep(500);
                            runOnUiThread(new Runnable() {
                                              @Override
                                              public void run() {
                                                  if ((boardManager.isWin() || boardManager.isLose())
                                                          && !isPaused) {
                                                      setIsWin();
                                                      isPaused = true;
//                                        user.getHistory().put("resumeHistory", null);
                                                      endAlert();
                                                  } else {
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

        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(boardManager.getBoard().getDimension());
        gridView.setBoardManager(boardManager);
        boardManager.getBoard().addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / boardManager.getBoard().getDimension();
                        columnHeight = displayHeight / boardManager.getBoard().getDimension();

                        display();

                    }
                });
        addRedoButton();
        addUndoButton();
        addCheatButton();
    }

    private void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    private void setIsWin() {
        if (boardManager.isWin()) {
            isWin = true;
        }
    }


    private void updateTileButtons() {
        Board2048 board = boardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / boardManager.getBoard().getDimension();
            int col = nextPos % boardManager.getBoard().getDimension();
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
    }

    private void autoSave() {
    }

    private void createTileButtons(Context context) {
        Board2048 board = boardManager.getBoard();
        ArrayList tileButtons = new ArrayList<>();
        for (int row = 0; row != boardManager.getBoard().getDimension(); row++) {
            for (int col = 0; col != boardManager.getBoard().getDimension(); col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    private void endAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Main2048Activity.this);
        int score = getScore();
        if (isWin) {
            builder.setMessage("You Win!");
        }
        else {
            builder.setMessage("You Lose, get better next time.");
        }
        builder.setPositiveButton("Back To Game Center", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Main2048Activity.this.finish();
                        switchToGameCenter();
                    }
                })
                .setNegativeButton("Back To ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Main2048Activity.this.finish();
                        //user.getHistory().put("resumeHistory", null);
                        //switchToGameCenter();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void switchToGameCenter() {
            Intent intent = new Intent(this, MainInfoPanelActivity.class);
            Bundle pass = new Bundle();
            pass.putSerializable("user",this.user);
            pass.putSerializable("allUsers", this.users);
            pass.putString("fragment", "Slide");
            intent.putExtras(pass);
            startActivity(intent);
        }

    private int getScore() {
        int score;
        score = personalScoreBoard.calculateScore(boardManager);
        Object[] result = new Object[2];
        result[0] = user.getName();
        result[1] = score;
        personalScoreBoard.addAndSort(result);
        globalScoreBoard.addAndSort(result);
        saveToFile(UserAccountManager.USERS);
        return score;

    }
}
