package ferjogames.coralsystemdeck.actors;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.actions.ChangeImage;
import ferjogames.coralsystemdeck.actions.FlipIn;
import ferjogames.coralsystemdeck.actions.FlipOut;
import ferjogames.coralsystemdeck.actions.MoveUp;
import ferjogames.coralsystemdeck.actions.ShowKeyboard;
import ferjogames.coralsystemdeck.logic.Operation;
import ferjogames.coralsystemdeck.utils.Colors;
import ferjogames.coralsystemdeck.utils.I18N;
import ferjogames.coralsystemdeck.utils.SoundUtils;
import ferjogames.coralsystemdeck.utils.Utils;

/**
 * Created by Fer on 12/08/2017.
 */
public class GameRocket extends Actor {

    private enum Side { BACK, FRONT };

    private static final int MAX_ANSWER_LENGTH = 5;
    private static final String ROCKET_FRONT_IMAGE = "suma1a";
    private static final String ROCKET_BACK_IMAGE = "suma2a";
    private static final float ROCKET_X_POS = 26;
    private static final float ROCKET_Y_POS = 0;
    public static final float ROCKET_WIDTH = (645 / 501f) * 331;
    private static final float ROCKET_HEIGHT = 645;
    private static final float ROTATE_DURATION = 0.250f;

    private Image rocketImage;
    private Text operationText;
    private Text answerText;
    private Label correctText;
    private float textYOffset;
    private float answerTextYOffset;
    private CoralSystemDeck game;
    private Operation operation;
    private Side side = Side.FRONT;
    private GameKeyboard keyboard;
    private MoveUp moveUp;
    private ShowKeyboard showKeyboard;
    private int correctAnswers;
    private int incorrectAnswers;

    public GameRocket(CoralSystemDeck game) {
        this.game = game;
        rocketImage = new Image(game, ROCKET_FRONT_IMAGE, ROCKET_X_POS, ROCKET_Y_POS, ROCKET_WIDTH, ROCKET_HEIGHT);
        operationText = new Text(game, "Roboto-Regular65", 0, 0, "", Colors.WHITE);
        answerText = new Text(game, "Roboto-Regular65", 0, 0, "", Colors.WHITE);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.getFont("Roboto-Regular43");
        correctText = new Label("", labelStyle);
        correctText.setColor(Colors.GREEN);
        correctText.setY(getY() + 150);
        correctText.setX(ROCKET_X_POS);
        correctText.setWidth(ROCKET_WIDTH);
        correctText.setAlignment(Align.center);
        setBounds(ROCKET_X_POS, ROCKET_Y_POS, ROCKET_WIDTH, ROCKET_HEIGHT);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        rocketImage.draw(batch, parentAlpha);
        operationText.draw(batch, parentAlpha);

        if (side == Side.FRONT)
            answerText.draw(batch, parentAlpha);
        else
            correctText.draw(batch, parentAlpha);
    }

    @Override
    protected void positionChanged() {
        rocketImage.setX(getX());
        rocketImage.setY(getY());
        operationText.setY(getY() + textYOffset);
        answerText.setY(getY() + answerTextYOffset);
        correctText.setY(getY() + 150);
    }

    @Override
    protected void sizeChanged() {
        rocketImage.setWidth(getWidth());
        rocketImage.setHeight(getHeight());
    }

    public Image getImage() {
        return rocketImage;
    }

    public void setOperation(Operation operation) {
        setSide(Side.FRONT);
        answerText.setText("");
        this.operation = operation;
        String operationString =  operation.getFirstNumber() + " " +
                        operation.getOperationCharacter() + " " + operation.getSecondNumber();

        operationText.setText(operationString);

        switch (operationString.length()) {
            case 5:
                if ('-' == operation.getOperationCharacter()) {
                    setOperationTextParams(158, 345, "Roboto-Regular63");
                } else if (':' == operation.getOperationCharacter())
                    setOperationTextParams(163, 345, "Roboto-Regular65");
                else
                    setOperationTextParams(150, 345, "Roboto-Regular65");
                break;
            case 6:
                if ('-' == operation.getOperationCharacter())
                    setOperationTextParams(154, 340, "Roboto-Regular54");
                else if (':' == operation.getOperationCharacter())
                    setOperationTextParams(155, 340, "Roboto-Regular54");
                else
                    setOperationTextParams(150, 340, "Roboto-Regular54");
                break;
            case 7:
                if ('-' == operation.getOperationCharacter()
                        || ':' == operation.getOperationCharacter())
                    setOperationTextParams(155, 337, "Roboto-Regular43");
                else
                    setOperationTextParams(150, 337, "Roboto-Regular43");
                break;
            case 8:
                if ('-' == operation.getOperationCharacter())
                    setOperationTextParams(154, 334, "Roboto-Regular38");
                else if (':' == operation.getOperationCharacter())
                    setOperationTextParams(156, 334, "Roboto-Regular38");
                else
                    setOperationTextParams(150, 334, "Roboto-Regular38");
                break;
            default: // 9
                if ('-' == operation.getOperationCharacter())
                    setOperationTextParams(155, 334, "Roboto-Regular32");
                else if ('x' == operation.getOperationCharacter())
                    setOperationTextParams(152, 334, "Roboto-Regular32");
                else if (':' == operation.getOperationCharacter())
                    setOperationTextParams(157, 334, "Roboto-Regular32");
                else
                    setOperationTextParams(150, 334, "Roboto-Regular32");
                break;
        }
    }

