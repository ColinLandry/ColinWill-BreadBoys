package com.breadboys.willcolin;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.breadboys.willcolin.JavaBeans.Loaf;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                    CheckoutFragment.OnFragmentInteractionListener,
                    LoavesFragment.OnFragmentInteractionListener,
                    RecipeFragment.OnFragmentInteractionListener,
                    SettingsFragment.OnFragmentInteractionListener,
                    ContactFragment.OnFragmentInteractionListener,
                    MainFragment.OnFragmentInteractionListener,
                    RecipePageFragment.OnFragmentInteractionListener{

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fm = getSupportFragmentManager();
        //if this is the first time we run the app
        if(savedInstanceState == null){
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.content, new MainFragment());
            transaction.commit();
        }

        //initialize inventory
        Loaf.initializeInventory();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.setCustomAnimations(R.anim.slide_down_in, R.anim.slide_down_out, R.anim.slide_up_in, R.anim.slide_up_out);
            transaction.replace(R.id.content, new SettingsFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.slide_back_in, R.anim.slide_back_out);

        if (id == R.id.nav_loaves) {
            transaction.replace(R.id.content, new LoavesFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_recipe) {
            transaction.replace(R.id.content, new RecipeFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_checkout) {
            transaction.replace(R.id.content, new CheckoutFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_contact) {
            transaction.replace(R.id.content, new ContactFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_main) {
            transaction.replace(R.id.content, new MainFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onFragmentInteraction(Uri uri) {

    }
}
