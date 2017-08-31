package ferjogames.coralsystemdeck.actions;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;

import ferjogames.coralsystemdeck.actors.Image;

/**
 * Created by Fer on 13/08/2017.
 */

public class ChangeImage extends Action {

    private Image image;
    private Sprite sprite;

    public ChangeImage(Image image, Sprite sprite) {
        this.image = image;
        this.sprite = sprite;
    }

    @Override
    public boolean act(float delta) {
        image.setSprite(sprite);
        return true;
    }
}
