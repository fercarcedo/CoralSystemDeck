package ferjogames.coralsystemdeck.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.actors.Image;
import ferjogames.coralsystemdeck.dialogs.AboutDialog;
import ferjogames.coralsystemdeck.utils.GamePreferences;
import ferjogames.coralsystemdeck.utils.I18N;
import ferjogames.coralsystemdeck.utils.SoundUtils;

/**
 * Created by Fer on 01/08/2017.
 */

public class MainScreen extends AbstractScreen {

    private static final int BEAR_X_POS = 190;
    private static final int BEAR_Y_POS = 215;
    private static final int BEAR_WIDTH = 100;
    private static final int BEAR_HEIGHT = 90;

    private AboutDialog aboutDialog;

    public MainScreen(final CoralSystemDeck game) {
        super(game);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.getFont("font35");
        Label titleLabel = new Label(I18N.get("touchbear"), labelStyle);
        titleLabel.setY(650);
        titleLabel.setWidth(CoralSystemDeck.WORLD_WIDTH);
        titleLabel.setAlignment(Align.center);
        titleLabel.setColor(1, 1, 0, 1);
        getStage().addActor(titleLabel);

        showInfoImage();
        showSoundImage();

        Image nave = new Image(game, "naveespacial", 10, 0, 460, 630);
        nave.setBounds(10, 0, 460, 630);
        nave.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (x >= BEAR_X_POS && x <= (BEAR_X_POS + BEAR_WIDTH)
                        && y >= BEAR_Y_POS && y <= (BEAR_Y_POS + BEAR_HEIGHT)) {
                    game.setScreen(new NameScreen(game));
                }
                return true;
            }
        });
        getStage().addActor(nave);
        SoundUtils.playSound(game, I18N.get("ifyouwanttoplaysound"));
    }

    private void showInfoImage() {
        Image infoImage = new Image(game, "ic_info", 420, 655, 40, 40);
        infoImage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (aboutDialog == null) {
                    aboutDialog = new AboutDialog(game);
                }
                aboutDialog.show(stage);
                return true;
            }
        });
        getStage().addActor(infoImage);
    }

    private void showSoundImage() {
        String imageName = GamePreferences.isSoundEnabled() ? "speaker" : "speakermuted";
        final Image soundImage = new Image(game, imageName, 20, 655, 40, 40);
        soundImage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                boolean newSoundEnabled = !GamePreferences.isSoundEnabled();
                String name = newSoundEnabled ? "speaker" : "speakermuted";
                GamePreferences.putSoundEnabled(newSoundEnabled);
                soundImage.setSprite(new Sprite(game.getAtlas().findRegion(name)));
                return true;
            }
        });
        getStage().addActor(soundImage);
    }

    @Override
    public void onBackPressed() {
        if (aboutDialog != null && aboutDialog.getStage() != null) {
            aboutDialog.hide();
        } else {
            Gdx.app.exit();
        }
    }
}
