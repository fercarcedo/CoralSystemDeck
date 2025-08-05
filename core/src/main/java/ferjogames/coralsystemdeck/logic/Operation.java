package ferjogames.coralsystemdeck.logic;

import java.io.Serializable;

import ferjogames.coralsystemdeck.utils.I18N;

/**
 * Created by fer on 19/09/16.
 */
public abstract class Operation implements Serializable {
	private static final long serialVersionUID = 2578309568793157701L;
	protected int firstNumber, secondNumber, result;
    protected Difficulty difficulty;

    public enum Difficulty {
        EASY, MEDIUM, HARD;

        @Override
        public String toString() {
            return I18N.get(name().toLowerCase()).toUpperCase();
        }
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public int getResult() {
        return result;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public abstract void generateNumbers();
    public abstract char getOperationCharacter();
    public abstract String toString();
    public abstract String getOperationCodeString();
}
