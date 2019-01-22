package ferjogames.coralsystemdeck.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import ferjogames.coralsystemdeck.CoralSystemDeck;

/**
 * Created by Fer on 31/07/2017.
 */

public class Image extends Actor {

    private Sprite sprite;

    public Image(Sprite sprite, float x, float y) {
        this.sprite = sprite;
        setX(x);
        setY(y);
    }

    public Image(Sprite sprite, float x, float y, float width, float height) {
        this(sprite, x, y);
        setWidth(width);
        setHeight(height);
    }

    public Image(CoralSystemDeck game, String name, float x, float y) {
        this(new Sprite(game.getAtlas().findRegion(name)), x, y);
    }

    public Image(CoralSystemDeck game, String name, float x, float y, float width, float height) {
        this(game, name, x, y);
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        if (getWidth() > 0 && getHeight() > 0) {
            batch.draw(sprite, getX(), getY(), getWidth(), getHeight());
        } else {
            batch.draw(sprite, getX(), getY());
        }
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
