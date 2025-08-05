package ferjogames.coralsystemdeck.screens;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.List;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.actors.Image;
import ferjogames.coralsystemdeck.logic.Operation;
import ferjogames.coralsystemdeck.logic.Score;
import ferjogames.coralsystemdeck.utils.Colors;
import ferjogames.coralsystemdeck.utils.GamePreferences;
import ferjogames.coralsystemdeck.utils.GraphicUtils;
import ferjogames.coralsystemdeck.utils.I18N;
import ferjogames.coralsystemdeck.utils.Utils;

/**
 * Created by Fer on 09/08/2017.
 */

public class ResultScreen extends AbstractScreen {

    private String playerName;
    private Operation operation;

    public ResultScreen(CoralSystemDeck game, String playerName, Operation operation,
                        int correctAnswers, int incorrectAnswers) {
        super(game);
        this.playerName = playerName;
        this.operation = operation;

        if (CoralSystemDeck.SCREENSHOT_MODE) {
            correctAnswers = 17;
            incorrectAnswers = 1;
        }
        getStage().addActor(GraphicUtils.createLabel(I18N.get("results"), game, "font55", Colors.YELLOW, 0, 630, CoralSystemDeck.WORLD_WIDTH));
        getStage().addActor(new Image(game, "naveespacial", 180, 455, 110, 160));

        createResultsBlackboard();
        createPlayAgainButton();
        createMenuButton();

        List<Score> highScores = GamePreferences.getHighScores(operation);
        int points = correctAnswers * 10;
        int x = 40;
        int width = 400;
        if (Utils.newRecord(highScores, playerName, points)) {
            getStage().addActor(GraphicUtils.createLabel(I18N.get("correctcards", correctAnswers),
                    game, "font35", Colors.BLUE, x, 385, width));
            getStage().addActor(GraphicUtils.createLabel(I18N.get("incorrectcards", incorrectAnswers),
                    game, "font35", Colors.ORANGE, x, 315, width));
            getStage().addActor(GraphicUtils.createLabel(I18N.get("points", points),
                    game, "font35", Colors.YELLOW, x, 245, width));
            getStage().addActor(GraphicUtils.createLabel(I18N.get("newhighscore"),
                    game, "font35", Colors.GREEN, x, 175, width));
        } else {
            getStage().addActor(GraphicUtils.createLabel(I18N.get("correctcards", correctAnswers),
                    game, "font35", Colors.BLUE, x, 350, width));
            getStage().addActor(GraphicUtils.createLabel(I18N.get("incorrectcards", incorrectAnswers),
                    game, "font35", Colors.ORANGE, x, 280, width));
            getStage().addActor(GraphicUtils.createLabel(I18N.get("points", points),
                    game, "font35", Colors.YELLOW, x, 210, width));
        }
        Utils.saveResult(operation, highScores, playerName, points);
    }

    private void createResultsBlackboard() {
        Pixmap pixmap = GraphicUtils.getBlackboardPixmap(Utils.toPixelsWidth(400),
                Utils.toPixelsHeight(320),
                Utils.toPixelsWidth(7));

        Image image = new Image(new Sprite(new Texture(pixmap)), 40, 135, 400, 320);
        image.setBounds(40, 130, 400, 340);
        getStage().addActor(image);
    }

    private void createPlayAgainButton() {
        BitmapFont font = game.getAssetManager().get("font20.ttf");
        TextButton playAgainButton = GraphicUtils.createTextButton(I18N.get("playagain").toUpperCase(), 150, 70, 170, 50, font, Colors.WHITE, Colors.RED);
        playAgainButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game, playerName, operation));
                return true;
            }
        });
        getStage().addActor(playAgainButton);
    }

    private void createMenuButton() {
        BitmapFont font = game.getAssetManager().get("font20.ttf");
        TextButton menuButton = GraphicUtils.createTextButton(I18N.get("menu").toUpperCase(), 150, 10, 170, 50, font, Colors.BLACK, Colors.GREEN);
        menuButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new OperationsScreen(game, playerName));
                return true;
            }
        });
        getStage().addActor(menuButton);
    }

    @Override
    public void onBackPressed() {
        game.setScreen(new DifficultyScreen(game, playerName, operation));
    }
}
