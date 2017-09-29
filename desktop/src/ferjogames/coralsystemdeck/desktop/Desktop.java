package ferjogames.coralsystemdeck.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import ferjogames.coralsystemdeck.Platform;

/**
 * Created by Fer on 29/09/2017.
 */
public class Desktop implements Platform {
    @Override
    public void getTextInput(Input.TextInputListener listener, String title, String text, String hint) {
        Gdx.input.getTextInput(listener, title, text, hint);
    }
}
