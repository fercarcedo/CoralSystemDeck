package ferjogames.coralsystemdeck.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.actors.BackArrow;
import ferjogames.coralsystemdeck.actors.Image;
import ferjogames.coralsystemdeck.actors.Text;
import ferjogames.coralsystemdeck.dialogs.SimpleDialog;
import ferjogames.coralsystemdeck.logic.Addition;
import ferjogames.coralsystemdeck.logic.Operation;
import ferjogames.coralsystemdeck.utils.GamePreferences;
import ferjogames.coralsystemdeck.utils.GraphicUtils;
import ferjogames.coralsystemdeck.utils.I18N;
import ferjogames.coralsystemdeck.utils.SoundUtils;
import ferjogames.coralsystemdeck.utils.Utils;

/**
 * Created by Fer on 01/08/2017.
 */

public class NameScreen extends AbstractScreen {

    private boolean nameEntered;

    public NameScreen(final CoralSystemDeck game) {
        super(game);
        getStage().addActor(new Text(game, "font35", 110, 690, I18N.get("whats_your_name"), Color.YELLOW));
        final Image nave = new Image(game, "naveespacial", 125, 260, 230, 370);
        getStage().addActor(nave);

        getStage().addActor(new BackArrow(game, this));

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        Pixmap pixmap = GraphicUtils.getBlackboardPixmap(Utils.toPixelsWidth(316),
                                            Utils.toPixelsHeight(165),
                                            Utils.toPixelsWidth(7));

        style.background = new SpriteDrawable(new Sprite(new Texture(pixmap)));
        pixmap.dispose();
        style.fontColor = Color.WHITE;
        style.font = game.getAssetManager().get("font40.ttf");
        style.font.getData().setScale(CoralSystemDeck.WORLD_WIDTH / Gdx.graphics.getWidth());

        final String lastPlayerName = GamePreferences.getLastPlayerName();
        final String namePlaceholder = I18N.get("name");
        final TextField textField = new TextField(lastPlayerName.trim().isEmpty() ? namePlaceholder : lastPlayerName, style);
        textField.setMaxLength(9);
        textField.setBounds(85, 95, 316, 165);
        textField.setAlignment(Align.center);
        textField.setOnscreenKeyboard(new TextField.OnscreenKeyboard() {
            @Override
            public void show(boolean visible) {
                Gdx.input.getTextInput(new Input.TextInputListener() {
                    @Override
                    public void input(String text) {
                        nameEntered = true;
                        textField.setText(text.toUpperCase());
                        GamePreferences.putLastPlayerName(textField.getText());
                        if (textField.getText().isEmpty())
                            textField.setText(namePlaceholder);
                    }

                    @Override
                    public void canceled() {

                    }
                }, I18N.get("whats_your_name"), textField.getText().equals(namePlaceholder) ? "" : textField.getText(), textField.getText().trim().isEmpty() ? namePlaceholder : "");
            }
        });
        getStage().addActor(textField);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = game.getAssetManager().get("font30.ttf");
        textButtonStyle.font.getData().setScale(CoralSystemDeck.WORLD_WIDTH / Gdx.graphics.getWidth());

        textButtonStyle.fontColor = Color.WHITE;
        pixmap = new Pixmap(Utils.toPixelsWidth(240), Utils.toPixelsHeight(70), Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        textButtonStyle.up = new SpriteDrawable(new Sprite(new Texture(pixmap)));
        pixmap.dispose();
        textButtonStyle.down = textButtonStyle.up;
        TextButton textButton = new TextButton(I18N.get("letsplay").toUpperCase(), textButtonStyle);
        textButton.setBounds(123, 10, 240, 70);
        textButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if ((lastPlayerName.trim().isEmpty() && !nameEntered) || textField.getText().trim().isEmpty()) {
                    new SimpleDialog(game, I18N.get("enter_name_descr"), I18N.get("enter_name_btn")).show(stage);
                } else {
                    game.setScreen(new OperationsScreen(game, textField.getText()));
                }
                return true;
            }
        });
        getStage().addActor(textButton);
        SoundUtils.playSound(game, I18N.get("whatsyournamesound"));
    }

    @Override
    public void onBackPressed() {
        game.setScreen(new MainScreen(game));
    }
}
