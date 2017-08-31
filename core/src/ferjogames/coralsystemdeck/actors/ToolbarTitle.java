package ferjogames.coralsystemdeck.actors;

import com.badlogic.gdx.graphics.Color;

import ferjogames.coralsystemdeck.CoralSystemDeck;

/**
 * Created by Fer on 09/08/2017.
 */

public class ToolbarTitle extends Text {

    private static final String FONT_NAME = "Roboto-Regular25";
    private static final int X_POS = 100;
    private static final int Y_POS = 690;
    private static final Color TEXT_COLOR = Color.WHITE;

    public ToolbarTitle(CoralSystemDeck game, String title) {
        super(game, FONT_NAME, X_POS, Y_POS, title, TEXT_COLOR);
    }
}
