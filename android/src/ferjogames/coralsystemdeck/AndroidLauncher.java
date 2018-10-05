package ferjogames.coralsystemdeck;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FrameLayout layout = new FrameLayout(this);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		layout.addView(initializeForView(new CoralSystemDeck(new Android(this)), config));
		setContentView(layout);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (!BuildConfig.DEBUG && !AndroidUtils.isStoreVersion(this))
			AndroidUtils.openPlayStoreListing(this);
	}
}
