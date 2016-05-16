package com.glooory.calligraphy.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.glooory.calligraphy.Constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.fragments.DetailImageFragment;
import com.glooory.calligraphy.fragments.MoreWorksFragment;

/**
 * Created by Glooo on 2016/5/15 0015.
 */
public class GridviewActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Fragment fragment = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        setupToolbar();
        int index = getIntent().getIntExtra(Constants.FRAGMENT_INDEX, Constants.MOREWORKS);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_gridview_container, getFragment(index)).commit();
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.activity_gridview_app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private Fragment getFragment(int index) {
        switch (index) {
            case Constants.MOREWORKS:
                fragment = new MoreWorksFragment();
                this.setTitle("作品欣赏");
                break;
            case Constants.DETAILIMAGE:
                fragment = new DetailImageFragment();
                Bundle args = new Bundle();
                args.putInt(Constants.IMAGE_POSITION, getIntent().getIntExtra(Constants.IMAGE_POSITION, 0));
                fragment.setArguments(args);
                getSupportActionBar().hide();
                break;
        }
        return fragment;
    }
}
