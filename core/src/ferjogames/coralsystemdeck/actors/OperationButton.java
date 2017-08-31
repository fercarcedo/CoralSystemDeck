package ferjogames.coralsystemdeck.actors;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import ferjogames.coralsystemdeck.CoralSystemDeck;

/**
 * Created by Fer on 09/08/2017.
 */

public class OperationButton extends Actor {

    private static final String DEFAULT_FONT_NAME = "Roboto-Bold70";
    private static final float RADIUS = 76.2f;
    private static final float BORDER_CIRCLE_RADIUS = 79.2f;

    private Circle circle;
    private Circle borderCircle;
    private Text textActor;

    private int fontX;
    private int fontY;
    private Color fontColor;
    private String text;
    private String fontName = DEFAULT_FONT_NAME;

    public OperationButton(CoralSystemDeck game, Camera camera, int x, int y, Color color, int fontX, int fontY, Color fontColor, String text) {
        circle = new Circle(camera, x, y, RADIUS, color);
        borderCircle = new Circle(camera, x, y, BORDER_CIRCLE_RADIUS, Color.BLACK);
        textActor = new Text(game, fontName, fontX, fontY, text, fontColor);
        this.fontX = fontX;
        this.fontY = fontY;
        this.fontColor = fontColor;
        this.text = text;
        setBounds(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
    }

    public OperationButton(CoralSystemDeck game, Camera camera, int x, int y, Color color, int fontX, int fontY, Color fontColor, String text, String fontName) {
        this(game, camera, x, y, color, fontX, fontY, fontColor, text);
        this.fontName = fontName;
        textActor = new Text(game, fontName, fontX, fontY, text, fontColor);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        borderCircle.draw(batch, parentAlpha);
        circle.draw(batch, parentAlpha);
        textActor.draw(batch, parentAlpha);
    }

    public void dispose() {
        borderCircle.dispose();
        circle.dispose();
    }
}
