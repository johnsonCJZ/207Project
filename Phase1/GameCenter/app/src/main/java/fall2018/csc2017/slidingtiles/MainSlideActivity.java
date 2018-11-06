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
import android.widget.Button;
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

    private ScoreBoard personalScoreBoard;

    private ScoreBoard globalScoreBoard;

    /**
     * If a game is paused.
     */
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
     * Grid View and calculated column height and width based on device size.
     */
    private GestureDetectGridView gridView;
    private static int columnWidth, columnHeight;

    /**
     * Time count.
     */
    private double count=0;

    /**
     * Time count for autosave purpose.
     */
    private double tempCount = 0; //

    /**
     * UserAccount associated to the game.
     */
    private UserAccount user;

    /**
     * Dimension of the board game.
     */
    private int size;

    /**
     * UserAccountManager associated to the UserAccount.
     */
    private UserAccountManager users;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    /**
     * Switch to GameCenterActivity.
     */
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
        isPaused = true;
        boardManager.setTime(count);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to save/override this game?")

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            saveHistory(dialog);
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
        alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                isPaused = false;
                dialog.cancel();
            }
        });
        alert.show();

    }

    /**
     * Return the score of the finished game.
     * @return the score of the finished game.
     */
    private int getScore(){
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

    /**
     * Save the game history.
     * @param dialog
     * @throws CloneNotSupportedException
     */
    private void saveHistory(DialogInterface dialog) throws CloneNotSupportedException {
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

    /**
     * Automatically save the game for resuming.
     * @throws CloneNotSupportedException
     */
    private void autoSave() throws CloneNotSupportedException {
        boardManager.setTime(count);
        user.getHistory().put("resumeHistory", (BoardManager) boardManager.clone());
        saveToFile(UserAccountManager.USERS);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try {
            getAllInfo(); // pass in all useful data from last activity, including boardManager
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        createTileButtons(this);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.textView6);
        count=boardManager.getTime();
        isPaused = false;
        Thread t = new Thread(){
            @Override
            public void run(){
                while(!isInterrupted()) {
                    if (!isPaused) {
                        try {
                            Thread.sleep(10);
                            if (boardManager.puzzleSolved()) {
                                this.interrupt();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (boardManager.puzzleSolved() && !isPaused) {
                                        boardManager.setTime(count);
                                        isPaused = true;
                                        user.getHistory().put("resumeHistory", null);
                                        winAlert();
                                    }
                                    else {
                                        count += 0.01;
                                        if (tempCount < 2) {
                                            tempCount += 0.01;
                                        } else {
                                            tempCount = 0;
                                            try {
                                                autoSave();
                                            } catch (CloneNotSupportedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        textView.setText(String.valueOf(df2.format(count)) + " s");
                                    }
                                }
                            });
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

    /**
     * Alert when a puzzle is solved.
     */
    private void winAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainSlideActivity.this);
        int score = getScore();
        builder.setMessage("you got " + String.valueOf(score) + " !")

                .setPositiveButton("See my rank", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainSlideActivity.this.finish();
                        switchToScoreBoard();
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

    /**
     * Add the cheat button.`
     */
    private void addCheatButton() {
        Button cheat = findViewById(R.id.Cheating);
        cheat.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                cheat();
                display();
            }
        });
    }

    /**
     * Add the undo button.
     */
    private void addUndoButton() {
        Button undo = findViewById(R.id.Undo);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager.undo();
                display();
            }
        });
    }

    /**
     * Add the redo button.
     */
    private void addRedoButton() {
        Button redo = findViewById(R.id.Redo);
        redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager.redo();
                display();
            }

        });
    }

    /**
     * Manage the board to a ready-to-solve state. The cheat operation is only for testing and will
     * not be saved into history. This functionality will be removed in Phase 2.
     */
    private void cheat() {
        List<Tile> tiles = new ArrayList<>();
        int numTiles = size*size;
        for (int tileNum = 1; tileNum != numTiles-1; tileNum++) {
            tiles.add(new Tile(tileNum));
        }
        tiles.add(new Tile(0));
        tiles.add(new Tile(numTiles-1));
        boardManager.getBoard().setTiles(tiles);
    }

    /**
     * Switch to ScoreBoard activity/view.
     */
    private void switchToScoreBoard(){
        Intent tmp = new Intent(this, ScoreBoardActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("user",this.user);
        pass.putSerializable("allUsers", this.users);
        pass.putSerializable("personalScoreBoard", this.personalScoreBoard);
        pass.putSerializable("globalScoreBoard", this.globalScoreBoard);
        tmp.putExtras(pass);
        startActivity(tmp);
    }

    /**
     * Receive all the info(User, Size, BoardManager, ScoreBoards)from previous activity/view.
     * @throws CloneNotSupportedException
     */
    private void getAllInfo() throws CloneNotSupportedException {
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();

        assert extra != null;
        this.user = (UserAccount) extra.getSerializable("user");
        this.users = (UserAccountManager) extra.getSerializable("allUsers");
        loadFromFile(UserAccountManager.USERS);

        for (UserAccount u : users.getUserList()) {
            if (u.getName().equals(user.getName())) {
                this.user = u;
            }
        }
        this.boardManager = (BoardManager) extra.getSerializable("boardManager");
        assert this.boardManager != null;
        this.boardManager = (BoardManager) this.boardManager.clone();
        this.size = this.boardManager.getBoard().getDimension();
        switch (size) {
            case 3:
                this.personalScoreBoard = this.user.getScoreBoard("history3x3");
                this.globalScoreBoard = this.users.getSlideTilesGlobalScoreBoard("history3x3");
                break;

            case 4:
                this.personalScoreBoard = this.user.getScoreBoard("history4x4");
                this.globalScoreBoard = this.users.getSlideTilesGlobalScoreBoard("history4x4");
                break;

            case 5:
                this.personalScoreBoard = this.user.getScoreBoard("history5x5");
                this.globalScoreBoard = this.users.getSlideTilesGlobalScoreBoard("history5x5");
                break;

            default:
                this.personalScoreBoard = this.user.getScoreBoard("history4x4");
                this.globalScoreBoard = this.users.getSlideTilesGlobalScoreBoard("history4x4");
        }
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

    /**
     * Load from file fileName
     * @param fileName the name of the file
     */
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
