package ferjogames.coralsystemdeck.screens;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.actors.BackArrow;
import ferjogames.coralsystemdeck.actors.CountdownCircle;
import ferjogames.coralsystemdeck.actors.GameKeyboard;
import ferjogames.coralsystemdeck.actors.GameRocket;
import ferjogames.coralsystemdeck.actors.Image;
import ferjogames.coralsystemdeck.actors.ToolbarTitle;
import ferjogames.coralsystemdeck.logic.MockOperation;
import ferjogames.coralsystemdeck.logic.Operation;
import ferjogames.coralsystemdeck.utils.Colors;

/**
 * Created by Fer on 09/08/2017.
 */

public class GameScreen extends AbstractScreen implements GameKeyboard.KeyboardListener {

    private static final int CARD_SECONDS = 10;
    private static final int TOTAL_CARD_SECONDS = 60;

    private String playerName;
    private Operation operation;
    private GameRocket rocket;
    private ToolbarTitle toolbarTitle;
    private CountdownCircle cardCountDownCircle;
    private CountdownCircle totalCountDownCircle;
    private Timer timer;
    private int secondsRemaining = CARD_SECONDS;
    private int totalSeconds = TOTAL_CARD_SECONDS;

    public GameScreen(CoralSystemDeck game, String playerName, Operation operation) {
        super(game);
        this.playerName = playerName;
        this.operation = operation;

        if (CoralSystemDeck.SCREENSHOT_MODE) {
            this.operation = operation = new MockOperation();
        }
        rocket = new GameRocket(game);
        final GameKeyboard keyboard = new GameKeyboard(game);
        keyboard.setListener(this);
        rocket.setKeyboard(keyboard);
        getStage().addActor(keyboard);
        getStage().addActor(rocket);

        if (CoralSystemDeck.SCREENSHOT_MODE) {
            rocket.setAnswer(String.valueOf(operation.getResult()));
        }
        Pixmap pixmap = new Pixmap(480, 75, Pixmap.Format.RGBA8888);
        pixmap.setColor(Colors.BLUE);
        pixmap.fill();
        getStage().addActor(new Image(new Sprite(new Texture(pixmap)), 0, 645, 480, 76));
        pixmap.dispose();

        cardCountDownCircle = new CountdownCircle(game, getStage().getCamera(), 420);
        totalCountDownCircle = new CountdownCircle(game, getStage().getCamera(), 60);
        getStage().addActor(cardCountDownCircle);
        getStage().addActor(totalCountDownCircle);

        toolbarTitle = new ToolbarTitle(game, operation.toString());
        getStage().addActor(toolbarTitle);
        getStage().addActor(new BackArrow(game, this));

        initGame();
    }

    @Override
    public void onBackPressed() {
        if (timer != null)
            timer.cancel();
        game.setScreen(new DifficultyScreen(game, playerName, operation));
    }

    @Override
    public void numberClicked(int number) {
        rocket.setAnswer(rocket.getAnswer() + number);
    }

    @Override
    public void cancelClicked() {
        rocket.setAnswer("");
    }

    @Override
    public void confirmClicked() {
        if (CoralSystemDeck.SCREENSHOT_MODE) {
            totalCountDownCircle.setValue(String.format(Locale.getDefault(), "%02d", 51));
            cardCountDownCircle.setValue(String.format(Locale.getDefault(), "%02d", 1));
        }
        secondsRemaining = CARD_SECONDS;
        rocket.showResult();

        if (timer != null)
            timer.cancel();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                initGame();
            }
        }, 2000);
    }

    private void initGame() {
        if (totalSeconds <= 1) {
            if (totalSeconds == 1)
                totalCountDownCircle.setValue(String.format(Locale.getDefault(), "%02d", --totalSeconds));
            launchResult();
            return;
        }
        operation.generateNumbers();
        rocket.setOperation(operation);
        toolbarTitle.setText(operation.toString());

        if (timer != null)
            timer.cancel();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                --totalSeconds;
                --secondsRemaining;
                if (CoralSystemDeck.SCREENSHOT_MODE) {
                    totalCountDownCircle.setValue(String.format(Locale.getDefault(), "%02d", 55));
                    cardCountDownCircle.setValue(String.format(Locale.getDefault(), "%02d", 5));
                } else {
                    totalCountDownCircle.setValue(String.format(Locale.getDefault(), "%02d", totalSeconds));
                    cardCountDownCircle.setValue(String.format(Locale.getDefault(), "%02d", secondsRemaining));
                }
                if (totalSeconds <= 0 || secondsRemaining <= 0) {
                    if (timer != null)
                        timer.cancel();

                    confirmClicked();
                }
            }
        }, 0, 1000);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                rocket.moveUp();
            }
        }, 200);
    }

    private void launchResult() {
        if (timer != null)
            timer.cancel();

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                game.setScreen(new ResultScreen(game, playerName, operation,
                        rocket.getCorrectAnswers(), rocket.getIncorrectAnswers()));
            }
        });
    }
}
