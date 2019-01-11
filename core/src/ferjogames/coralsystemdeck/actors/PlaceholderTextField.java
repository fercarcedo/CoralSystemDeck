package ferjogames.coralsystemdeck.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ferjogames.coralsystemdeck.utils.TextField;

/**
 * Created by Fer on 02/09/2017.
 */

public class PlaceholderTextField extends TextField {

    private String placeholder = "";
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
            placeholderShown = true;
        }
    }

    @Override
    public void setText(String str) {
        if (textEmpty(str)) {
        	String placeholder = this.placeholder != null ? this.placeholder : "";
            super.setText(placeholder);
            placeholderShown = true;
        } else {
            super.setText(str);
            placeholderShown = false;
        }
    }

    @Override
    public String getText() {
        return placeholderShown ? "" : super.getText();
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
    	super.draw(batch, parentAlpha);
    }

    private boolean textEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }
}
