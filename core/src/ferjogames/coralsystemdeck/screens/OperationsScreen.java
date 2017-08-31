package ferjogames.coralsystemdeck.screens;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.actors.BackArrow;
import ferjogames.coralsystemdeck.actors.OperationButton;
import ferjogames.coralsystemdeck.actors.ToolbarTitle;
import ferjogames.coralsystemdeck.logic.Addition;
import ferjogames.coralsystemdeck.logic.Division;
import ferjogames.coralsystemdeck.logic.Operation;
import ferjogames.coralsystemdeck.logic.Product;
import ferjogames.coralsystemdeck.logic.RandomOperation;
import ferjogames.coralsystemdeck.logic.Substraction;
import ferjogames.coralsystemdeck.utils.Colors;
import ferjogames.coralsystemdeck.utils.I18N;
import ferjogames.coralsystemdeck.utils.SoundUtils;

/**
 * Created by Fer on 31/07/2017.
 */

public class OperationsScreen extends AbstractScreen {

    private OperationButton additionButton;
    private OperationButton substractionButton;
    private OperationButton randomButton;
    private OperationButton productButton;
    private OperationButton divisionButton;
    private String playerName;

    public OperationsScreen(CoralSystemDeck game, String playerName) {
        super(game);
        this.playerName = playerName;
        getStage().addActor(new ToolbarTitle(game, I18N.get("greet", playerName)));
        getStage().addActor(new BackArrow(game, this));
        Camera camera = getStage().getCamera();
        additionButton = new OperationButton(game, camera, 100, 550, Colors.YELLOW, 80, 575, Colors.RED, "+");

        getStage().addActor(additionButton);

        substractionButton = new OperationButton(game, camera, 380, 550, Colors.PURPLE, 367, 575, Colors.YELLOW, "-", "font55");
        getStage().addActor(substractionButton);

        randomButton = new OperationButton(game, camera, 240, 330, Colors.RED, 225, 355, Colors.BLACK, "?");
        getStage().addActor(randomButton);

        productButton = new OperationButton(game, camera, 100, 110, Colors.GREEN, 80, 133, Colors.BLACK, "X");
        getStage().addActor(productButton);

        divisionButton = new OperationButton(game, camera, 380, 110, Colors.ORANGE, 373, 140, Colors.BLACK, ":");
        getStage().addActor(divisionButton);
        setClickListeners();
        SoundUtils.playSound(game, I18N.get("letsplaysound"));
    }

    private void setOperationButtonListener(Actor actor, final Operation operation) {
        actor.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new DifficultyScreen(game, playerName, operation));
                return true;
            }
        });
    }

    private void setClickListeners() {
        setOperationButtonListener(additionButton, new Addition());
        setOperationButtonListener(substractionButton, new Substraction());
        setOperationButtonListener(randomButton, new RandomOperation());
        setOperationButtonListener(productButton, new Product());
        setOperationButtonListener(divisionButton, new Division());
    }

    @Override
    public void onBackPressed() {
        game.setScreen(new NameScreen(game));
    }

    @Override
    public void dispose() {
        super.dispose();
        additionButton.dispose();
        substractionButton.dispose();
        randomButton.dispose();
        productButton.dispose();
        divisionButton.dispose();
    }
}
