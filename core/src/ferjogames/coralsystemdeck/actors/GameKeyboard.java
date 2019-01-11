package ferjogames.coralsystemdeck.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.utils.Colors;
import ferjogames.coralsystemdeck.utils.MathUtils;

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
        Pixmap backgroundPixmap = new Pixmap(MathUtils.roundToNextPowerOfTwo((int) GameRocket.ROCKET_WIDTH), 
        		MathUtils.roundToNextPowerOfTwo(246), 
        		Pixmap.Format.RGBA8888);
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

    @Override
    public void act(float delta) {
    	super.act(delta);
    	for (KeyboardButton button : numberButtons) {
    		handleKeyboardPress(button);
    	}
    	handleKeyboardCancel(cancelButton);
    	handleKeyboardConfirm(confirmButton);
    }
 
    private void handleKeyboardPress(KeyboardButton numberButton) {
    	int[] keys;
    	int number = Integer.parseInt(numberButton.getButtonText());
    	switch (number) {
    	case 0: keys = new int[] {Keys.NUM_0, Keys.NUMPAD_0}; break;
    	case 1: keys = new int[] {Keys.NUM_1, Keys.NUMPAD_1}; break;
    	case 2: keys = new int[] {Keys.NUM_2, Keys.NUMPAD_2}; break;
    	case 3: keys = new int[] {Keys.NUM_3, Keys.NUMPAD_3}; break;
    	case 4: keys = new int[] {Keys.NUM_4, Keys.NUMPAD_4}; break;
    	case 5: keys = new int[] {Keys.NUM_5, Keys.NUMPAD_5}; break;
    	case 6: keys = new int[] {Keys.NUM_6, Keys.NUMPAD_6}; break;
    	case 7: keys = new int[] {Keys.NUM_7, Keys.NUMPAD_7}; break;
    	case 8: keys = new int[] {Keys.NUM_8, Keys.NUMPAD_8}; break;
    	default: keys = new int[] {Keys.NUM_9, Keys.NUMPAD_9}; break;
    	}
    	for (int key : keys) {
    		if (Gdx.input.isKeyPressed(key)) {
    			if (listener != null) {
    				listener.numberClicked(number);
    			}
    		}
    	}
    }
    
    private void handleKeyboardCancel(ImageButton cancelButton) {
    	if (Gdx.input.isKeyPressed(Keys.BACKSPACE)) {
    		if (listener != null) {
    			listener.cancelClicked();
    		}
    	}
    }
    
    private void handleKeyboardConfirm(ImageButton confirmButton) {
    	if (Gdx.input.isKeyPressed(Keys.ENTER)) {
    		if (listener != null) {
    			listener.confirmClicked();
    		}
    	}
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        if (isVisible()) {
            background.draw(batch, parentAlpha);
            for (KeyboardButton numberButton : numberButtons)
                numberButton.draw(batch, parentAlpha);
            cancelButton.draw(batch, parentAlpha);
            confirmButton.draw(batch, parentAlpha);
        }
    }
}
