package ferjogames.coralsystemdeck.logic;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Fer on 17/08/2017.
 */

public class RandomOperation extends Operation {

    private static final List<Operation> OPERATIONS =
            Arrays.asList(new Addition(), new Substraction(), new Product(), new Division());

    private Operation operation;
    private int previousIndex = -1;

    public RandomOperation() {
        generateRandomOperation();
    }

    @Override
    public void generateNumbers() {
        generateRandomOperation();
        operation.generateNumbers();
    }

    @Override
    public char getOperationCharacter() {
        return operation.getOperationCharacter();
    }

    @Override
    public String toString() {
        return operation.toString();
    }

    @Override
    public String getOperationCodeString() {
        return "aleatorio";
    }

    private Operation generateRandomOperation() {
        int index = (int) (Math.random() * OPERATIONS.size());

        while (index == previousIndex)
            index = (int) (Math.random() * OPERATIONS.size());

        operation = OPERATIONS.get(index);
        operation.setDifficulty(getDifficulty());
        previousIndex = index;
        return operation;
    }
}
