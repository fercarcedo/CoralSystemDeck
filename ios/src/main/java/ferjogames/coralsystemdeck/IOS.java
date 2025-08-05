package ferjogames.coralsystemdeck;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by Fer on 29/09/2017.
 */

public class IOS implements Platform {
    @Override
    public void getTextInput(Input.TextInputListener listener, String title, String text, String hint) {
        Gdx.input.getTextInput(listener, title, text, hint);
    }
}
