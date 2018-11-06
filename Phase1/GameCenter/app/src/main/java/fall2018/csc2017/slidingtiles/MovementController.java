package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.widget.Toast;

/**
 * The swiping move operation controller
 */
class MovementController {

    private BoardManager boardManager = null;

    /**
     * Set the boardManager to boardManager.
     * @param boardManager the BoardManger to set
     */
    void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    /**
     * Process a tap movement
     * @param context
     * @param position the position where a tap occurred
     */
    void processTapMovement(Context context, int position) {
        if (boardManager.isValidTap(position)) {
            boardManager.touchMove(position);
            if (boardManager.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
