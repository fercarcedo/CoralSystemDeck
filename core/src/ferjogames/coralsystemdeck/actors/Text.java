package ferjogames.coralsystemdeck.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import ferjogames.coralsystemdeck.CoralSystemDeck;

/**
 * Created by Fer on 31/07/2017.
 */

public class Text extends Actor {

    private BitmapFont font;
    private String text;

    public Text(CoralSystemDeck game, String fontName, float x, float y, String text, Color color) {
        this(game.getAssetManager().get(fontName + ".ttf", BitmapFont.class), x, y, text, color);
    }

    public Text(BitmapFont font, float x, float y, String text, Color color) {
        setFont(font);
        setX(x);
        setY(y);
        this.text = text;
        setColor(color);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setFontName(CoralSystemDeck game, String fontName) {
        setFont(game.getAssetManager().get(fontName + ".ttf", BitmapFont.class));
    }

    public void setFont(BitmapFont font) {
        this.font = font;
        this.font.setScale(CoralSystemDeck.WORLD_WIDTH / Gdx.graphics.getWidth());
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        this.font.setColor(getColor());
        font.draw(batch, text, getX(), getY());
    }
}
