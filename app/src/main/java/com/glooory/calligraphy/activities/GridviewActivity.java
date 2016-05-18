package com.glooory.calligraphy.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.glooory.calligraphy.Constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.fragments.FlourishingWorksFragment;
import com.glooory.calligraphy.fragments.MoreWorksFragment;

/**
 * Created by Glooo on 2016/5/15 0015.
 */
public class GridviewActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private String[] category;
    private int index;
    private Fragment fragment = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        index = getIntent().getIntExtra(Constants.FRAGMENT_INDEX, Constants.MOREWORKS);
        setupToolbar();
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_gridview_container, getFragment(index)).commit();
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.activity_gridview_app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        category = getResources().getStringArray(R.array.spiner_item);
        SpinnerAdapter spinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.spiner_item, R.layout.spiner_dropdown_item);
        Spinner spinner = new Spinner(getSupportActionBar().getThemedContext());
        spinner.setAdapter(spinnerAdapter);

        mToolbar.addView(spinner, 0);
        spinner.setSelection(index, true);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_gridview_container, getFragment(Constants.MOREWORKS)).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_gridview_container, getFragment(Constants.FLOURISHINGWORKS)).commit();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private Fragment getFragment(int index) {
        switch (index) {
            case Constants.MOREWORKS:
                fragment = new MoreWorksFragment();
                break;
            case Constants.FLOURISHINGWORKS:
                fragment = new FlourishingWorksFragment();
                break;
        }
        return fragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition();
                return true;
            } else {
                super.onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            super.onBackPressed();
        }
    }
}
