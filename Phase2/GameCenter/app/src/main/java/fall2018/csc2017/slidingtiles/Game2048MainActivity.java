package fall2018.csc2017.slidingtiles;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.slidingtiles.database.DatabaseHelper;

public class Game2048MainActivity extends AppCompatActivity implements IObserver {
    private ScoreBoard personalScoreBoard;
    private ScoreBoard globalScoreBoard;
    private Game2048BoardManager boardManager;
    private GestureDetectGridView gridView;
    private ArrayList<Button> tileButtons;
    private UserAccount user;
    private UserAccountManager users;

    static boolean isPaused;
    private int columnHeight;
    private int columnWidth;

    double tempCount;
    private boolean isWin;
    private DatabaseHelper myDB;
    private String username;

    private TextView scorePlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myDB = new DatabaseHelper(this);
        username = (String) DataHolder.getInstance().retrieve("current user");
        getAllInfo(); // pass in all useful data from last activity, including boardManager
        getAllComponents();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2048);
        createTileButtons(this);
        isPaused = false;
        Thread t = time();

        t.start();
        setGridView();
    }

    public void getAllComponents(){
        scorePlace = findViewById(R.id.score);
        gridView = (GestureDetectGridView)findViewById(R.id.grid2048);
    }

    @NonNull
    private Thread time() {
        return new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    if (!isPaused) {
                        try {
                            Thread.sleep(500);
                            if (boardManager.isWon()) {
                                this.interrupt();
                            }
                            runOnUiThread(new InGame());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
    }

    private class InGame implements Runnable{
        @Override
        public void run() {
            if ((boardManager.isWon() || boardManager.isLost())
                    && !isPaused) {
                setIsWin();
                isPaused = true;
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

    void setGridView() {
        gridView.setNumColumns(boardManager.getBoard().getDimension());
        gridView.setBoardBoardManager(boardManager);
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
        addNewGameButton();
    }

    private void cheat() {
        boardManager.cheat();
    }

    private void getAllInfo() {
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();

        assert extra != null;
        this.user = myDB.selectUser(username);
        this.users = myDB.selectAccountManager();

        personalScoreBoard = user.getScoreBoard("2048");
        globalScoreBoard = users.getGlobalScoreBoard("2048");
        this.boardManager = (Game2048BoardManager) extra.getSerializable("boardManager");
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

    void addNewGameButton() {
        Button newGame = findViewById(R.id.newGame);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = new Game2048BoardManager();
                isPaused = false;
                Thread t = time();
                t.start();
                setGridView();
            }
        });
    }

    private void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    private void setIsWin() {
        if (boardManager.isWon()) {
            isWin = true;
        }
    }


    private void updateTileButtons() {
        Game2048Board board = boardManager.getBoard();
        if (board.isChanged()) {
            Game2048Tile newTile = board.addTile();
            if (newTile != null) {
                newTile.setAnimation();
            }
            int nextPos = 0;
            for (Button b : tileButtons) {
                int row = nextPos / boardManager.getBoard().getDimension();
                int col = nextPos % boardManager.getBoard().getDimension();
                if (board.getTile(row, col).getFadeIn()) {
                    addFadeInAnimation(b);
                    board.getTile(row, col).removeFadeIn();
                }
                b.setBackgroundResource(board.getTile(row, col).getBackground());
                nextPos++;
            }
        }
        // add a tile if the board has been modified and it has empty spot
        scorePlace.setText("Score: "+String.valueOf(personalScoreBoard.calculateScore(boardManager)));

    }

    private void autoSave() {
        user.setGame2048History("resumeHistory2048", boardManager);
        myDB.updateUser(username, user);
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
        Game2048Board board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int i = 0; i < board.getDimension() * board.getDimension(); i++) {
            Button tmp = new Button(context);
            tmp.setBackgroundResource(board.getTiles().get(i).getBackground());
            tileButtons.add(tmp);
        }

    }

    private void endAlert() {
        int score = getScore();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (isWin) {
            builder.setMessage("You Win!");
        }
        else {
            builder.setMessage("You Lose, get better next time. Your scored "+String.valueOf(score));
        }
        builder.setPositiveButton("Back To Game Center", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                switchToGameCenter();
            }
        })
                .setNegativeButton("See My Rank ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Game2048MainActivity.this.finish();
                        user.setGame2048History("resumeHistory2048", null);
                        switchToScoreBoard();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void switchToScoreBoard(){
        Intent tmp = new Intent(this, ScoreBoardTabLayoutActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("personalScoreBoard", this.personalScoreBoard);
        pass.putSerializable("globalScoreBoard", this.globalScoreBoard);
        tmp.putExtras(pass);
        startActivity(tmp);
    }

    private void switchToGameCenter() {
        Intent intent = new Intent(this, InfoPanelMainActivity.class);
        Bundle pass = new Bundle();
        myDB.updateUser(user.getName(), this.user);
        myDB.updateAccountManager(users);
        pass.putString("fragment", "2048");
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
        myDB.updateUser(user.getName(), this.user);
        myDB.updateAccountManager(users);
        return score;

    }


    @Override
    public void update(IObservable o) {
        display();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onBackPressed() {
        autoSave();
        switchToGameCenter();
    }

}