package ferjogames.coralsystemdeck.logic;

/**
 * Created by fer on 28/11/15.
 */
public class Score implements Comparable<Score> {
    private String studentName;
    private int points;
    private String colorHex;

    public Score(String studentName, int points, String colorHex) {
        this.studentName = studentName;
        this.points = points;
        this.colorHex = colorHex;
    }

    @Override
    public String toString() {
        return studentName + ":" + points + ":" + colorHex;
    }

    @Override
    public int compareTo(Score score) {
        if (points == score.points)
            return 0;
        else if (points > score.points)
            return -1;
        else
            return 1;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getPoints() {
        return points;
    }

    public String getColorHex() {
        return colorHex;
    }
}