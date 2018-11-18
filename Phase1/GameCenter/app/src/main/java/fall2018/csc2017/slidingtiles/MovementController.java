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
        BoardManager b = (BoardManager) boardManager;
        if (b.isValidTap(position)) {
            b.touchMove(position);
            if (b.puzzleSolved()) {
                Toasty.success(context, "YOU WIN!!", Toast.LENGTH_SHORT, true).show();
            }
        } else {
            Toasty.warning(context, "Invalid Tap!", Toast.LENGTH_SHORT, true).show();
        }
    }
}
