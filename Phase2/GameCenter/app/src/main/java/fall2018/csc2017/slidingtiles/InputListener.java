package fall2018.csc2017.slidingtiles;

// this
// file's
// author
// is
// https://github.com/veryyoung/2048/blob/master/app/src/main/java/me/veryyoung/game2048/InputListener.java

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import static fall2018.csc2017.slidingtiles.GestureDetectGridView.SWIPE_MAX_OFF_PATH;

public class InputListener extends GestureDetector.SimpleOnGestureListener   {

    private static final int SWIPE_MIN_DISTANCE = 0;
    private static final int SWIPE_THRESHOLD_VELOCITY = 25;
    private static final int MOVE_THRESHOLD = 250;
    private static final int RESET_STARTING = 10;

    private float x;
    private float y;
    private float lastdx;
    private float lastdy;
    private float previousX;
    private float previousY;
    private float startingX;
    private float startingY;
    private int previousDirection = 1;
    private int veryLastDirection = 1;
    private boolean hasMoved = false;

    private Game2048BoardManager boardManager;

    public InputListener() {
        super();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        try {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH){
                if (Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH) {
                    return false;
                }
                // up to down swipe
                if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    boardManager.moveDown();
                }
                // down to up swipe
                else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    boardManager.moveUp();
            }   }
            // right to left swipe
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                boardManager.moveLeft();
            }
            // left to right swipe
            else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                boardManager.moveRight();


            }
        } catch (Exception e) {

        }
        return false;
    }

    public void setBoardManager(Game2048BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                startingX = x;
                startingY = y;
                previousX = x;
                previousY = y;
                lastdx = 0;
                lastdy = 0;
                hasMoved = false;
                return true;
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                if (!Main2048Activity.isPaused) {
                    float dx = x - previousX;
                    if (Math.abs(lastdx + dx) < Math.abs(lastdx) + Math.abs(dx)
                            && Math.abs(dx) > RESET_STARTING
                            && Math.abs(x - startingX) > SWIPE_MIN_DISTANCE) {
                        startingX = x;
                        startingY = y;
                        lastdx = dx;
                        previousDirection = veryLastDirection;
                    }
                    if (lastdx == 0) {
                        lastdx = dx;
                    }
                    float dy = y - previousY;
                    if (Math.abs(lastdy + dy) < Math.abs(lastdy) + Math.abs(dy)
                            && Math.abs(dy) > RESET_STARTING
                            && Math.abs(y - startingY) > SWIPE_MIN_DISTANCE) {
                        startingX = x;
                        startingY = y;
                        lastdy = dy;
                        previousDirection = veryLastDirection;
                    }
                    if (lastdy == 0) {
                        lastdy = dy;
                    }
                    if (pathMoved() > SWIPE_MIN_DISTANCE * SWIPE_MIN_DISTANCE) {
                        boolean moved = false;
                        if (((dy >= SWIPE_THRESHOLD_VELOCITY && previousDirection == 1) || y
                                - startingY >= MOVE_THRESHOLD)
                                && previousDirection % 2 != 0) {
                            moved = true;
                            previousDirection = previousDirection * 2;
                            veryLastDirection = 2;
                            boardManager.moveDown();
                            // this is when swipe down

                        } else if (((dy <= -SWIPE_THRESHOLD_VELOCITY && previousDirection == 1) || y
                                - startingY <= -MOVE_THRESHOLD)
                                && previousDirection % 3 != 0) {
                            moved = true;
                            previousDirection = previousDirection * 3;
                            veryLastDirection = 3;
                            boardManager.moveUp();
                            // this is when swipe up

                        } else if (((dx >= SWIPE_THRESHOLD_VELOCITY && previousDirection == 1) || x
                                - startingX >= MOVE_THRESHOLD)
                                && previousDirection % 5 != 0) {
                            moved = true;
                            previousDirection = previousDirection * 5;
                            veryLastDirection = 5;
                            boardManager.moveRight();
                            // this is when swipe right

                        } else if (((dx <= -SWIPE_THRESHOLD_VELOCITY && previousDirection == 1) || x
                                - startingX <= -MOVE_THRESHOLD)
                                && previousDirection % 7 != 0) {
                            moved = true;
                            previousDirection = previousDirection * 7;
                            veryLastDirection = 7;
                            boardManager.moveLeft();
                            // this is when swipe left

                        }
                        if (moved) {
                            hasMoved = true;
                            startingX = x;
                            startingY = y;
                        }
                    }
                }
                previousX = x;
                previousY = y;
                return true;
        }
        return true;
    }

    private float pathMoved() {
        return (x - startingX) * (x - startingX) + (y - startingY)
                * (y - startingY);
    }

}