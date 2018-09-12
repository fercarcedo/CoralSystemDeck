package ferjogames.coralsystemdeck.logic;

import ferjogames.coralsystemdeck.utils.I18N;

/**
 * Created by fer on 19/09/16.
 */
public class Division extends Operation {
	private static final long serialVersionUID = -5359446359506889364L;
	private static final Operation PRODUCT = new Product();

    @Override
    public void generateNumbers() {
        //Generamos una multiplicación. Está asegurado que los dos operandos no van a ser 0
        PRODUCT.setDifficulty(difficulty);

        if(difficulty == Difficulty.HARD) {
            do {
                PRODUCT.generateNumbers();
            } while (PRODUCT.getFirstNumber() == 0 || PRODUCT.getSecondNumber() == 0 ||
                    PRODUCT.getFirstNumber() > 17);
        }else {
            do {
                PRODUCT.generateNumbers();
            } while (PRODUCT.getFirstNumber() == 0 || PRODUCT.getSecondNumber() == 0);
        }

        firstNumber = PRODUCT.getFirstNumber();
        secondNumber = PRODUCT.getSecondNumber();
        result = PRODUCT.getResult();

        if(secondNumber == 0) {
            int temp = firstNumber;
            firstNumber = result;
            secondNumber = temp;

            result = firstNumber / secondNumber;
        }else {
            boolean bySecond = false;

            if(bySecond) {
                int temp = firstNumber;
                firstNumber = result;
                result = temp;
            }else{
                int tmpFirst = firstNumber;
                int tmpSecond = secondNumber;

                firstNumber = result;
                secondNumber = tmpFirst;
                result = tmpSecond;
            }
        }

        result = firstNumber / secondNumber;
    }

    @Override
    public char getOperationCharacter() {
        return ':';
    }

    @Override
    public String toString() {
        return I18N.get("division");
    }

    @Override
    public String getOperationCodeString() {
        return "division";
    }
}
