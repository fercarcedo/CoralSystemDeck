package ferjogames.coralsystemdeck.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.utils.GameStage;

/**
 * Created by Fer on 31/07/2017.
 */

public abstract class AbstractScreen implements Screen, GameStage.OnBackPressedListener, GameStage.OnSwipeListener {

    protected GameStage stage;
    protected CoralSystemDeck game;

    public AbstractScreen(CoralSystemDeck game, boolean scroll) {
        this.game = game;
        stage = new GameStage(scroll);
        stage.setOnBackPressedListener(this);
        stage.setOnSwipeListener(this);
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    public AbstractScreen(CoralSystemDeck game) {
        this(game, false);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(CoralSystemDeck.BACKGROUND_COLOR.r, CoralSystemDeck.BACKGROUND_COLOR.g,
                            CoralSystemDeck.BACKGROUND_COLOR.b, CoralSystemDeck.BACKGROUND_COLOR.a);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }

    public abstract void onBackPressed();

    @Override
    public void onLeftSwipe() {}

    @Override
    public void onRightSwipe() {}
}
