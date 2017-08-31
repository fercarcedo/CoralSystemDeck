package ferjogames.coralsystemdeck;

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
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("market://details?id=" + context.getPackageName()));
        boolean playStoreFound = false;

        //Find all applications able to handle the Intent
        final List<ResolveInfo> otherApps = context.getPackageManager().queryIntentActivities(intent, 0);

        for(ResolveInfo otherApp : otherApps) {
            //Look for Google Play app
            if(otherApp.activityInfo.applicationInfo.packageName.equals("com.android.vending")) {
                ActivityInfo otherAppActivity = otherApp.activityInfo;
                ComponentName componentName = new ComponentName(
                        otherAppActivity.applicationInfo.packageName,
                        otherAppActivity.name
                );

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setComponent(componentName);
                context.startActivity(intent);
                playStoreFound = true;
                break;
            }
        }

        //If Google Play isn't present on device, open up browser
        if(!playStoreFound) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName()));
            context.startActivity(webIntent);
        }
    }
}
