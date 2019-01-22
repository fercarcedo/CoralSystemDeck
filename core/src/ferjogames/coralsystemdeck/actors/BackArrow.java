package ferjogames.coralsystemdeck.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.utils.GameStage;

/**
 * Created by Fer on 31/07/2017.
 */

public class BackArrow extends Actor {

    private static final int X_POS = 20;
    private static final int Y_POS = 660;
    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;

    private CoralSystemDeck game;
    private GameStage.OnBackPressedListener listener;

    public BackArrow(CoralSystemDeck game, GameStage.OnBackPressedListener listener) {
        this.game = game;
        this.listener = listener;
        setBounds(X_POS, Y_POS, WIDTH, HEIGHT);
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                BackArrow.this.listener.onBackPressed();
                return true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
    	Color batchColor = batch.getColor();
    	batch.setColor(Color.WHITE);
        batch.draw(game.getAtlas().findRegion("ic_back"), X_POS, Y_POS, WIDTH, HEIGHT);
       batch.setColor(batchColor);
    }
}
