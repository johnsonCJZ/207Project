package fall2018.csc2017.slidingtiles;


import android.content.Intent;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

import fall2018.csc2017.slidingtiles.database.DatabaseHelper;

public class MainActivityController {

    DatabaseHelper myDB;
    /**
     * The per-user scoreboard
     */
    private ScoreBoard personalScoreBoard;

    /**
     * The per-game scoreboard
     */
    private ScoreBoard globalScoreBoard;

    /**
     * If a game is paused.
     */
    private boolean isPaused;

    /**
     * The MineBoard manager.
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
    private int count = 0;

    /**
     * Time count for autosave purpose.
     */
    private int tempCount = 0;

    /**
     * UserAccount associated to the game.
     */
    private UserAccount user;

    /**
     * Dimension of the MineBoard.
     */
    private int dimension;

    private int mine;

    /**
     * UserAccountManager associated to the UserAccount.
     */
    private UserAccountManager users;



}