package ferjogames.coralsystemdeck.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.utils.Colors;

/**
 * Created by Fer on 18/08/2017.
 */

public class KeyboardButton extends Actor {
    private static final float WIDTH = 100;
    private static final float HEIGHT = 80;

    private Text text;

    public KeyboardButton(CoralSystemDeck game, int x, int y, String contents) {
        text = new Text(game, "Roboto-Bold35", (x + x + WIDTH) / 2, (y + y + HEIGHT) / 2, contents, Colors.RED);
        setBounds(x, y, WIDTH, HEIGHT);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        text.draw(batch, parentAlpha);
    }

    public String getButtonText() {
        return text.getText();
    }
}
