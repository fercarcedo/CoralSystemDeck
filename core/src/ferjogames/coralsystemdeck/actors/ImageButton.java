package ferjogames.coralsystemdeck.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.utils.MathUtils;

/**
 * Created by Fer on 17/08/2017.
 */

public class ImageButton extends Actor {

    private Image backgroundImage;
    private Image image;

    public ImageButton(CoralSystemDeck game, String imageName, int x, int y, int width, int height, Color backgroundColor) {
        Pixmap pixmap = new Pixmap(MathUtils.roundToNextPowerOfTwo(width), MathUtils.roundToNextPowerOfTwo(height), Pixmap.Format.RGBA8888);
        pixmap.setColor(backgroundColor);
        pixmap.fill();
        backgroundImage = new Image(new Sprite(new Texture(pixmap)), x, y, width, height);
        image = new Image(game, imageName, x + 20, y + 10, height - 20, height - 20);
        setBounds(x, y, width, height);
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        backgroundImage.draw(batch, parentAlpha);
        image.draw(batch, parentAlpha);
    }
}
