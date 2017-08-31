package ferjogames.coralsystemdeck.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

/**
 * Created by Fer on 13/08/2017.
 */

public class FlipIn extends Action {

    private float x;
    private float width;
    private float duration;
    private float done = 0;

    public FlipIn(float x, float width, float duration) {
        this.x = x;
        this.width = width;
        this.duration = duration;
    }

    @Override
    public boolean act(float delta) {
        done += delta;
        if (done >= duration) {
            actor.setX(x);
            actor.setWidth(width);
            return true;
        }
        float tmpWidth = width * (done / duration);
        actor.setX(x + ((width / 2) - (tmpWidth / 2)));
        actor.setWidth(tmpWidth);
        return false;
    }
}
