package ferjogames.coralsystemdeck;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import ferjogames.coralsystemdeck.screens.LoadingScreen;
import ferjogames.coralsystemdeck.screens.MainScreen;
import ferjogames.coralsystemdeck.screens.OperationsScreen;
import ferjogames.coralsystemdeck.utils.DensityFileResolver;
import ferjogames.coralsystemdeck.utils.Utils;

public class CoralSystemDeck extends Game {

    public static final float WORLD_WIDTH = 480;
    public static final float WORLD_HEIGHT = 720;
    public static final Color BACKGROUND_COLOR = Color.valueOf("#2196F3");

    private AssetManager assetManager;

    @Override
    public void create() {
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
        return getAssetManager().get(DensityFileResolver.resolve("pack.atlas"));
    }

    public Skin getUISkin() {
        return getAssetManager().get("skins/skin.json");
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
