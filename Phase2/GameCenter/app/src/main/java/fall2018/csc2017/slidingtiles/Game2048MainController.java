package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import java.util.ArrayList;

public class Game2048MainController {
    public ArrayList<Button> updateTileButtons(Game2048BoardManager boardManager, ArrayList<Button> tileButtons) {
        Game2048Board board = boardManager.getBoard();
        //If the board has been changed.
        if (board.isChanged()) {
            if (board.findEmpty().size() != 0) { //If no empty slot left, meaning cheated. Otherwise generate a new random tile.
                Game2048Tile newTile = board.addTile();
                if (newTile != null) {
                    newTile.setAnimation();
                }
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
        return tileButtons;
    }

    private void addFadeInAnimation(Button b) {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(1000);
        AnimationSet animation = new AnimationSet(true); //change to false
        animation.addAnimation(fadeIn);
        b.setAnimation(animation);
    }

    public ArrayList<Button> createTileButtons(Context context, Game2048BoardManager boardManager, ArrayList<Button> tileButtons) {
        Game2048Board board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int i = 0; i < board.getDimension() * board.getDimension(); i++) {
            Button tmp = new Button(context);
            tmp.setBackgroundResource(board.getTiles().get(i).getBackground());
            tileButtons.add(tmp);
        }
        return tileButtons;
    }
}
