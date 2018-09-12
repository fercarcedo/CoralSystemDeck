package ferjogames.coralsystemdeck.desktop;

import java.io.File;

public class License {
	public boolean checkLicense() {
		return new File(System.getProperty("user.home"), ".coralsystemdeck").exists();
	}
}
