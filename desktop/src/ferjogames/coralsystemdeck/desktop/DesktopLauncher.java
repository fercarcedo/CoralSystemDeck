package ferjogames.coralsystemdeck.desktop;

import java.util.ResourceBundle;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.utils.DensityFileResolver;

public class DesktopLauncher {
	private static final boolean PACK_TEXTURES = false;
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 480;
		config.height = 720;
		config.resizable = false;
		config.useHDPI = true;
		config.title = ResourceBundle.getBundle("i18n/strings").getString("app_name");
		config.addIcon("images/ic_launcher_128_128.png", Files.FileType.Internal);
		config.addIcon("images/ic_launcher_32_32.png", Files.FileType.Internal);
		config.addIcon("images/ic_launcher_16_16.png", Files.FileType.Internal);
		
		if (PACK_TEXTURES) {
			TexturePacker.Settings settings = new TexturePacker.Settings();
			settings.maxHeight = 2048;
			settings.maxWidth = 2048;

			TexturePacker.process(settings, "images/xxxhdpi", "xxxhdpi", "pack");
			TexturePacker.process(settings, "images/xxhdpi", "xxhdpi", "pack");
			TexturePacker.process(settings, "images/xhdpi", "xhdpi", "pack");
			TexturePacker.process(settings, "images/hdpi", "hdpi", "pack");
			TexturePacker.process(settings, "images/mdpi", "mdpi", "pack");
			TexturePacker.process(settings, "images/ldpi", "ldpi", "pack");
		}

		DensityFileResolver.HIGHER_DENSITIES_FIRST = true;
		if (!(new License().checkLicense())) {
			System.exit(-1);
		}
		new LwjglApplication(new CoralSystemDeck(new Desktop()), config);
	}
}
