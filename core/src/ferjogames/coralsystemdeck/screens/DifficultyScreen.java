package ferjogames.coralsystemdeck.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.actors.BackArrow;
import ferjogames.coralsystemdeck.actors.DifficultyButton;
import ferjogames.coralsystemdeck.actors.ToolbarTitle;
import ferjogames.coralsystemdeck.logic.Operation;
import ferjogames.coralsystemdeck.utils.Colors;
import ferjogames.coralsystemdeck.utils.I18N;

/**
 * Created by Fer on 09/08/2017.
 */

public class DifficultyScreen extends AbstractScreen {

    private String playerName;
    private Operation operation;

    public DifficultyScreen(final CoralSystemDeck game, final String playerName, final Operation operation) {
        super(game);
        this.playerName = playerName;
        this.operation = operation;
        getStage().addActor(new ToolbarTitle(game, I18N.get("difficulty")));
        getStage().addActor(new BackArrow(game, this));

        DifficultyButton easyButton = new DifficultyButton(game, Colors.GREEN, I18N.get("easy").toUpperCase(), 470);
        setDifficultyButtonListener(easyButton, Operation.Difficulty.EASY);
        getStage().addActor(easyButton);

        DifficultyButton mediumButton = new DifficultyButton(game, Colors.YELLOW, I18N.get("medium").toUpperCase(), 340);
        setDifficultyButtonListener(mediumButton, Operation.Difficulty.MEDIUM);
        getStage().addActor(mediumButton);

        DifficultyButton hardButton = new DifficultyButton(game, Colors.RED, I18N.get("hard").toUpperCase(), 210);
        setDifficultyButtonListener(hardButton, Operation.Difficulty.HARD);
        getStage().addActor(hardButton);

        DifficultyButton recordsButton = new DifficultyButton(game, Colors.ORANGE, I18N.get("highscores").toUpperCase(), 80);
        recordsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                operation.setDifficulty(Operation.Difficulty.EASY);
                DifficultyScreen.this.game.setScreen(new HighscoresScreen(DifficultyScreen.this.game, playerName, operation));
                return true;
            }
        });
        getStage().addActor(recordsButton);
    }

    @Override
    public void onBackPressed() {
        game.setScreen(new OperationsScreen(game, playerName));
    }

    private void setDifficultyButtonListener(Actor difficultyButton, final Operation.Difficulty difficulty) {
        difficultyButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                operation.setDifficulty(difficulty);
                game.setScreen(new GameScreen(game, playerName, operation));
                return true;
            }
        });
    }
}
