package com.example.alex.headhunter.utils;

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

import com.example.alex.headhunter.R;
import com.example.alex.headhunter.fragments.PrefsFragment;


public class ThemeUtils {

    private static int cTheme;
    public final static int AppThemeLight = 0;
    public final static int AppThemeDark = 1;

    public static void changeToTheme(Activity activity)

    {
        Intent i = activity.getIntent();
        activity.finish();
        activity.startActivity(i);
    }

    public static void onActivityCreateSetTheme(Activity activity)
    {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
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