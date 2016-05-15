package com.glooory.calligraphy.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
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

import com.glooory.calligraphy.R;
import com.glooory.calligraphy.Utils.Constants;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private CardView roundhandCard;
    private CardView italyCard;
    private CardView handprintedCard;
    private CardView copperplateCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        if (id == R.id.action_settings) {
            return true;
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
            intentCalli.putExtra(Constants.FRAGPARAM, 0);
            startActivity(intentCalli);
        } else if (id == R.id.nav_other_font) {
            Intent intentOther = new Intent(MainActivity.this, UniversalActivity.class);
            intentOther.putExtra(Constants.FRAGPARAM, 1);
            startActivity(intentOther);
        } else if (id == R.id.nav_flourishing) {
            Intent intentFlouri = new Intent(MainActivity.this, UniversalActivity.class);
            intentFlouri.putExtra(Constants.FRAGPARAM, 2);
            startActivity(intentFlouri);
        } else if (id == R.id.nav_more_piece) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_italy:
                startActivity(new Intent(MainActivity.this, ItalicActivity.class));
                break;
            case R.id.card_roundhand:
                startActivity(new Intent(MainActivity.this, RoundHandActivity.class));
                break;
            case R.id.card_handprinted:
                startActivity(new Intent(MainActivity.this, HandprintedActivity.class));
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
