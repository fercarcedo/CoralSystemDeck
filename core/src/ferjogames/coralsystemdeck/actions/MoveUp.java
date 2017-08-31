package ferjogames.coralsystemdeck.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

/**
 * Created by Fer on 16/08/2017.
 */

public class MoveUp extends Action {

    private float toY;
    private boolean stopped;

    public MoveUp(float toY) {
        this.toY = toY;
    }

    @Override
    public boolean act(float delta) {
        if (!stopped) {
            if (getActor().getY() < toY) {
                for (int i = 0; i < 4; i++) {
                    getActor().setY(getActor().getY() + 1);
                }
                return false;
            }
        }
        return true;
    }

    public void stop() {
        stopped = true;
    }
}
