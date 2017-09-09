package ferjogames.coralsystemdeck.logic;

/**
 * Created by Fer on 10/09/2017.
 */

public class MockOperation extends Addition {
    public MockOperation() {
        setDifficulty(Difficulty.EASY);
    }

    @Override
    public int getFirstNumber() {
        return 2;
    }

    @Override
    public int getSecondNumber() {
        return 2;
    }

    @Override
    public int getResult() {
        return 4;
    }
}
