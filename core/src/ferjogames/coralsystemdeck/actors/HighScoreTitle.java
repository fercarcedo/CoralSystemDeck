package ferjogames.coralsystemdeck.actors;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.utils.Colors;
import ferjogames.coralsystemdeck.utils.GraphicUtils;
import ferjogames.coralsystemdeck.utils.MathUtils;
import ferjogames.coralsystemdeck.utils.Utils;

/**
 * Created by Fer on 19/08/2017.
 */

public class HighScoreTitle extends Group {
    private Image background;
    private Image pushpinImage;
    private Label titleLabel;

    public HighScoreTitle(CoralSystemDeck game, String title, float x, float y, float width, float height) {
        Pixmap backgroundPixmap = GraphicUtils.getRoundedRectanglePixmap(
                MathUtils.roundToNextPowerOfTwo(Utils.toPixelsWidth((int) width)), 
                MathUtils.roundToNextPowerOfTwo(Utils.toPixelsHeight((int) height)), 
                Utils.toPixelsWidth(4), Colors.YELLOW
        );
        background = new Image(new Sprite(new Texture(backgroundPixmap)), x, y, width, height);
        backgroundPixmap.dispose();
        pushpinImage = new Image(game, "pushpin", ((x + x + width) / 2) - 25, y + height - 50, 55, 41.8f);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.getAssetManager().get("Roboto-Regular25.ttf");
        titleLabel = new Label(title, labelStyle);
        titleLabel.setColor(Colors.BLACK);
        titleLabel.setX(x);
        titleLabel.setY(y + 12);
        titleLabel.setWidth(width);
        titleLabel.setAlignment(Align.center);
        addActor(background);
        addActor(pushpinImage);
        addActor(titleLabel);
    }
}
