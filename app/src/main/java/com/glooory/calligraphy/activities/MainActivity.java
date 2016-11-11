package com.glooory.calligraphy.activities;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.glooory.calligraphy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Glooory on 2016/5/9 0015.
 */
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.card_italy)
    CardView mItalyCard;
    @BindView(R.id.card_roundhand)
    CardView mRoundhandCard;
    @BindView(R.id.card_handprinted)
    CardView mHandprintedCard;
    @BindView(R.id.card_copperplate)
    CardView mCopperplateCard;
    @BindView(R.id.toolbar_main)
    Toolbar mToolbar;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavView.setNavigationItemSelectedListener(this);

        mRoundhandCard.setOnClickListener(this);
        mItalyCard.setOnClickListener(this);
        mHandprintedCard.setOnClickListener(this);
        mCopperplateCard.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                if (System.currentTimeMillis() - exitTime > 2000) {
                    Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    if (Build.VERSION.SDK_INT >= 21) {
                        finishAfterTransition();
                    } else {
                        finish();
                    }
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_calligraphy) {
            UniversalActivity.launch(MainActivity.this, UniversalActivity.CONTENT_CALLIGRAPHY);
        } else if (id == R.id.nav_other_font) {
            UniversalActivity.launch(MainActivity.this, UniversalActivity.CONTENT_OTHER_FONTS);
        } else if (id == R.id.nav_flourishing) {
            UniversalActivity.launch(MainActivity.this, UniversalActivity.CONTENT_FLOURISHING);
        } else if (id == R.id.nav_works) {
            WorksActivity.launch(MainActivity.this);
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_italy:
                launchFontActivity(FontActivity.FONT_ITALIC);
                break;
            case R.id.card_roundhand:
                launchFontActivity(FontActivity.FONT_ROUND_HAND);
                break;
            case R.id.card_handprinted:
                launchFontActivity(FontActivity.FONT_HAND_PRINTED);
                break;
            case R.id.card_copperplate:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Sorry").setMessage("Copperplate is coming soon...")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }
    }

    private void launchFontActivity(int fontType) {
        FontActivity.launch(MainActivity.this, fontType);
    }
}
