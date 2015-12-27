package com.example.alex.headhunter.activities;

import android.app.Fragment;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.alex.headhunter.NetBaseActivity;
import com.example.alex.headhunter.R;
import com.example.alex.headhunter.fragments.AboutFragment;
import com.example.alex.headhunter.fragments.PrefsFragment;
import com.example.alex.headhunter.fragments.SearchFormFragment;
import com.example.alex.headhunter.fragments.SearchResultsFragment;
import com.example.alex.headhunter.utils.ThemeUtils;

public class MainActivity extends NetBaseActivity implements SearchFormFragment.SearchButtonCallback, SearchResultsFragment.VacancySelectCallback {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
//    TODO: check if savedInstanceState != null => do not recreate fragments
    private final Fragment mSearchFormFragment = new SearchFormFragment();
    private final Fragment mSearchResultsFragment = new SearchResultsFragment();
    private final Fragment mAboutFragment = new AboutFragment();
    private final Fragment mPrefFragment = new PrefsFragment();

    private final Uri CONTENT_URI = Uri.parse("content://com.example.alex.headhunter.provider/search_result");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {};
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

//        ContentValues contentValues = new ContentValues();
//        contentValues.put(SearchResultContract.SearchResultEntry.COLUMN_NAME_VACANCY_ID, 123);
//        contentValues.put(SearchResultContract.SearchResultEntry.COLUMN_NAME_NAME, "Программист хаха");
//        contentValues.put(SearchResultContract.SearchResultEntry.COLUMN_NAME_EMPLOYER_NAME, "ООО пшд");
//
//        getContentResolver().insert(CONTENT_URI, contentValues);


        getFragmentManager().beginTransaction()
                .replace(R.id.main_content_frame, mSearchFormFragment)
                .commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                mDrawerLayout.closeDrawers();

                if (!item.isChecked()) {
                    switch (item.getItemId()) {
                        case R.id.nav_search:
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.main_content_frame, mSearchFormFragment)
                                    .commit();
                            break;

                        case R.id.nav_results:
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.main_content_frame, mSearchResultsFragment)
                                    .commit();
                            break;
                        case R.id.nav_manage:
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.main_content_frame, mPrefFragment)
                                    .commit();
                            break;
                        case R.id.nav_about:
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.main_content_frame, mAboutFragment)
                                    .commit();
                            break;
                    }
                }

                return true;
            }
        });

//        findViewById(R.id.nav_search).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.main_content_frame, mSearchFormFragment)
//                        .commit();
//            }
//        });
//
//        findViewById(R.id.nav_results).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.main_content_frame, mSearchResultsFragment)
//                        .commit();
//            }
//        });
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
    public void onVacancySelect() {

    }

    @Override
    public void onServiceCallback(int requestId, int resultCode, Bundle data) {

    }
}
