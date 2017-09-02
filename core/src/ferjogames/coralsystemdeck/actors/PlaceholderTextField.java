package ferjogames.coralsystemdeck.actors;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * Created by Fer on 02/09/2017.
 */

public class PlaceholderTextField extends TextField {

    private String placeholder;
    private boolean placeholderShown;

    public PlaceholderTextField(String text, TextFieldStyle style) {
        super(text, style);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        if (textEmpty(getText())) {
            super.setText(placeholder);
        }
    }

    @Override
    public void setText(String str) {
        if (textEmpty(str)) {
            super.setText(placeholder);
            placeholderShown = true;
        } else {
            super.setText(str);
            placeholderShown = false;
        }
    }

    @Override
    public String getText() {
        return placeholderShown ? "" : text;
    }

    private boolean textEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }
}
