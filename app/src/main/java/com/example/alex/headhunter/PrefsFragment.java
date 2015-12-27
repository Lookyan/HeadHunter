package com.example.alex.headhunter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Map;
import java.util.Objects;

/**
 * Created by nano on 26.12.15.
 */


public class PrefsFragment extends PreferenceFragment{
    static final String PREF_HISTORY_SAVE = "history_save";
    static final String PREF_CITY= "city";
    static final String PREF_THEME = "theme";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(super.getActivity());

        ListPreference listThemes = (ListPreference) findPreference(PREF_THEME);
        CharSequence[] themes = listThemes.getEntries();

        Log.i("history_save:", ((Boolean)prefs.getBoolean(PREF_HISTORY_SAVE, true)).toString());
        Log.i("city:",  prefs.getString(PREF_CITY, ""));

        int index = Integer.valueOf(prefs.getString(PREF_THEME, "-1"));
        if ( index >= 0 && index < themes.length)
            Log.i("theme:", themes[index].toString());

        prefs.getAll();
    }
}