package com.glooory.calligraphy.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.glooory.calligraphy.constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.fragments.WorksFragment;

/**
 * Created by Glooo on 2016/5/15 0015.
 */
public class WorksActivity extends BaseActivity {
    public static final String FIRST_TIME = "first_time";
    private Toolbar mToolbar;
    private int index;
    private Fragment fragment = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_works);

        index = getIntent().getIntExtra(Constants.WORKS_INDEX, Constants.WORKS_NORMAL_INDEX);
        setupToolbar();
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_gridview_container, getFragment(index)).commit();
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.activity_gridview_app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
                                .replace(R.id.activity_gridview_container, getFragment(Constants.WORKS_NORMAL_INDEX)).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_gridview_container, getFragment(Constants.WORKS_FLOURISHING_INDEX)).commit();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private Fragment getFragment(int index) {
        fragment = WorksFragment.newInstance(index);
        return fragment;
    }
}
