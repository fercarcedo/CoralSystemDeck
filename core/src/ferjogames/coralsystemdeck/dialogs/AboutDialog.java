package ferjogames.coralsystemdeck.dialogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.utils.Colors;
import ferjogames.coralsystemdeck.utils.GraphicUtils;
import ferjogames.coralsystemdeck.utils.I18N;

/**
 * Created by Fer on 29/08/2017.
 */

public class AboutDialog extends Dialog {
    public AboutDialog(CoralSystemDeck game) {
        super("", game.getUISkin(), "dialog");
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.getFont("font35");
        Label label = new Label("About", labelStyle);
        label.setWrap(true);
        label.setColor(Colors.BLACK);
        label.setFontScale((CoralSystemDeck.WORLD_WIDTH / Gdx.graphics.getWidth() * 0.8f));
        label.setAlignment(Align.topLeft);

        VerticalGroup verticalGroup = new VerticalGroup();
        verticalGroup.align(Align.top | Align.center);
        verticalGroup.addActor(new Image(game.getAtlas().findRegion("ic_launcher")));
        verticalGroup.addActor(GraphicUtils.createLabel(I18N.get("app_name"), game, "Roboto-Regular20Bold", Colors.BLACK));
        verticalGroup.addActor(GraphicUtils.createLabel(I18N.get("version"), game, "Roboto-Regular20Bold", Colors.BLACK));
        verticalGroup.space(15);
        Label ferjogamesLabel = GraphicUtils.createEmailLabel(I18N.get("about_email"), game, "Roboto-Regular20");
        verticalGroup.addActor(ferjogamesLabel);
        verticalGroup.space(15);
        verticalGroup.addActor(GraphicUtils.createLabel(I18N.get("about_based_on")+'\n'+I18N.get("juan_pedro")+'\n'+I18N.get("teacher_and_child_psychologist"), game, "Roboto-Regular20", Colors.BLACK));
        Label barajascalculoLabel = GraphicUtils.createWebsiteLabel(I18N.get("about_barajas_web"), game, "Roboto-Regular20");
        verticalGroup.addActor(barajascalculoLabel);
        verticalGroup.space(15);
        verticalGroup.addActor(GraphicUtils.createLabel(I18N.get("acknowledgements"), game, "Roboto-Regular20Bold", Colors.BLACK));
        verticalGroup.space(15);
        verticalGroup.addActor(GraphicUtils.createLabel(I18N.get("about_illustrations_sara"), game, "Roboto-Regular20", Colors.BLACK));
        Label smayolagoLabel = GraphicUtils.createEmailLabel(I18N.get("about_illustrations_email"), game, "Roboto-Regular20");
        verticalGroup.addActor(smayolagoLabel);
        verticalGroup.space(15);
        verticalGroup.addActor(GraphicUtils.createLabel(I18N.get("about_sounds_martin"), game, "Roboto-Regular20", Colors.BLACK));
        verticalGroup.space(15);
        verticalGroup.addActor(GraphicUtils.createLabel(I18N.get("thanks_sergio"), game, "Roboto-Regular20", Colors.BLACK));

        getContentTable().add(verticalGroup).padTop(20).fill().expand();

        getButtonTable().padTop(10).padBottom(0);

        TextButton okButton = GraphicUtils.createTextButtonAbsolute(I18N.get("close"), 280, 300, 110, 20,
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
        System.out.println("Elegido: " + object);
    }

    @Override
    public float getPrefWidth() {
        return 390;
    }

    @Override
    public float getPrefHeight() {
        return 650;
    }
}
