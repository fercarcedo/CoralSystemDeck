package ferjogames.coralsystemdeck.logic;

import ferjogames.coralsystemdeck.utils.I18N;
import ferjogames.coralsystemdeck.utils.Utils;

/**
 * Created by fer on 19/09/16.
 */
public class Product extends Operation {
	private static final long serialVersionUID = 3895999513193168568L;

	@Override
    public void generateNumbers() {
        switch (difficulty) {
            case EASY:
                //Tablas directas hasta 10 * 10
                firstNumber = Utils.randomBetween(1, 10);
                secondNumber = Utils.randomBetween(1, 10);
                break;
            case MEDIUM:
                //Multiplicamos decenas hasta * 10
                firstNumber = Utils.randomBetween(1, 9) * 10; //Decenas
                secondNumber = Utils.randomBetween(1, 10);
                break;
            case HARD:
                //Decenas y unidades hasta 99 * 9
                //Añadimos alguna de * 10, * 100 y * 1000
                do {
                    firstNumber = Utils.randomBetween(11, 99);
                } while (Utils.getNthDigit(firstNumber, 10, 1) == 0);

                int randomN = Utils.randomBetween(1, 4);

                if (randomN == 2)
                    secondNumber = Utils.random(new int[] { 10, 100, 1000 });
                else
                    secondNumber = Utils.randomBetween(1, 9);
                break;
        }

        result = firstNumber * secondNumber;
    }

    @Override
    public char getOperationCharacter() {
        return 'x';
    }

    @Override
    public String toString() {
        return I18N.get("multiplication");
    }

    @Override
    public String getOperationCodeString() {
        return "multiplicacion";
    }
}
