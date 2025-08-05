package ferjogames.coralsystemdeck.actors;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.utils.Colors;

/**
 * Created by Fer on 17/08/2017.
 */

public class CountdownCircle extends Actor {
    private static final float Y_POS = 610;
    private static final float RADIUS = 35;
    private static final Color BACKGROUND_COLOR = Colors.YELLOW;
    private static final Color TEXT_COLOR = Colors.RED;

    private Circle circle;
    private Text countdownText;

    public CountdownCircle(CoralSystemDeck game, Camera camera, float xPos) {
        circle = new Circle(camera, xPos, Y_POS, RADIUS, BACKGROUND_COLOR);
        setBounds(xPos, Y_POS, RADIUS, RADIUS);
        countdownText = new Text(game, "Roboto-Regular54", getX() - 30, getY() + 20, "", TEXT_COLOR);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        circle.draw(batch, parentAlpha);
        countdownText.draw(batch, parentAlpha);
    }

    public void setValue(String value) {
        countdownText.setText(String.valueOf(value));
    }
}
