package com.androvista.kaustubh.joyofpython;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
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
            Snackbar.make(findViewById(R.id.drawer_layout), "Why do you want a settings in this app?", Snackbar.LENGTH_LONG)
                    .setAction("FML", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    }).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_all_videos) {
            Intent i = new Intent(this, AllVideosActivity.class);
            i.putExtra("type", "JOC");
            startActivity(i);
        } else if (id == R.id.nav_week_wise) {
            Intent i = new Intent(this, WeekWiseActivity.class);
            i.putExtra("type", "JOC");
            startActivity(i);
        } else if (id == R.id.nav_chapter_wise) {
            Intent i = new Intent(this, ChapterWiseActivity.class);
            i.putExtra("type", "JOC");
            finish();
            startActivity(i);
        } else if (id == R.id.nav_all_videos_pds) {
            Intent i = new Intent(this, AllVideosActivity.class);
            i.putExtra("type", "PDS");
            finish();
            startActivity(i);
        } else if (id == R.id.nav_week_wise_pds) {
            Intent i = new Intent(this, LinkActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_chapter_wise_pds) {
            Intent i = new Intent(this, ChapterWiseActivity.class);
            i.putExtra("type", "PDS");
            finish();
            startActivity(i);
        }else if (id == R.id.nav_about) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setMessage(R.string.about)
                    .setTitle(R.string.about_title)
                    .setCancelable(true)
                    .show();
        } else if (id == R.id.nav_share) {
            Snackbar.make(findViewById(R.id.drawer_layout), R.string.no_sharing, Snackbar.LENGTH_LONG)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    }).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openAll(View v) {
        Intent i = new Intent(this, AllVideosActivity.class);
        i.putExtra("type","JOC");
        startActivity(i);
    }

    public void openAllB(View v) {
        Intent i = new Intent(this, AllVideosActivity.class);
        i.putExtra("type","PDS");
        startActivity(i);
    }

    public void openWeek(View v) {
        Intent i = new Intent(this, WeekWiseActivity.class);
        startActivity(i);
    }

    public void openChapter(View v) {
        Intent i = new Intent(this, ChapterWiseActivity.class);
        i.putExtra("type","JOC");
        startActivity(i);
    }

    public void openChapterB(View v) {
        Intent i = new Intent(this, ChapterWiseActivity.class);
        i.putExtra("type","PDS");
        startActivity(i);
    }

    public void openLink(View v) {
        Intent i = new Intent(this, LinkActivity.class);
        startActivity(i);
    }

    public void openWeb(View v) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://onlinecourses.nptel.ac.in/m#/course/noc18_cs35"));
        startActivity(i);
    }

    public void openWebB(View v) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://onlinecourses.nptel.ac.in/m#/course/noc18_cs34"));
        startActivity(i);
    }

}
