package ferjogames.coralsystemdeck.utils;

import com.badlogic.gdx.Gdx;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.logic.Operation;
import ferjogames.coralsystemdeck.logic.Score;

/**
 * Utils class
 * Created by fer on 14/11/15.
 */
public final class Utils {

    public static boolean isNumber(String text) {
        try {
            Integer.parseInt(text);
            return true;
        }catch(NumberFormatException e) {
            return false;
        }
    }

    public static int randomBetween(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static int random(int[] numbers) {
        return numbers[randomBetween(0, numbers.length - 1)];
    }

    public static boolean hasCarrySubstraction(int firstNumber, int secondNumber) {
        return getNthDigit(secondNumber, 10, 1) > getNthDigit(firstNumber, 10, 1);
    }

    public static boolean hasCarryAddition(int firstNumber, int secondNumber) {
        return getNthDigit(firstNumber, 10, 1) + getNthDigit(secondNumber, 10, 1) >= 10;
    }

    /**
     * Returns the digit at position n, starting from the right and beginning with 1
     *
     * @param number number from which to extract digit
     * @param base number base (2, 10, 16)
     * @param n digit position starting from the right and beginning with 1
     * @return digit from number at position n
     */
    public static int getNthDigit(int number, int base, int n) {
        return (int) ((number / Math.pow(base, n - 1)) % base);
    }

    public static  String getOperationCodeString(Operation operation) {
        if(operation == null)
            return "aleatorio";

        return operation.getOperationCodeString();
    }

    public static String getHighScoreName(Operation operation) {
        return Utils.getOperationCodeString(operation) + operation.getDifficulty().name();
    }

    public static int fontSizeToPixels(int fontSize) {
        return toPixelsWidth(fontSize);
    }

    public static int toPixelsWidth(int worldUnits) {
        return (int) Math.ceil((worldUnits * Gdx.graphics.getWidth()) / CoralSystemDeck.WORLD_WIDTH);
    }

    public static int toPixelsHeight(int worldUnits) {
        return (int) Math.ceil((worldUnits * Gdx.graphics.getHeight()) / CoralSystemDeck.WORLD_HEIGHT);
    }

    public static int toInt(String text) {
        int result;
        if (text == null || text.trim().isEmpty()) {
            result = Integer.MAX_VALUE;
        } else {
            result = Integer.parseInt(text);
        }

        return result;
    }

    public static boolean inHighScores(List<Score> highScores, String studentName, int points) {
        for (Score highScore : highScores) {
            if (highScore.getStudentName().equals(studentName) &&
                    highScore.getPoints() == points) {
                return true;
            }
        }
        return false;
    }

    public static boolean newRecord(List<Score> highScores, String studentName, int points) {
        if (CoralSystemDeck.SCREENSHOT_MODE)
            return true;

        if (uniqueHighScore(highScores, studentName, points)) {
            if (highScores.isEmpty())
                return true;

            if(!highScores.isEmpty() && points > highScores.get(0).getPoints())
                return true;
        }
        return false;
    }

    public static void saveResult(Operation operation, List<Score> highScores, String studentName, int points) {
        if (uniqueHighScore(highScores, studentName, points)) {
            highScores.add(new Score(studentName, points, Colors.randomColor(highScores)));
            Collections.sort(highScores);
            GamePreferences.putHighScores(operation, highScores);
        }
    }

    private static boolean uniqueHighScore(List<Score> highScores, String studentName, int points) {
        return points > 0 && !Utils.inHighScores(highScores, studentName, points);
    }

    public static boolean isSpanish() {
        return Locale.getDefault().getLanguage().startsWith("es");
    }
}
