package ferjogames.coralsystemdeck.utils;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import com.badlogic.gdx.Gdx;

/**
 * Created by Fer on 25/08/2017.
 */

public class I18N {

	private static final String BUNDLE_PATH = "i18n/strings";
    private static ResourceBundle bundle = createBundle();

    public static String get(String key) {
        return bundle.getString(key);
    }

    public static String get(String key, Object... args) {
        String pattern = get(key);
        return MessageFormat.format(pattern, args);
    }

    private static ResourceBundle createBundle() {
    	return ResourceBundle.getBundle(BUNDLE_PATH);
    }

    public static void recreateBundle() {
        bundle = createBundle();
    }
}
