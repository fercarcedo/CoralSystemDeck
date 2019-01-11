package ferjogames.coralsystemdeck.desktop;

import java.util.ResourceBundle;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.utils.DensityFileResolver;

public class DesktopLauncher {
	private static final boolean PACK_TEXTURES = false;
	
	public static void main (String[] arg) {
		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 480;
		config.height = 720;
		config.resizable = false;
		config.useGL20 = false;
		config.title = ResourceBundle.getBundle("i18n/strings").getString("app_name");
		config.addIcon("images/ic_launcher_128_128.png", Files.FileType.Internal);
		config.addIcon("images/ic_launcher_32_32.png", Files.FileType.Internal);
		config.addIcon("images/ic_launcher_16_16.png", Files.FileType.Internal);
		
		if (PACK_TEXTURES) {
			TexturePacker2.Settings settings = new TexturePacker2.Settings();
			settings.maxHeight = 2048;
			settings.maxWidth = 2048;

			TexturePacker2.process(settings, "images/xxxhdpi", "xxxhdpi", "pack");
			TexturePacker2.process(settings, "images/xxhdpi", "xxhdpi", "pack");
			TexturePacker2.process(settings, "images/xhdpi", "xhdpi", "pack");
			TexturePacker2.process(settings, "images/hdpi", "hdpi", "pack");
			TexturePacker2.process(settings, "images/mdpi", "mdpi", "pack");
			TexturePacker2.process(settings, "images/ldpi", "ldpi", "pack");
		}

		DensityFileResolver.HIGHER_DENSITIES_FIRST = true;
		if (!(new License().checkLicense())) {
			System.exit(-1);
		}
		new LwjglApplication(new CoralSystemDeck(new Desktop()), config);
	}
}
