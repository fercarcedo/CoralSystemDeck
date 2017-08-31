package ferjogames.coralsystemdeck.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.Locale;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.actors.Text;
import ferjogames.coralsystemdeck.utils.DensityFileResolver;
import ferjogames.coralsystemdeck.utils.I18N;
import ferjogames.coralsystemdeck.utils.Utils;

/**
 * Created by Fer on 04/08/2017.
 */
public class LoadingScreen extends AbstractScreen {

    private Text loadingText;

    public LoadingScreen(CoralSystemDeck game) {
        super(game);
        loadAssets();
    }

    @Override
    public void onBackPressed() {
        Gdx.app.exit();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        long startTime = System.currentTimeMillis();

        if (game.getAssetManager().update()) {
            System.out.println(game.getAssetManager().get(DensityFileResolver.resolve("pack.atlas"), TextureAtlas.class).getRegions());
            game.setScreen(new MainScreen(game));
        } else if (loadingText != null) {
            long time = System.currentTimeMillis() - startTime;
            System.out.println("Load asset time: " + time);
            loadingText.setText((int)(game.getAssetManager().getProgress() * 100) + " %");
        }

        if (loadingText == null) {
            if (game.getAssetManager().isLoaded("font55.ttf")) {
                loadingText = new Text(game, "font55", 190, 380, "0%", Color.WHITE);
                getStage().addActor(loadingText);
            }
        }
    }

    private void loadAssets() {
        AssetManager assetManager = game.getAssetManager();
        FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(new InternalFileHandleResolver()));

        FreetypeFontLoader.FreeTypeFontLoaderParameter fontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParams.fontFileName = "fonts/font.ttf";
        fontParams.fontParameters.size = Utils.fontSizeToPixels(55);

        assetManager.load("font55.ttf", BitmapFont.class, fontParams);

        fontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParams.fontFileName = "fonts/font.ttf";
        fontParams.fontParameters.size = Utils.fontSizeToPixels(35);

        assetManager.load("font35.ttf", BitmapFont.class, fontParams);

        fontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParams.fontFileName = "fonts/Roboto-Regular.ttf";
        fontParams.fontParameters.size = Utils.fontSizeToPixels(25);

        assetManager.load("Roboto-Regular25.ttf", BitmapFont.class, fontParams);

        fontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParams.fontFileName = "fonts/Roboto-Regular.ttf";
        fontParams.fontParameters.size = Utils.fontSizeToPixels(65);

        assetManager.load("Roboto-Regular65.ttf", BitmapFont.class, fontParams);

        fontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParams.fontFileName = "fonts/Roboto-Regular.ttf";
        fontParams.fontParameters.size = Utils.fontSizeToPixels(54);

        assetManager.load("Roboto-Regular54.ttf", BitmapFont.class, fontParams);

        fontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParams.fontFileName = "fonts/Roboto-Regular.ttf";
        fontParams.fontParameters.size = Utils.fontSizeToPixels(20);

        assetManager.load("Roboto-Regular20.ttf", BitmapFont.class, fontParams);

        fontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParams.fontFileName = "fonts/Roboto-Bold.ttf";
        fontParams.fontParameters.size = Utils.fontSizeToPixels(20);

        assetManager.load("Roboto-Regular20Bold.ttf", BitmapFont.class, fontParams);

        fontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParams.fontFileName = "fonts/Roboto-Regular.ttf";
        fontParams.fontParameters.size = Utils.fontSizeToPixels(43);

        assetManager.load("Roboto-Regular43.ttf", BitmapFont.class, fontParams);

        fontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParams.fontFileName = "fonts/Roboto-Regular.ttf";
        fontParams.fontParameters.size = Utils.fontSizeToPixels(38);

        assetManager.load("Roboto-Regular38.ttf", BitmapFont.class, fontParams);

        fontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParams.fontFileName = "fonts/Roboto-Regular.ttf";
        fontParams.fontParameters.size = Utils.fontSizeToPixels(63);

        assetManager.load("Roboto-Regular63.ttf", BitmapFont.class, fontParams);

        fontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParams.fontFileName = "fonts/Roboto-Regular.ttf";
        fontParams.fontParameters.size = Utils.fontSizeToPixels(32);

        assetManager.load("Roboto-Regular32.ttf", BitmapFont.class, fontParams);

        fontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParams.fontFileName = "fonts/Roboto-Black.ttf";
        fontParams.fontParameters.size = Utils.fontSizeToPixels(30);

        assetManager.load("Roboto-Black30.ttf", BitmapFont.class, fontParams);

        fontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParams.fontFileName = "fonts/Roboto-Bold.ttf";
        fontParams.fontParameters.size = Utils.fontSizeToPixels(70);

        assetManager.load("Roboto-Bold70.ttf", BitmapFont.class, fontParams);

        fontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParams.fontFileName = "fonts/Roboto-Bold.ttf";
        fontParams.fontParameters.size = Utils.fontSizeToPixels(35);

        assetManager.load("Roboto-Bold35.ttf", BitmapFont.class, fontParams);

        fontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParams.fontFileName = "fonts/font.ttf";
        fontParams.fontParameters.size = Utils.fontSizeToPixels(30);

        assetManager.load("font30.ttf", BitmapFont.class, fontParams);

        fontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParams.fontFileName = "fonts/font.ttf";
        fontParams.fontParameters.size = Utils.fontSizeToPixels(20);

        assetManager.load("font20.ttf", BitmapFont.class, fontParams);

        fontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParams.fontFileName = "fonts/font.ttf";
        fontParams.fontParameters.size = Utils.fontSizeToPixels(40);

        assetManager.load("font40.ttf", BitmapFont.class, fontParams);

        assetManager.load(DensityFileResolver.resolve("pack.atlas"), TextureAtlas.class);

        assetManager.load(DensityFileResolver.resolve("corkboard.jpg"), Texture.class);

        assetManager.load(I18N.get("ifyouwanttoplaysound"), Music.class);

        assetManager.load(I18N.get("letsplaysound"), Music.class);

        assetManager.load(I18N.get("whatsyournamesound"), Music.class);

        assetManager.load("sound/default/correctsound.mp3", Music.class);

        assetManager.load("sound/default/incorrectsound.mp3", Music.class);

        assetManager.load("skins/skin.json", Skin.class);
    }
}
