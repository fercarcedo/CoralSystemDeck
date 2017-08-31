package ferjogames.coralsystemdeck;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Fabric.with(this, new Crashlytics());
		FrameLayout layout = new FrameLayout(this);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		layout.addView(initializeForView(new CoralSystemDeck(), config));
		setContentView(layout);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (!BuildConfig.DEBUG && !AndroidUtils.isStoreVersion(this))
			AndroidUtils.openPlayStoreListing(this);
	}
}
