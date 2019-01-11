package ferjogames.coralsystemdeck.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.utils.Colors;
import ferjogames.coralsystemdeck.utils.GraphicUtils;
import ferjogames.coralsystemdeck.utils.MathUtils;
import ferjogames.coralsystemdeck.utils.Utils;

/**
 * Created by Fer on 20/08/2017.
 */
public class HighScoreItem extends Stack {

    private Label namePositionLabel;
    private Label pointsLabel;

    public HighScoreItem(CoralSystemDeck game, Color color, String namePosition, int points) {
        add(new Image(new Texture(generateBackground(color))));
        namePositionLabel = createLabel(game, namePosition);
        pointsLabel = createLabel(game, String.valueOf(points));
        VerticalGroup verticalGroup = new VerticalGroup();
        verticalGroup.addActor(new Image(game.getAtlas().findRegion("pushpin")));
        verticalGroup.setSpacing(15);
        verticalGroup.addActor(namePositionLabel);
        verticalGroup.addActor(pointsLabel);
        add(verticalGroup);
    }

    private static Pixmap generateBackground(Color color) {
        return GraphicUtils.getRoundedRectanglePixmap(
                MathUtils.roundToNextPowerOfTwo(Utils.toPixelsWidth(190)), 
                MathUtils.roundToNextPowerOfTwo(Utils.toPixelsHeight(150)), 
                Utils.toPixelsWidth(4), color
        );
    }

    private Label createLabel(CoralSystemDeck game, String text) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.getAssetManager().get("Roboto-Regular25.ttf");
        Label label = new Label(text, labelStyle);
        label.setAlignment(Align.center);
        label.setColor(Colors.BLACK);
        label.setWidth(getWidth());
        return label;
    }
}
