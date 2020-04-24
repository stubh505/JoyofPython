package com.androvista.kaustubh.joyofpython;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class WeekActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<String> allTitles, allVideos, desc;
    private String week;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        allTitles = new ArrayList<>();
        allVideos = new ArrayList<>();
        desc = new ArrayList<>();

        week = getIntent().getStringExtra("week");

        setTitle(week);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        loadArrayLists();

        ListView lv = findViewById(R.id.week_list_view);
        CustomAdapter adapter = new CustomAdapter(allTitles, desc, getLayoutInflater(), R.drawable.list_video_icon, this);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(WeekActivity.this, YoutubeVideoActivity.class);
                intent.putExtra("code", allVideos.get(i));
                intent.putExtra("title", allTitles.get(i));
                startActivity(intent);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void loadArrayLists() {
        File f1 = new File(getFilesDir()+"/JOC/Week Wise/"+week, "AllVideos.dat");
        File f2 = new File(getFilesDir()+"/JOC/Week Wise/"+week, "AllVideoTitles.dat");
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(f1));
            BufferedReader br2 = new BufferedReader(new FileReader(f2));
            String s;
            while ((s = br1.readLine()) != null) {
                allVideos.add(s);
                allTitles.add(br2.readLine());
                desc.add("Youtube Video");
            }
        } catch (Exception e) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setMessage(R.string.data_corrupted_warn)
                    .setTitle(R.string.data_corrupted_title)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent in = getBaseContext().getPackageManager()
                                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(in);                        }
                    }).show();
            e.printStackTrace();
            CustomAdapter.deleteFiles(new File(LoadActivity.dir));
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
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
            Snackbar.make(findViewById(R.id.drawer_layout), R.string.why_settings, Snackbar.LENGTH_LONG)
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
            finish();
        } else if (id == R.id.nav_all_videos) {
            Intent i = new Intent(this, AllVideosActivity.class);
            finish();
            startActivity(i);
        } else if (id == R.id.nav_week_wise) {
            setResult(RESULT_OK);
            finish();
        } else if (id == R.id.nav_chapter_wise) {
            Intent i = new Intent(this, ChapterWiseActivity.class);
            finish();
            startActivity(i);
        } else if (id == R.id.nav_all_videos_pds) {
            Intent i = new Intent(this, AllVideosActivity.class);
            i.putExtra("type", "PDS");
            finish();
            startActivity(i);
        } else if (id == R.id.nav_week_wise_pds) {
            Intent i = new Intent(this, LinkActivity.class);
            finish();
            startActivity(i);
        } else if (id == R.id.nav_chapter_wise_pds) {
            Intent i = new Intent(this, ChapterWiseActivity.class);
            i.putExtra("type", "PDS");
            finish();
            startActivity(i);
        } else if (id == R.id.nav_about) {
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
}
