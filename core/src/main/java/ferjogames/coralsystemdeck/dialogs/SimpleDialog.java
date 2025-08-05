package ferjogames.coralsystemdeck.dialogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.utils.Colors;
import ferjogames.coralsystemdeck.utils.GraphicUtils;

/**
 * Created by Fer on 29/08/2017.
 */

public class SimpleDialog extends Dialog {
    public SimpleDialog(CoralSystemDeck game, String content, String okButtonText) {
        super("", game.getUISkin(), "dialog");
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.getFont("font35");
        Label label = new Label(content, labelStyle);
        label.setWrap(true);
        label.setColor(Colors.BLACK);

        label.setFontScale((CoralSystemDeck.WORLD_WIDTH / Gdx.graphics.getWidth() * 0.8f));
        label.setAlignment(Align.center);
        getContentTable().padTop(50).add(label).width(350).row();
        getButtonTable().padTop(50).padBottom(10);

        TextButton okButton = GraphicUtils.createTextButtonAbsolute(okButtonText, 300, 300, 100, 40,
                game.getFont("font35"), Colors.WHITE, Colors.BLUE);
        button(okButton, true);
        key(Input.Keys.ENTER, true).key(Input.Keys.ESCAPE, true);

        setModal(true);
        setMovable(false);
        invalidateHierarchy();
        invalidate();
        layout();
    }

    @Override
    protected void result(Object object) {
    }

    @Override
    public float getPrefWidth() {
        return 350;
    }

    @Override
    public float getPrefHeight() {
        return 200;
    }
}
