package fall2018.csc2017.slidingtiles;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * The game activity.
 */
public class MainSlideActivity extends AppCompatActivity implements Observer {

    private ScoreBoard scoreBoard;

    private Object[] score;

    private boolean isPaused;

    private static DecimalFormat df2 = new DecimalFormat(".##");
    /**
     * The board manager.
     */
    private BoardManager boardManager;
    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    /**
     * Constants for swiping directions. Should be an enum, probably.
     */
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    // Grid View and calculated column height and width based on device size
    private GestureDetectGridView gridView;
    private static int columnWidth, columnHeight;
    private double count=0;
    private double tempcount = 0; //
    private UserAccount user;
    private int size;
    private UserAccountManager users;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    // Display
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    private void switchToGameCenter() {
        Intent intent = new Intent(this, GameCenterActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("user",this.user);
        pass.putSerializable("allUsers", this.users);
        intent.putExtras(pass);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        boardManager.setTime(count);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to save/override this game?")

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        isPaused = true;
                        try {
                            getHistory(dialog);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        MainSlideActivity.this.finish();
                        switchToGameCenter();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainSlideActivity.this.finish();
                        switchToGameCenter();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

/**
 * alert box in alter box, not mature enough
 *
    private void overrideAlert(final String memory){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your had a history, wanna override?")

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        user.getHistory().replace(memory,boardManager);
                        saveToFile(UserAccountManager.USERS);
                        dialog.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

*/

    private int clearHistoryAndGetScore(){
        int score;
        switch (size) {
            case 3:
                user.getHistory().put("history3x3",null);
                scoreBoard=user.getScoreBoard("history3x3");
                break;
            case 4:
                user.getHistory().put("history4x4",null);
                scoreBoard=user.getScoreBoard("history4x4");
                break;

            case 5:
                user.getHistory().put("history5x5",null);
                scoreBoard=user.getScoreBoard("history5x5");
                break;

            default:
                user.getHistory().put("history4x4",null);
                scoreBoard=user.getScoreBoard("history4x4");


        }
        scoreBoard.setStrategy(new SlidingTilesScoreStrategy());
        score = scoreBoard.calculateScore(boardManager);
        this.score=new Object[2];
        this.score[0] = user.getName();
        this.score[1] = score;
        scoreBoard.addAndSort(this.score);
        saveToFile(UserAccountManager.USERS);
        return score;

    }


    private void getHistory(DialogInterface dialog) throws CloneNotSupportedException {
        switch (size) {
            case 3:
                user.getHistory().put("history3x3", (BoardManager) boardManager.clone());
                saveToFile(UserAccountManager.USERS);
                break;
            case 4:
                user.getHistory().put("history4x4",(BoardManager) boardManager.clone());
                saveToFile(UserAccountManager.USERS);
                break;

            case 5:
                user.getHistory().put("history5x5",(BoardManager) boardManager.clone());
                saveToFile(UserAccountManager.USERS);
                break;
                }
                dialog.cancel();

    }

    private void autoSave() throws CloneNotSupportedException {
        boardManager.setTime(count);
        user.getHistory().put("resumeHistory", (BoardManager) boardManager.clone());
        saveToFile(UserAccountManager.USERS);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try {
            getUserAndSize(); // pass in all useful data from last activity, including boardManager
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        createTileButtons(this);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView)findViewById(R.id.textView6);
        count=boardManager.getTime();
        isPaused = false;
        Thread t = new Thread(){
            @Override
            public void run(){
                while(!isInterrupted()){
                    if (isPaused) {
                        break;
                    }
                    try{
                        Thread.sleep(10);
                        if(boardManager.puzzleSolved() || isPaused) {
                            this.interrupt();
                            boardManager.setTime(count);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (boardManager.puzzleSolved()) {
                                    isPaused = true;
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainSlideActivity.this);
                                    int score = clearHistoryAndGetScore();
                                    builder.setMessage("you got "+String.valueOf(score)+" !")

                                            .setPositiveButton("See my rank", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    MainSlideActivity.this.finish();
                                                    addScoreBoard();
                                                }
                                            })
                                            .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    MainSlideActivity.this.finish();
                                                    user.getHistory().put("resumeHistory", null);
                                                    switchToGameCenter();
                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                }

                                count +=0.01;
                                if (tempcount < 2) {
                                    tempcount += 0.01;
                                }
                                else {
                                    tempcount = 0;
                                    try {
                                        autoSave();
                                    } catch (CloneNotSupportedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                textView.setText(String.valueOf(df2.format(count))+" s");

                            }
                        });
                    }catch (InterruptedException e){
                        e.printStackTrace();
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
        // redo and undo buttons

        Button redo = findViewById(R.id.Redo);
        Button undo = findViewById(R.id.Undo);
        Button cheat = findViewById(R.id.Cheating);
        redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager.redo();
                display();
            }

        });

        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager.undo();
                display();
            }
        });
        cheat.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                setUpCorrect();
                display();
        }
    });
    }

    //cheat functions
    private List<Tile> makeTiles() {
        List<Tile> tiles = new ArrayList<>();
        int numTiles = size*size;
        for (int tileNum = 1; tileNum != numTiles-1; tileNum++) {
            tiles.add(new Tile(tileNum));
        }
        tiles.add(new Tile(0));
        tiles.add(new Tile(numTiles-1));

        return tiles;
    }

    /**
     * Make a solved Board.
     */
    private void setUpCorrect() {
        List<Tile> tiles = makeTiles();
        boardManager.getBoard().setTiles(tiles);
    }




    //
    private void addScoreBoard(){
        Intent tmp = new Intent(this, ScoreBoardActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("user",this.user);
        pass.putSerializable("allUsers", this.users);
        pass.putSerializable("boardManager",boardManager);
        pass.putSerializable("scoreBoard", scoreBoard);
        tmp.putExtras(pass);
        startActivity(tmp);
    }


    private void getUserAndSize() throws CloneNotSupportedException {
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();
        this.user=(UserAccount) extra.getSerializable("user");
        this.users = (UserAccountManager) extra.getSerializable("allUsers");
        loadFromFile(UserAccountManager.USERS);
        for (UserAccount u: users.getUserList()){
            if(u.getName().equals(user.getName())){
                this.user = u;
                }
            }
        this.boardManager = (BoardManager) extra.getSerializable("boardManager");
        this.boardManager = (BoardManager) this.boardManager.clone();
        this.size = this.boardManager.getBoard().getDimension();
        }






    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        Board board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != boardManager.getBoard().getDimension(); row++) {
            for (int col = 0; col != boardManager.getBoard().getDimension(); col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        Board board = boardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / boardManager.getBoard().getDimension();
            int col = nextPos % boardManager.getBoard().getDimension();
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }


    /**
     * Save the board manager to fileName.
     *
     * @param fileName the name of the file
     */
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

    private void loadFromFile(String fileName) {

        try {
            InputStream inputStream = this.openFileInput(fileName);
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
