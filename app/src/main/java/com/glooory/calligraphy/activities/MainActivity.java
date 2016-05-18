package com.glooory.calligraphy.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.glooory.calligraphy.Constants.Constants;
import com.glooory.calligraphy.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String DECLARATION = "declaration";
    private Toolbar mToolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private boolean showDeclaration;
    private CardView roundhandCard;
    private CardView italyCard;
    private CardView handprintedCard;
    private CardView copperplateCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        setupDrawer();
        initViews();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        showDeclaration = sharedPreferences.getBoolean(DECLARATION, true);
        if (showDeclaration) {
            showDeclarationDialog();
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
            editor.putBoolean(DECLARATION, false);
            editor.commit();
        }

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

    private void showDeclarationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("声明")
                .setMessage(getString(R.string.declaration))
                .setCancelable(false)
                .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (Build.VERSION.SDK_INT >= 21) {
                finishAfterTransition();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_declaration) {
            showDeclarationDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_calligraphy) {
            Intent intentCalli = new Intent(MainActivity.this, UniversalActivity.class);
            intentCalli.putExtra(Constants.FRAGMENT_INDEX, Constants.CALLIFRAG);
            if (Build.VERSION.SDK_INT >= 21) {
                startActivity(intentCalli, ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            } else {
                startActivity(intentCalli);
            }
        } else if (id == R.id.nav_other_font) {
            Intent intentOther = new Intent(MainActivity.this, UniversalActivity.class);
            intentOther.putExtra(Constants.FRAGMENT_INDEX, Constants.OTHERFRAG);
            if (Build.VERSION.SDK_INT >= 21) {
                startActivity(intentOther, ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            } else {
                startActivity(intentOther);
            }
        } else if (id == R.id.nav_flourishing) {
            Intent intentFlouri = new Intent(MainActivity.this, UniversalActivity.class);
            intentFlouri.putExtra(Constants.FRAGMENT_INDEX, Constants.FLOURIFRAG);
            if (Build.VERSION.SDK_INT >= 21) {
                startActivity(intentFlouri, ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            } else {
                startActivity(intentFlouri);
            }
        } else if (id == R.id.nav_more_piece) {
            Intent intentMoreWorks = new Intent(MainActivity.this, GridviewActivity.class);
            intentMoreWorks.putExtra(Constants.FRAGMENT_INDEX, Constants.MOREWORKS);
            if (Build.VERSION.SDK_INT >= 21) {
                startActivity(intentMoreWorks, ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            } else {
                startActivity(intentMoreWorks);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_italy:
                if (Build.VERSION.SDK_INT >= 21) {
                    startActivity(new Intent(MainActivity.this, ItalicActivity.class),
                            ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                } else {
                    startActivity(new Intent(MainActivity.this, ItalicActivity.class));
                }
                break;
            case R.id.card_roundhand:
                if (Build.VERSION.SDK_INT >= 21) {
                    startActivity(new Intent(MainActivity.this, RoundHandActivity.class),
                            ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                } else {
                    startActivity(new Intent(MainActivity.this, RoundHandActivity.class));
                }
                break;
            case R.id.card_handprinted:
                if (Build.VERSION.SDK_INT >= 21) {
                    startActivity(new Intent(MainActivity.this, HandprintedActivity.class),
                            ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                } else {
                    startActivity(new Intent(MainActivity.this, HandprintedActivity.class));
                }
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
}
