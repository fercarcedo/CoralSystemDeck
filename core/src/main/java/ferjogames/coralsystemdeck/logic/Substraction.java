package ferjogames.coralsystemdeck.logic;

import ferjogames.coralsystemdeck.utils.I18N;
import ferjogames.coralsystemdeck.utils.Utils;

/**
 * Created by fer on 19/09/16.
 */
public class Substraction extends Operation {
	private static final long serialVersionUID = 3562079052053825245L;

	@Override
    public void generateNumbers() {
        switch (difficulty) {
            case EASY:
                //Desde el 10 para atrás
                //10-10, 4-2, ...
                do {
                    firstNumber = Utils.randomBetween(1, 10);
                    secondNumber = Utils.randomBetween(1, 10);
                } while (firstNumber < secondNumber);
                break;
            case MEDIUM:
                //Desde 99 para atrás pero sin llevadas, restas de decenas
                //completas o unidades
                //40-10, 54-3, 98-6, 70-40, ...
                do {
                    firstNumber = Utils.randomBetween(11, 99);
                    secondNumber = Utils.random(
                            new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90});
                } while (firstNumber < secondNumber || Utils.hasCarrySubstraction(firstNumber, secondNumber));
                break;
            case HARD:
                //Con llevadas hasta 99
                do {
                    firstNumber = Utils.randomBetween(10, 99);
                    secondNumber = Utils.randomBetween(1, 99);
                } while (firstNumber < secondNumber || !Utils.hasCarrySubstraction(firstNumber, secondNumber));
                break;
        }

        result = firstNumber - secondNumber;
    }

    @Override
    public char getOperationCharacter() {
        return '-';
    }

    @Override
    public String toString() {
        return I18N.get("substraction");
    }

    @Override
    public String getOperationCodeString() {
        return "resta";
    }
}
