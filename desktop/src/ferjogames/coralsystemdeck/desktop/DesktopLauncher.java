package ferjogames.coralsystemdeck.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import ferjogames.coralsystemdeck.CoralSystemDeck;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 480;
		config.height = 720;
		config.resizable = false;
		config.useHDPI = true;

		TexturePacker.Settings settings = new TexturePacker.Settings();
		settings.maxHeight = 2048;
		settings.maxWidth = 2048;

		TexturePacker.process(settings, "../../images/xxxhdpi", "xxxhdpi", "pack");
		TexturePacker.process(settings, "../../images/xxhdpi", "xxhdpi", "pack");
		TexturePacker.process(settings, "../../images/xhdpi", "xhdpi", "pack");
		TexturePacker.process(settings, "../../images/hdpi", "hdpi", "pack");
		TexturePacker.process(settings, "../../images/mdpi", "mdpi", "pack");
		TexturePacker.process(settings, "../../images/ldpi", "ldpi", "pack");
		new LwjglApplication(new CoralSystemDeck(), config);
	}
}
