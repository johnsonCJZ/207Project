package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * The swiping move operation controller
 */
class MovementController {

    private Manager boardManager = null;

    /**
     * Set the boardManager to boardManager.
     * @param boardManager the BoardManger to set
     */
    void setBoardManager(Manager boardManager) {
        this.boardManager = boardManager;
    }

    /**
     * Process a tap movement
     * @param context
     * @param position the position where a tap occurred
     */
    void processTapMovement(Context context, int position) {
        if (boardManager instanceof BoardManager) {
            BoardManager b = (BoardManager) boardManager;
            if (b.isValidTap(position)) {
                b.touchMove(position);
                if (b.isWon()) {
                    Toasty.success(context, "YOU WIN!!", Toast.LENGTH_SHORT, true).show();
                }
            } else {
                Toasty.warning(context, "Invalid Tap!", Toast.LENGTH_SHORT, true).show();
            }
        }
        else if (boardManager instanceof MineSweeperManager) {
            MineSweeperManager b = (MineSweeperManager) boardManager;
            b.touchMove(position);
            if (b.isLost()) {
                Toasty.warning(context, "YOU LOST!", Toast.LENGTH_SHORT, true).show();
            }
            else if (b.isWon()) {
                Toasty.success(context, "YOU WIN!", Toast.LENGTH_SHORT,true).show();
            }
        }

    }

    void processLongPressMovement(Context context, int position){
        if (boardManager instanceof MineSweeperManager) {
            MineSweeperManager b = (MineSweeperManager) boardManager;
            if (b.getBoard().getTile(position).isObscured()) {
                b.mark(position);
                }
        }
    }

    void processLeftSwipe() {
        if (boardManager instanceof  Board2048Manager) {
            ((Board2048Manager) boardManager).moveLeft();
        }
    }

    void processRightSwipe() {
        if (boardManager instanceof  Board2048Manager) {
            ((Board2048Manager) boardManager).moveRight();
        }
    }

    void processDownSwipe() {
        if (boardManager instanceof  Board2048Manager) {
            ((Board2048Manager) boardManager).moveDown();
        }
    }

    void processUpSwipe() {
        if (boardManager instanceof  Board2048Manager) {
            ((Board2048Manager) boardManager).moveUp();
        }
    }
}
