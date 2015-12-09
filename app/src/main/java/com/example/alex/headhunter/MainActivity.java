package com.example.alex.headhunter;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private final Fragment mSearchFormFragment = new SearchFormFragment();
    private final Fragment mSearchResultsFragment = new SearchResultsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {};
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

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
}
