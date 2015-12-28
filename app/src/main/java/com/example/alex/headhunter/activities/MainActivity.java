package com.example.alex.headhunter.activities;

import android.app.Fragment;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.alex.headhunter.NetBaseActivity;
import com.example.alex.headhunter.R;
import com.example.alex.headhunter.fragments.AboutFragment;
import com.example.alex.headhunter.fragments.PrefsFragment;
import com.example.alex.headhunter.fragments.SearchFormFragment;
import com.example.alex.headhunter.fragments.SearchResultsFragment;
import com.example.alex.headhunter.utils.ThemeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends NetBaseActivity implements SearchFormFragment.SearchButtonCallback, SearchResultsFragment.UpdateListCallback {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private Map<String, Fragment> fragmentMap;
    private final Fragment mSearchFormFragment = new SearchFormFragment();
    private final Fragment mSearchResultsFragment = new SearchResultsFragment();
    private final Fragment mAboutFragment = new AboutFragment();
    private final Fragment mPrefFragment = new PrefsFragment();

    private ProgressBar progressBar;

    private final Uri CONTENT_URI = Uri.parse("content://com.example.alex.headhunter.provider/search_result");
    private List<Integer> searchRequestIds;

    private final String searchFormFragmentKey = "search_form_fragment_key";
    private final String searchResultsFragmentKey = "search_results_fragment_key";
    private final String aboutFragmentKey = "about_fragment_key";
    private final String prefFragmentKey = "pref_fragment_key";

    private final String CURRENT_FRAGMENT_EXTRA = "CURRENT_FRAGMENT_EXTRA";
    private final String CURRENT_FRAGMENT_SAVED_EXTRA = "CURRENT_FRAGMENT_SAVED_EXTRA";
    private String currentFragmentKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchRequestIds = new ArrayList<>();
        progressBar = (ProgressBar) findViewById(R.id.toolbar_progress);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {};
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        fragmentMap = new HashMap<>();
        fragmentMap.put(searchFormFragmentKey, mSearchFormFragment);
        fragmentMap.put(searchResultsFragmentKey, mSearchResultsFragment);
        fragmentMap.put(aboutFragmentKey, mAboutFragment);
        fragmentMap.put(prefFragmentKey, mPrefFragment);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.main_content_frame, mSearchFormFragment)
                    .commit();
            currentFragmentKey = searchFormFragmentKey;
        } else {
            currentFragmentKey = savedInstanceState.getString(CURRENT_FRAGMENT_EXTRA);
//            Fragment.SavedState savedState = savedInstanceState.getParcelable(CURRENT_FRAGMENT_SAVED_EXTRA);
            if (currentFragmentKey != null) {
//                fragmentMap.get(currentFragmentKey).setInitialSavedState(savedState);
                getFragmentManager().beginTransaction().replace(R.id.main_content_frame, fragmentMap.get(currentFragmentKey));
            }
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> navigationItemClicked(item));

    }

    private boolean navigationItemClicked(MenuItem item) {
        mDrawerLayout.closeDrawers();

        if (!item.isChecked()) {
            switch (item.getItemId()) {
                case R.id.nav_search:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_content_frame, mSearchFormFragment)
                            .commit();
                    currentFragmentKey = searchFormFragmentKey;
                    break;

                case R.id.nav_results:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_content_frame, mSearchResultsFragment)
                            .commit();
                    currentFragmentKey = searchResultsFragmentKey;
                    break;
                case R.id.nav_manage:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_content_frame, mPrefFragment)
                            .commit();
                    currentFragmentKey = prefFragmentKey;
                    break;
                case R.id.nav_about:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_content_frame, mAboutFragment)
                            .commit();
                    currentFragmentKey = aboutFragmentKey;
                    break;
            }
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onSearchButtonClick() {
        getFragmentManager().beginTransaction()
                .replace(R.id.main_content_frame, mSearchResultsFragment)
                .commit();
//        ((SearchResultsFragment) mSearchResultsFragment).onLoaderReset(null);
    }

    @Override
    public void saveRequestId(int id) {
        if (searchRequestIds.indexOf(id) == -1) {
            searchRequestIds.add(id);
        }
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onServiceCallback(int requestId, int resultCode, Bundle data) {
        if (searchRequestIds.indexOf(requestId) != -1) {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onListUpdateStarted(int id) {
        if (searchRequestIds.indexOf(id) == -1) {
            searchRequestIds.add(id);
        }
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CURRENT_FRAGMENT_EXTRA, currentFragmentKey);
//        outState.putParcelable(CURRENT_FRAGMENT_SAVED_EXTRA, getFragmentManager().saveFragmentInstanceState(fragmentMap.get(currentFragmentKey)));
    }
}
