package ferjogames.coralsystemdeck.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.I18NBundle;

/**
 * Created by Fer on 25/08/2017.
 */

public class I18N {

	private static final String BUNDLE_PATH = "i18n/strings";
    private static I18NBundle bundle = createBundle();

    public static String get(String key) {
        return bundle.get(key);
    }

    public static String get(String key, Object... args) {
        return bundle.format(key, args);
    }

    private static I18NBundle createBundle() {
        return I18NBundle.createBundle(Gdx.files.internal(BUNDLE_PATH));
    }

    public static void recreateBundle() {
        bundle = createBundle();
    }
}
