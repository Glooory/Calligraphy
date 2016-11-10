package com.glooory.calligraphy.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.glooory.calligraphy.R;
import com.glooory.calligraphy.constants.Constants;
import com.glooory.calligraphy.utils.NetworkUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    @BindView(R.id.italy_card_cover)
    ImageView mItalyImageView;
    @BindView(R.id.italic_cover_text)
    TextView mItalicCoverTextView;
    @BindView(R.id.roundhand_card)
    ImageView mRoundhandImageView;
    @BindView(R.id.cards_container_1)
    LinearLayout mCardsContainerLL;
    @BindView(R.id.handprint_card_cover)
    ImageView mHandprintImageView;
    @BindView(R.id.copperplate_card_cover)
    ImageView mCopperplateImageView;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private CardView roundhandCard;
    private CardView italyCard;
    private CardView handprintedCard;
    private CardView copperplateCard;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar();
        setupDrawer();
        initViews();
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setupDrawer() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initViews() {
        roundhandCard = (CardView) findViewById(R.id.card_roundhand);
        italyCard = (CardView) findViewById(R.id.card_italy);
        handprintedCard = (CardView) findViewById(R.id.card_handprinted);
        copperplateCard = (CardView) findViewById(R.id.card_copperplate);
        roundhandCard.setOnClickListener(this);
        italyCard.setOnClickListener(this);
        handprintedCard.setOnClickListener(this);
        copperplateCard.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
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
            Intent intentCalli = new Intent(MainActivity.this, UniversalActivity.class);
            intentCalli.putExtra(Constants.FRAGMENT_INDEX, Constants.CALLIFRAG);
            lanuchActivity(intentCalli);
        } else if (id == R.id.nav_other_font) {
            Intent intentOther = new Intent(MainActivity.this, UniversalActivity.class);
            intentOther.putExtra(Constants.FRAGMENT_INDEX, Constants.OTHERFRAG);
            lanuchActivity(intentOther);
        } else if (id == R.id.nav_flourishing) {
            Intent intentFlouri = new Intent(MainActivity.this, UniversalActivity.class);
            intentFlouri.putExtra(Constants.FRAGMENT_INDEX, Constants.FLOURIFRAG);
            lanuchActivity(intentFlouri);
        } else if (id == R.id.nav_works) {
            if (!NetworkUtil.isOnline(getApplicationContext())) {
                Toast.makeText(this, "网络不可用，请检查后重试。", Toast.LENGTH_SHORT).show();
            } else {
                startWorksActivity();
            }
            return true;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_italy:
                Intent intentItalic = new Intent(MainActivity.this, FontActivity.class);
                intentItalic.putExtra(Constants.ACTIVITY_INDEX, Constants.ACTIVITY_ITALIC_INDEX);
                lanuchActivity(intentItalic);
                break;
            case R.id.card_roundhand:
                Intent intentRoundhand = new Intent(MainActivity.this, FontActivity.class);
                intentRoundhand.putExtra(Constants.ACTIVITY_INDEX, Constants.ACTIVITY_ROUNDHAND_INDEX);
                lanuchActivity(intentRoundhand);
                break;
            case R.id.card_handprinted:
                Intent intentHandprinted = new Intent(MainActivity.this, FontActivity.class);
                intentHandprinted.putExtra(Constants.ACTIVITY_INDEX, Constants.ACTIVITY_HANDPRINTED_INDEX);
                lanuchActivity(intentHandprinted);
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

    private void startWorksActivity() {
        WorksActivity.launch(MainActivity.this);
    }

    private void lanuchActivity(Intent intent) {
        if (Build.VERSION.SDK_INT >= 21) {
            startActivity(intent,
                    ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this).toBundle());
        } else {
            startActivity(intent);
        }
    }
}
