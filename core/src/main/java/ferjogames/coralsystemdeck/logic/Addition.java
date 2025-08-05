package ferjogames.coralsystemdeck.logic;

import ferjogames.coralsystemdeck.utils.I18N;
import ferjogames.coralsystemdeck.utils.Utils;

/**
 * Created by fer on 19/09/16.
 */
public class Addition extends Operation {
	private static final long serialVersionUID = -8129587896271101822L;

	@Override
    public void generateNumbers() {
        switch (difficulty) {
            case EASY:
                //Hasta 10 + 10
                firstNumber = Utils.randomBetween(1, 10);
                secondNumber = Utils.randomBetween(1, 10);
                break;
            case MEDIUM:
                //Cambian las decenas pero solo se suman unidades sin llevadas
                //45+3 22+7 35+4
                do {
                    firstNumber = Utils.randomBetween(11, 99);
                    secondNumber = Utils.randomBetween(1, 9);
                } while (Utils.hasCarryAddition(firstNumber, secondNumber));
                break;
            case HARD:
                //Con llevadas hasta 99
                //Centenas completas hasta 1000:
                //400+400 300+500
                int randomN = Utils.randomBetween(1, 3);

                if (randomN != 1) {
                    do {
                        firstNumber = Utils.randomBetween(11, 99);
                        secondNumber = Utils.randomBetween(11, 99);
                    } while (!Utils.hasCarryAddition(firstNumber, secondNumber));
                } else {
                    firstNumber = Utils.randomBetween(1, 9) * 100;
                    secondNumber = Utils.randomBetween(1, 9) * 100;
                }
                break;
        }

        result = firstNumber + secondNumber;
    }

    @Override
    public char getOperationCharacter() {
        return '+';
    }

    @Override
    public String toString() {
        return I18N.get("addition");
    }

    @Override
    public String getOperationCodeString() {
        return "suma";
    }
}