    public void setAnswer(String answer) {
        if (answer.length() > MAX_ANSWER_LENGTH) {
            setAnswer(answer.substring(MAX_ANSWER_LENGTH));
            return;
        }

        answerText.setText(answer);

        switch (answer.length()) {
            case 1:
                setAnswerTextParams(200, 188, "Roboto-Regular65");
                break;
            case 2:
                setAnswerTextParams(185, 188, "Roboto-Regular65");
                break;
            case 3:
                setAnswerTextParams(165, 188, "Roboto-Regular65");
                break;
            case 4:
                setAnswerTextParams(148, 188, "Roboto-Regular65");
                break;
            default: // 5
                setAnswerTextParams(145, 185, "Roboto-Regular56");
                break;
        }
    }

    public String getAnswer() {
        return answerText.getText();
    }

    public void setResult(int result) {
        String resultStr = String.valueOf(result);
        operationText.setText(resultStr);

        switch (resultStr.length()) {
            case 1:
                setOperationTextParams(200, 348, "Roboto-Regular65");
                break;
            case 2:
                setOperationTextParams(180, 348, "Roboto-Regular65");
                break;
            case 3:
                setOperationTextParams(165, 348, "Roboto-Regular65");
                break;
            case 4:
                setOperationTextParams(148, 348, "Roboto-Regular65");
                break;
            default: // 5
                setOperationTextParams(144, 343, "Roboto-Regular56");
                break;
        }
    }

    public void showResult() {
        if (side == Side.FRONT) {
            checkAnswer();
            moveDown();
            setSide(Side.BACK);
            setResult(operation.getResult());

            if (!game.isPaused()) {
                addAction(
                        new SequenceAction(
                                new FlipOut(getX(), getWidth(), ROTATE_DURATION),
                                new ChangeImage(getImage(),
                                        new Sprite(game.getAtlas().findRegion(ROCKET_BACK_IMAGE))),
                                new FlipIn(getX(), getWidth(), ROTATE_DURATION)
                        )
                );
            } else {
                getImage().setSprite(new Sprite(game.getAtlas().findRegion(ROCKET_BACK_IMAGE)));
            }
        }
    }

    private void checkAnswer() {
        int answer = Utils.toInt(answerText.getText());

        if (answer == operation.getResult()) {
            correctAnswers++;
            correctText.setText(I18N.get("correct"));
            correctText.setColor(Colors.GREEN);
            SoundUtils.playSound(game, "sound/default/correctsound.mp3");
        } else {
            incorrectAnswers++;
            correctText.setText(I18N.get("incorrect"));
            correctText.setColor(Colors.RED);
            SoundUtils.playSound(game, "sound/default/incorrectsound.mp3");
        }
    }

    public void setKeyboard(GameKeyboard keyboard) {
        this.keyboard = keyboard;
    }

    public void moveUp() {
        if (side == Side.FRONT) {
            moveUp = new MoveUp(253);
            showKeyboard = new ShowKeyboard(keyboard);

            if (!game.isPaused()) {
                addAction(new SequenceAction(
                        moveUp,
                        showKeyboard
                ));
            } else {
                setY(253);
                if (keyboard != null)
                    keyboard.setVisible(true);
            }
        }
    }

    public void moveDown() {
        if (moveUp != null)
            moveUp.stop();
        if (showKeyboard != null)
            showKeyboard.stop();
        if (keyboard != null)
            keyboard.setVisible(false);
        setY(0);
    }

    private void setOperationTextParams(int operationTextXOffset, int operationTextYOffset, String fontName) {
        this.textYOffset = operationTextYOffset;
        setTextParams(operationText, operationTextXOffset, operationTextYOffset, fontName);
    }

    private void setTextParams(Text text, int textXOffset, int textYOffset, String fontName) {
        text.setX(getX() + textXOffset);
        text.setY(getY() + textYOffset);
        text.setFontName(game, fontName);
    }

    private void setAnswerTextParams(int answerTextXOffset, int answerTextYOffset, String fontName) {
        this.answerTextYOffset = answerTextYOffset;
        setTextParams(answerText, answerTextXOffset, answerTextYOffset, fontName);
    }

    private void setSide(Side side) {
        this.side = side;

        if (side == Side.FRONT)
            changeCardImage(ROCKET_FRONT_IMAGE);
        else
            changeCardImage(ROCKET_BACK_IMAGE);
    }

    private void changeCardImage(String image) {
        getImage().setSprite(new Sprite(game.getAtlas().findRegion(image)));
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getIncorrectAnswers() {
        return incorrectAnswers;
    }
}
