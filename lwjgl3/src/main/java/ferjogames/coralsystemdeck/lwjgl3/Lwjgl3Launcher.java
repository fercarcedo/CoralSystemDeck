package ferjogames.coralsystemdeck.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.glutils.HdpiMode;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import java.util.ResourceBundle;

import ferjogames.coralsystemdeck.CoralSystemDeck;
import ferjogames.coralsystemdeck.utils.DensityFileResolver;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    private static final boolean PACK_TEXTURES = false;

    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new CoralSystemDeck(new Desktop()), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle(ResourceBundle.getBundle("i18n/strings").getString("app_name"));
        configuration.setWindowedMode(480, 720);
        configuration.setResizable(false);
        configuration.setHdpiMode(HdpiMode.Logical);

        configuration.useVsync(true);
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);

        configuration.setWindowIcon(
            "images/ic_launcher_128_128.png",
            "images/ic_launcher_32_32.png",
            "images/ic_launcher_16_16.png"
        );

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
        return configuration;
    }
}
