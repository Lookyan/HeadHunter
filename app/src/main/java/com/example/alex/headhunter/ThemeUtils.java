package com.example.alex.headhunter;

/**
 * Created by nano on 28.12.15.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.TypedValue;


public class ThemeUtils {

    private static int cTheme;
    public final static int AppThemeLight = 0;
    public final static int AppThemeDark = 1;

    public static void changeToTheme(Activity activity)

    {
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    public static void onActivityCreateSetTheme(Activity activity)
    {

        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(activity);
        int index = Integer.valueOf(prefs.getString(PrefsFragment.PREF_THEME, "-1"));
        switch (index) {
            default:
            case AppThemeLight:
                activity.setTheme(R.style.AppThemeLight);
                break;

            case AppThemeDark:
                activity.setTheme(R.style.AppThemeDark);
                break;
        }
    }

}