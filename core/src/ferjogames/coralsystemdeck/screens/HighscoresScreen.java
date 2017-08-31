package ferjogames.coralsystemdeck.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import java.util.List;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.actors.BackArrow;
import ferjogames.coralsystemdeck.actors.HighScoreItem;
import ferjogames.coralsystemdeck.actors.HighScoreTitle;
import ferjogames.coralsystemdeck.actors.Image;
import ferjogames.coralsystemdeck.actors.ToolbarTitle;
import ferjogames.coralsystemdeck.logic.Operation;
import ferjogames.coralsystemdeck.logic.Score;
import ferjogames.coralsystemdeck.utils.Colors;
import ferjogames.coralsystemdeck.utils.DensityFileResolver;
import ferjogames.coralsystemdeck.utils.GamePreferences;
import ferjogames.coralsystemdeck.utils.GraphicUtils;
import ferjogames.coralsystemdeck.utils.I18N;
import ferjogames.coralsystemdeck.utils.Utils;

/**
 * Created by Fer on 09/08/2017.
 */

public class HighscoresScreen extends AbstractScreen {

    private String playerName;
    private Operation operation;

    public HighscoresScreen(final CoralSystemDeck game, final String playerName, final Operation operation) {
        super(game, true);
        this.playerName = playerName;
        this.operation = operation;
        getStage().addActor(new ToolbarTitle(game, I18N.get("highscores")));
        getStage().addActor(new BackArrow(game, this));
        Texture corkboardTexture = game.getAssetManager().get(
                DensityFileResolver.resolve("corkboard.jpg"));
        getStage().addActor(new Image(new Sprite(corkboardTexture), 0, 0, 480, 650));

        Pixmap pixmap = GraphicUtils.getRoundedRectanglePixmap(Utils.toPixelsWidth(316),
                Utils.toPixelsWidth(105), Utils.toPixelsWidth(7), Colors.YELLOW);

        getStage().addActor(new HighScoreTitle(game, operation.getDifficulty().toString(), 100, 530, 286, 105));

        Image leftArrow = new Image(game, "flechaizquierda", 5, 550, 81, 62);
        Image rightArrow = new Image(game, "flechaderecha", 400, 550, 80,60);

        leftArrow.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftClicked();
                return true;
            }
        });

        rightArrow.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rightClicked();
                return true;
            }
        });

        if (operation.getDifficulty() != Operation.Difficulty.EASY)
            getStage().addActor(leftArrow);
        if (operation.getDifficulty() != Operation.Difficulty.HARD)
            getStage().addActor(rightArrow);

        pixmap.dispose();

        pixmap = GraphicUtils.getRoundedRectanglePixmap(Utils.toPixelsWidth(80),
                Utils.toPixelsWidth(80), Utils.toPixelsWidth(4), Colors.ORANGE);

        Table scrollTable = new Table();
        scrollTable.align(Align.topLeft);
        //Group group = new Group();
        List<Score> highScores = GamePreferences.getHighScores(operation);

        for (int i = 0; i < highScores.size(); i++) {
            Score highScore = highScores.get(i);
            scrollTable.add(new HighScoreItem(game, Color.valueOf(highScore.getColorHex()),
                    (i + 1) + "º: " + highScore.getStudentName(), highScore.getPoints()))
                    .width(190).height(150).space(30);

            if (i % 2 != 0)
                scrollTable.row();
        }

        if (highScores.isEmpty())
            getStage().addActor(new HighScoreTitle(game, I18N.get("nohighscores"), 100, 250, 286, 105));

        ScrollPane scroller = new ScrollPane(scrollTable);
        Table table = new Table();
        table.setBounds(33, 10, 410, 500);
        table.add(scroller).fill().expand();

        getStage().addActor(table);
        pixmap.dispose();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        getStage().getBatch().setColor(1, 1, 1, 1);
    }

    @Override
    public void onBackPressed() {
        game.setScreen(new DifficultyScreen(game, playerName, operation));
    }

    @Override
    public void onRightSwipe() {
        leftClicked();
    }

    @Override
    public void onLeftSwipe() {
        rightClicked();
    }

    private void leftClicked() {
        if (operation.getDifficulty() == Operation.Difficulty.MEDIUM)
            operation.setDifficulty(Operation.Difficulty.EASY);
        else
            operation.setDifficulty(Operation.Difficulty.MEDIUM);

        game.setScreen(new HighscoresScreen(game, playerName, operation));
    }

    private void rightClicked() {
        if (operation.getDifficulty() == Operation.Difficulty.EASY)
            operation.setDifficulty(Operation.Difficulty.MEDIUM);
        else
            operation.setDifficulty(Operation.Difficulty.HARD);

        game.setScreen(new HighscoresScreen(game, playerName, operation));
    }
}
