package ferjogames.coralsystemdeck.utils;

import com.badlogic.gdx.graphics.Color;

import java.util.List;

import ferjogames.coralsystemdeck.logic.Score;

/**
 * Created by fer on 22/01/16.
 */
public class Colors {
    private static final String[] colors = createColors();

    public static final Color BLACKBOARD_GREEN = Color.valueOf("#3B653D");
    public static final Color YELLOW = Color.valueOf("#FFFF00");
    public static final Color GREEN = Color.valueOf("#42ff76");
    public static final Color PURPLE = Color.valueOf("#f073ff");
    public static final Color ORANGE = Color.valueOf("#ff9425");
    public static final Color BLACK = Color.valueOf("#000000");
    public static final Color RED = Color.valueOf("#f44336");
    public static final Color WHITE = Color.valueOf("#FFFFFF");
    public static final Color WOOD = Color.valueOf("#663300");
    public static final Color BLUE = Color.valueOf("#2196F3");
    public static final Color CORAL = Color.valueOf("#FF7F50");

    private static String[] createColors() {
        String[] colors = new String[20];

        colors[0] = "#ffff00"; //Yellow
        colors[1] = "#4caf50"; //Green
        colors[2] = "#cddc39"; //Lime
        colors[3] = "#ff9800"; //Orange
        colors[4] = "#9e9e9e"; //Grey
        colors[5] = "#ffc107"; //Amber
        colors[6] = "#e57373"; //Red
        colors[7] = "#ce93d8"; //Purple
        colors[8] = "#42a5f5"; //Blue
        colors[9] = "#00bcd4"; //Cyan

        colors[10] = "#bcaaa4"; //Brown
        colors[11] = "#82b1ff"; //Light blue
        colors[12] = "#ff6e40"; //Light orange
        colors[13] = "#D2691E"; //Chocolate
        colors[14] = "#FF7F50"; //Coral
        colors[15] = "#FA8072"; //Salmon
        colors[16] = "#716565"; //Caldera
        colors[17] = "#3b7a57"; //Amazon
        colors[18] = "#7e5e60"; //Deep Taupe
        colors[19] = "#00a693"; //Persian green

        return colors;
    }

    public static String randomColor() {
        int colorIndex = (int) (Math.random() * colors.length);
        return colors[colorIndex];
    }

    public static String randomColor(List<Score> scores) {
        String color;

        do {
            color = randomColor();
        }while(repeatedColor(scores, color));

        return color;
    }

    private static boolean repeatedColor(List<Score> scores, String colorHex) {
        for(Score score : scores)
            if(score.getColorHex().equals(colorHex))
                return true;

        return false;
    }
}
