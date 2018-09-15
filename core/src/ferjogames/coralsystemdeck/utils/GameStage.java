package ferjogames.coralsystemdeck.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayDeque;
import java.util.Deque;

import ferjogames.coralsystemdeck.CoralSystemDeck;

/**
 * Created by Fer on 04/08/2017.
 */

public class GameStage extends Stage {

    private static final int SCROLL_DISTANCE = 30;

    private OnBackPressedListener backPressedListener;
    private OnSwipeListener swipeListener;

    private Vector2 downPos = new Vector2();
    private Vector2 upPos = new Vector2();
    private Vector3 tmpPos = new Vector3();
    private boolean scroll;

    public interface OnBackPressedListener {
        void onBackPressed();
    }

    public interface OnSwipeListener {
        void onLeftSwipe();
        void onRightSwipe();
    }

    public GameStage() {
        this(false);
    }

    public GameStage(boolean scroll) {
        super(new StretchViewport(CoralSystemDeck.WORLD_WIDTH, CoralSystemDeck.WORLD_HEIGHT));
        this.scroll = scroll;
    }

    public void setOnBackPressedListener(OnBackPressedListener backPressedListener) {
        this.backPressedListener = backPressedListener;
    }

    public void setOnSwipeListener(OnSwipeListener swipeListener) {
        this.swipeListener = swipeListener;
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.BACK) {
            if (backPressedListener != null)
                backPressedListener.onBackPressed();
            return true;
        }
        return super.keyDown(keyCode);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (scroll) {
            tmpPos.set(screenX, screenY, 0);
            getCamera().unproject(tmpPos);
            downPos.set(tmpPos.x, tmpPos.y);
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (scroll) {
            tmpPos.set(screenX, screenY, 0);
            getCamera().unproject(tmpPos);
            upPos.set(tmpPos.x, tmpPos.y);

            if (Math.abs(downPos.y - upPos.y) < SCROLL_DISTANCE
                && Math.abs(downPos.x - upPos.x) >= SCROLL_DISTANCE) {
                if (swipeListener != null) {
                    if (downPos.x - upPos.x > 0)
                        handleLeftSwipe();
                    else
                        handleRightSwipe();
                }
            }
        }
        return super.touchUp(screenX, screenY, pointer, button);
    }

    private void handleLeftSwipe() {
       if (swipeListener != null)
           swipeListener.onLeftSwipe();
    }

    private void handleRightSwipe() {
       if (swipeListener != null)
           swipeListener.onRightSwipe();
    }
}
