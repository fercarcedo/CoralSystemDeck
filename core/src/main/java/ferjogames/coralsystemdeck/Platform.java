package ferjogames.coralsystemdeck;

import com.badlogic.gdx.Input;

/**
 * Interface for handling platform-specific behavior
 *
 * Created by Fer on 29/09/2017.
 */
public interface Platform {
    void getTextInput (Input.TextInputListener listener, String title, String text, String hint);
}
