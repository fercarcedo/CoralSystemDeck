package ferjogames.coralsystemdeck.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.utils.Colors;

/**
 * Created by Fer on 10/08/2017.
 */

public class DifficultyButton extends Actor {

    private static final String OVAL_IMAGE_NAME = "oval";
    private static final int X_POS = 60;
    private static final int WIDTH = 360;
    private static final int HEIGHT = 115;
    private static final String FONT_NAME = "Roboto-Black30";
    private static final Color FONT_COLOR = Colors.BLACK;

    private Image image;
    private Label textLabel;

    public DifficultyButton(CoralSystemDeck game, Color color, String text, int yPos) {
        image = new Image(game, OVAL_IMAGE_NAME, X_POS, yPos, WIDTH, HEIGHT);
        image.setColor(color);
        Label.LabelStyle textLabelStyle = new Label.LabelStyle();
        textLabelStyle.font = game.getFont(FONT_NAME);
        textLabel = new Label(text, textLabelStyle);
        textLabel.setColor(FONT_COLOR);
        textLabel.setX(X_POS);
        textLabel.setY(yPos);
        textLabel.setWidth(WIDTH);
        textLabel.setHeight(HEIGHT);
        textLabel.setAlignment(Align.center);
        setBounds(X_POS, yPos, WIDTH, HEIGHT);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        image.draw(batch, parentAlpha);
        textLabel.draw(batch, parentAlpha);
    }
}
