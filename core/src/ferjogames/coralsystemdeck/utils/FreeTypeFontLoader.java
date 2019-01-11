package ferjogames.coralsystemdeck.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;

public class FreeTypeFontLoader extends SynchronousAssetLoader<BitmapFont, FreeTypeFontLoader.FreeTypeFontParameters> {

    public FreeTypeFontLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public BitmapFont load(AssetManager assetManager, String fileName, FileHandle file, FreeTypeFontParameters parameter) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(parameter.getFont()));
        return generator.generateFont(parameter.getSize());
    }

    static public class FreeTypeFontParameters extends AssetLoaderParameters<BitmapFont> {
        private String font;
        private int size;
        
        public FreeTypeFontParameters(String font, int size) {
        	this.font = font;
        	this.size = size;
        }
        
        public String getFont() {
			return font;
		}
        
        public int getSize() {
        	return size;
        }
    }

    @SuppressWarnings("rawtypes")
	@Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, FreeTypeFontParameters parameter) {
        return null;
    }

}