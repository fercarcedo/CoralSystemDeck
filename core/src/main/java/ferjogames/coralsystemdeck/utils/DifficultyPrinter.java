package ferjogames.coralsystemdeck.utils;

import ferjogames.coralsystemdeck.logic.Operation;

/**
 * Created by Fer on 21/04/2017.
 */

public class DifficultyPrinter {
    public static String print(Operation.Difficulty difficulty) {
        return I18N.get(difficulty.name().toLowerCase());
    }
}
