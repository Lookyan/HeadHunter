package com.example.alex.headhunter.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.internal.widget.*;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.alex.headhunter.R;
import com.example.alex.headhunter.activities.MainActivity;
import com.example.alex.headhunter.utils.ThemeUtils;

import java.util.Map;
import java.util.Objects;

/**
 * Created by nano on 26.12.15.
 */


public class PrefsFragment extends PreferenceFragment{
    static final String PREF_HISTORY_SAVE = "history_save";
    static final String PREF_CITY= "city";
    public static final String PREF_THEME = "theme";
    public static final String PREF_CLEAR_HISTORY = "history_clear";

    public final Uri CONTENT_URI = Uri.parse("content://com.example.alex.headhunter.provider/search_result");
    private Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = getActivity();
        addPreferencesFromResource(R.xml.preferences);

        ListPreference listThemes = (ListPreference) findPreference(PREF_THEME);

        listThemes.setOnPreferenceChangeListener((preference, newValue) -> {
            ThemeUtils.changeToTheme(activity);
            return true;
        });

        Preference clearHistoryPref = findPreference(PREF_CLEAR_HISTORY);

        clearHistoryPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                getActivity().getContentResolver().delete(CONTENT_URI, null, null);
                Toast.makeText(getActivity(), "Список очищен", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

}