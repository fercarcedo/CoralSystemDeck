package ferjogames.coralsystemdeck.utils;

import com.badlogic.gdx.audio.Music;

import ferjogames.coralsystemdeck.CoralSystemDeck;

/**
 * Created by Fer on 03/08/2017.
 */

public class SoundUtils {

    private static Music currentMusic;

    public static void playSound(CoralSystemDeck game, String name) {
        stopMusicIfPlaying();
        if (GamePreferences.isSoundEnabled()) {
            currentMusic = game.getAssetManager().get(name, Music.class);
            currentMusic.play();
        }
    }

    public static void stopMusicIfPlaying() {
        if (currentMusic != null && currentMusic.isPlaying())
            currentMusic.stop();
    }
}
