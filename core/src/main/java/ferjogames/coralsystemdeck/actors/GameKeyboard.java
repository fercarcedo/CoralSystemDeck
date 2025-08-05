package ferjogames.coralsystemdeck.actors;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.utils.Colors;

/**
 * Created by Fer on 15/08/2017.
 */

public class GameKeyboard extends Group {

    public interface KeyboardListener {
        void numberClicked(int number);
        void cancelClicked();
        void confirmClicked();
    }

    private Image background;
    private KeyboardButton[] numberButtons;
    private ImageButton cancelButton, confirmButton;
    private KeyboardListener listener;

    public GameKeyboard(CoralSystemDeck game) {
        Pixmap backgroundPixmap = new Pixmap((int) GameRocket.ROCKET_WIDTH, 246, Pixmap.Format.RGBA8888);
        backgroundPixmap.setColor(Colors.YELLOW);
        backgroundPixmap.fill();
        background = new Image(new Sprite(new Texture(backgroundPixmap)), 28, 5, GameRocket.ROCKET_WIDTH, 246);
        backgroundPixmap.dispose();

        numberButtons = new KeyboardButton[10];

        numberButtons[0] = new KeyboardButton(game, 338, 180, "0");
        numberButtons[1] = new KeyboardButton(game, 233, 180, "3");
        numberButtons[2] = new KeyboardButton(game, 128, 180, "2");
        numberButtons[3] = new KeyboardButton(game, 23, 180, "1");
        numberButtons[4] = new KeyboardButton(game, 23, 100, "4");
        numberButtons[5] = new KeyboardButton(game, 128, 100, "5");
        numberButtons[6] = new KeyboardButton(game, 233, 100, "6");
        numberButtons[7] = new KeyboardButton(game, 23, 20, "7");
        numberButtons[8] = new KeyboardButton(game, 128, 20, "8");
        numberButtons[9] = new KeyboardButton(game, 233, 20, "9");

        cancelButton = new ImageButton(game, "ic_clear_24px", 354, 85, 100, 80, Colors.RED);
        confirmButton = new ImageButton(game, "ic_done_24px", 354, 5, 100, 80, Colors.GREEN);

        cancelButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (listener != null)
                    listener.cancelClicked();
                return true;
            }
        });

        confirmButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (listener != null)
                    listener.confirmClicked();
                return true;
            }
        });

        for (KeyboardButton numberButton : numberButtons) {
            setKeyboardButtonListener(numberButton);
            addActor(numberButton);
        }

        addActor(cancelButton);
        addActor(confirmButton);
        setVisible(false);
        addListener(new InputListener() {
        	@Override
        	public boolean keyDown(InputEvent event, int keycode) {
        		switch (keycode) {
        		case Keys.ENTER: handleKeyboardConfirm(confirmButton); return true;
        		case Keys.BACKSPACE: handleKeyboardCancel(cancelButton); return true;
        		case Keys.NUM_0: case Keys.NUMPAD_0: handleKeyboardPress(0); return true;
        		case Keys.NUM_1: case Keys.NUMPAD_1: handleKeyboardPress(1); return true;
        		case Keys.NUM_2: case Keys.NUMPAD_2: handleKeyboardPress(2); return true;
        		case Keys.NUM_3: case Keys.NUMPAD_3: handleKeyboardPress(3); return true;
        		case Keys.NUM_4: case Keys.NUMPAD_4: handleKeyboardPress(4); return true;
        		case Keys.NUM_5: case Keys.NUMPAD_5: handleKeyboardPress(5); return true;
        		case Keys.NUM_6: case Keys.NUMPAD_6: handleKeyboardPress(6); return true;
        		case Keys.NUM_7: case Keys.NUMPAD_7: handleKeyboardPress(7); return true;
        		case Keys.NUM_8: case Keys.NUMPAD_8: handleKeyboardPress(8); return true;
        		case Keys.NUM_9: case Keys.NUMPAD_9: handleKeyboardPress(9); return true;
        		}
        		return super.keyDown(event, keycode);
        	}
        });
    }

    private void setKeyboardButtonListener(final KeyboardButton numberButton) {
        numberButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (listener != null)
                    listener.numberClicked(Integer.parseInt(numberButton.getButtonText()));
                return true;
            }
        });
    }

    public void setListener(KeyboardListener listener) {
        this.listener = listener;
    }

    private void handleKeyboardPress(int number) {
    	if (listener != null) {
			listener.numberClicked(number);
		}
    }

    private void handleKeyboardCancel(ImageButton cancelButton) {
    	if (listener != null) {
			listener.cancelClicked();
		}
    }

    private void handleKeyboardConfirm(ImageButton confirmButton) {
    	if (listener != null) {
			listener.confirmClicked();
		}
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isVisible()) {
            background.draw(batch, parentAlpha);
            for (KeyboardButton numberButton : numberButtons)
                numberButton.draw(batch, parentAlpha);
            cancelButton.draw(batch, parentAlpha);
            confirmButton.draw(batch, parentAlpha);
        }
    }
}
