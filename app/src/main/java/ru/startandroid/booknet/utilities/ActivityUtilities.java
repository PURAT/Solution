package ru.startandroid.booknet.utilities;

import android.app.Activity;
import android.content.Intent;

import ru.startandroid.booknet.constants.AppConstants;

public class ActivityUtilities {

    private static ActivityUtilities activityUtilities = null;

    public static ActivityUtilities getInstance() {
        if (activityUtilities == null) {
            activityUtilities = new ActivityUtilities();
        }
        return activityUtilities;
    }

    public void invokeNewActivity(Activity activity, Class<?> tClass, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public void invokeCustomUrlActivity(Activity activity, Class<?> tClass, String pageTitle, String pageUrl, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        intent.putExtra(AppConstants.BUNDLE_KEY_TITLE, pageTitle);
        intent.putExtra(AppConstants.BUNDLE_KEY_URL, pageUrl);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }
}
