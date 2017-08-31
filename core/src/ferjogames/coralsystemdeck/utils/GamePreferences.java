package ferjogames.coralsystemdeck.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;
import java.util.List;

import ferjogames.coralsystemdeck.logic.Operation;
import ferjogames.coralsystemdeck.logic.Score;

/**
 * Created by Fer on 06/08/2017.
 */

public class GamePreferences {

    private static final String PREFS_NAME = "coralsystemdeckprefs";
    private static final String LAST_PLAYER_NAME_KEY = "last_player_name";
    private static final String SOUND_ENABLED_KEY = "sound_enabled";

    public static String getLastPlayerName() {
        Preferences prefs = getPreferences();
        return prefs.getString(LAST_PLAYER_NAME_KEY, "");
    }

    public static void putLastPlayerName(String lastPlayerName) {
        Preferences prefs = getPreferences();
        prefs.putString(LAST_PLAYER_NAME_KEY, lastPlayerName);
        prefs.flush();
    }

    public static boolean isSoundEnabled() {
        Preferences prefs = getPreferences();
        return prefs.getBoolean(SOUND_ENABLED_KEY, true);
    }

    public static void putSoundEnabled(boolean soundEnabled) {
        SoundUtils.stopMusicIfPlaying();
        Preferences prefs = getPreferences();
        prefs.putBoolean(SOUND_ENABLED_KEY, soundEnabled);
        prefs.flush();
    }

    public static List<Score> getHighScores(Operation operation) {
        String highScoresStr = getHighScoresStr(operation);
        return parseHighScores(highScoresStr);
    }

    private static String getHighScoresStr(Operation operation) {
        //De la forma: Fer:6:colorHex,Luis:7:colorHex,Pedro:8:colorHex
        Preferences prefs = getPreferences();
        String prefName = Utils.getHighScoreName(operation);
        return prefs.getString(prefName, "");
    }

    private static List<Score> parseHighScores(String highScoresStr) {
        String[] highScoresParts = highScoresStr.split(",");
        List<Score> highScores = new ArrayList<>();

        for (String highScorePart : highScoresParts) {
            String[] parts = highScorePart.split(":");
            if (parts.length == 3)
                highScores.add(new Score(parts[0], Integer.parseInt(parts[1]), parts[2]));
        }

        return highScores;
    }

    public static void putHighScores(Operation operation, List<Score> highScores) {
        String resultStr = encodeHighScores(highScores);
        Preferences prefs = getPreferences();
        prefs.putString(Utils.getHighScoreName(operation), resultStr);
        prefs.flush();
    }

    private static String encodeHighScores(List<Score> highScores) {
        String resultStr = "";

        for (int i = 0; (i < 10) && (i < highScores.size()); i++) {
            if (i != highScores.size() - 1)
                resultStr += highScores.get(i).toString() + ",";
            else
                resultStr += highScores.get(i).toString();
        }

        return resultStr;
    }

    private static Preferences getPreferences() {
        return Gdx.app.getPreferences(PREFS_NAME);
    }
}
