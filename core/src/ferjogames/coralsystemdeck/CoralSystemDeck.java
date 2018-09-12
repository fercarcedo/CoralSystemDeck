package ferjogames.coralsystemdeck;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import ferjogames.coralsystemdeck.screens.LoadingScreen;
import ferjogames.coralsystemdeck.utils.DensityFileResolver;
import ferjogames.coralsystemdeck.utils.I18N;

public class CoralSystemDeck extends Game {

    public static final boolean SCREENSHOT_MODE = false;
    public static final float WORLD_WIDTH = 480;
    public static final float WORLD_HEIGHT = 720;
    public static final Color BACKGROUND_COLOR = Color.valueOf("#2196F3");

    private AssetManager assetManager;
    private String atlasPath;
    private boolean paused;
    private Platform platform;

    public CoralSystemDeck(Platform platform) {
        this.platform = platform;
    }

    @Override
    public void create() {
        I18N.recreateBundle();
        assetManager = new AssetManager();
        setScreen(new LoadingScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        assetManager.dispose();
    }

    public BitmapFont getFont(String fontName) {
        BitmapFont font = assetManager.get(fontName + ".ttf");
        font.getData().setScale(CoralSystemDeck.WORLD_WIDTH / Gdx.graphics.getWidth());
        return font;
    }

    public TextureAtlas getAtlas() {
        if (atlasPath == null)
            atlasPath = DensityFileResolver.resolve("pack.atlas");
        return getAssetManager().get(atlasPath);
    }

    public Skin getUISkin() {
        return getAssetManager().get("skins/skin.json");
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public boolean isPaused() {
        return paused;
    }

    public Platform getPlatform() {
        return platform;
    }

    @Override
    public void pause() {
        super.pause();
        paused = true;
    }

    @Override
    public void resume() {
        super.resume();
        paused = false;
    }
}
