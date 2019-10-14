package com.dwiyan.tvandmoviecatalogue;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.dwiyan.tvandmoviecatalogue.FavouriteFragment.FavouriteActivity;
import com.dwiyan.tvandmoviecatalogue.MovieCatalog.MovieResponse;

import com.dwiyan.tvandmoviecatalogue.RoomDB.AppDatabase;
import com.dwiyan.tvandmoviecatalogue.RoomDB.MovieDataDBDao;
import com.dwiyan.tvandmoviecatalogue.SearchMovie.ActivityMovieSearch;

import com.dwiyan.tvandmoviecatalogue.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import static com.dwiyan.tvandmoviecatalogue.R.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private MovieResponse ai;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
         toolbar = findViewById(id.toolbar);
        TabLayout tabs = findViewById(id.tabs);
        tabs.setupWithViewPager(viewPager);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(id.fab);


 toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer = (DrawerLayout) findViewById(id.drawer_layout);
        drawer.addDrawerListener(toggle);

        navigationView = findViewById(id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FavouriteActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == id.action_change_settings) {
            Intent mIntent = new Intent(MainActivity.this,SettingActivity.class);
            startActivity(mIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    protected void onPause() {
        super.onPause();
        drawer.removeDrawerListener(toggle);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if(id == R.id.nav_movie){
          Intent intent = new Intent(MainActivity.this, ActivityMovieSearch.class);
          startActivity(intent);
        } else if (id == R.id.nav_tv) {

            Toast.makeText(this,"Search tv Ditekan",Toast.LENGTH_LONG).show();
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}