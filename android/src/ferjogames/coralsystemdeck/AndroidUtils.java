package ferjogames.coralsystemdeck;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by Fer on 28/08/2017.
 */

public class AndroidUtils {
    public static boolean isStoreVersion(Context context) {
        String installer = context.getPackageManager()
                .getInstallerPackageName(context.getPackageName());
        return !TextUtils.isEmpty(installer);
    }

    public static void openPlayStoreListing(Context context) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + context.getPackageName())));
        } catch (ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }
}
