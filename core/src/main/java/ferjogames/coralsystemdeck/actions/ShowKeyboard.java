package ferjogames.coralsystemdeck.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

import ferjogames.coralsystemdeck.actors.GameKeyboard;

/**
 * Created by Fer on 17/08/2017.
 */

public class ShowKeyboard extends Action {

    private GameKeyboard keyboard;
    private boolean stopped;

    public ShowKeyboard(GameKeyboard keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public boolean act(float delta) {
        if (!stopped) {
            if (keyboard != null)
                keyboard.setVisible(true);
        }
        return true;
    }

    public void stop() {
        stopped = true;
    }
}
