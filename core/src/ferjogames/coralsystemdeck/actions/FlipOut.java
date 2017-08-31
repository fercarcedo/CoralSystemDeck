package ferjogames.coralsystemdeck.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

/**
 * Created by Fer on 13/08/2017.
 */

public class FlipOut extends Action {

    private float x;
    private float width;
    private float duration;
    private float left;

    public FlipOut(float x, float width, float duration) {
        this.x = x;
        this.width = width;
        this.duration = duration;
        this.left = duration;
    }

    @Override
    public boolean act(float delta) {
        left -= delta;
        if (left <= 0) {
            actor.setX(x);
            actor.setWidth(1);
            return true;
        }
        float tmpWidth = width * (left / duration);
        actor.setX(x + ((width / 2) - (tmpWidth / 2)));
        actor.setWidth(tmpWidth);
        return false;
    }
}
