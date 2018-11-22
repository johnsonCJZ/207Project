package fall2018.csc2017.slidingtiles;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Main2048Activity extends AppCompatActivity implements Observer {
    private ScoreBoard personalScoreBoard;
    private ScoreBoard globalScoreBoard;
    private Board2048Manager boardManager;
    private GestureDetectGridView gridView;
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
        try {
            getAllInfo(); // pass in all useful data from last activity, including boardManager
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
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
        gridView = (GestureDetectGridView)findViewById(R.id.grid2048);
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
        addCheatButton();
    }

    private void cheat() {
    boardManager.cheat();
    }

    private void getAllInfo() throws CloneNotSupportedException {
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();

        assert extra != null;
        this.user = (UserAccount) extra.getSerializable("user");
        this.users = (UserAccountManager) extra.getSerializable("allUsers");
        loadFromFile();

        for (UserAccount u : users.getUserList()) {
            if (u.getName().equals(user.getName())) {
                this.user = u;
            }
        }
        this.boardManager = (Board2048Manager) extra.getSerializable("boardManager");
        assert this.boardManager != null;
        this.boardManager = (Board2048Manager) this.boardManager.clone();
//        this.size = this.boardManager.getBoard().getDimension();
    }


    private void addCheatButton() {
        Button cheat = findViewById(R.id.Cheat2048);
        cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cheat();
                display();
            }
        });
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
        Tile2048 newTile = board.addTile();
        if (newTile!=null){
            newTile.setAnimation();
        }
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / boardManager.getBoard().getDimension();
            int col = nextPos % boardManager.getBoard().getDimension();
            if (board.getTile(row, col).getFadeIn()){
                addFadeInAnimation(b);
                board.getTile(row, col).removeFadeIn();
            }
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
        // add a tile if the board has been modified and it has empty spot

    }

    private void autoSave() {
    }

    private void addFadeInAnimation(Button b){
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(1000);
        AnimationSet animation = new AnimationSet(true); //change to false
        animation.addAnimation(fadeIn);
        b.setAnimation(animation);

    }

    private void createTileButtons(Context context) {
        Board2048 board = boardManager.getBoard();
        ArrayList tileButtons = new ArrayList<>();
        for (int row = 0; row != boardManager.getBoard().getDimension(); row++) {
            for (int col = 0; col != boardManager.getBoard().getDimension(); col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                tileButtons.add(tmp);
            }
        }
        this.tileButtons=tileButtons;
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
                .setNegativeButton("See My Rank ", new DialogInterface.OnClickListener() {
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

    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(users);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void loadFromFile() {
        try {
            InputStream inputStream = this.openFileInput(UserAccountManager.USERS);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                users = (UserAccountManager) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }


}
