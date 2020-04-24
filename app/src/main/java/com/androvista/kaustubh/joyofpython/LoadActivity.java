package com.androvista.kaustubh.joyofpython;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadActivity extends AppCompatActivity {

    private AlertDialog dialog;
    static JSONArray jsonArray;
    static String dir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        dir = getFilesDir().toString();
        File f = new File(dir+"/JOC", "AllVideos.dat");

        final ImageView iv = findViewById(R.id.python_load_image);
        final TextView tv = findViewById(R.id.load_text_view);

        CountDownTimer timer = new CountDownTimer(4100, 2000) {
            @Override
            public void onTick(long l) {
                iv.animate().scaleX(0.75f).scaleY(0.75f).translationY(-200f).setDuration(2000);
                tv.animate().alpha(1).setDuration(1000);
            }

            @Override
            public void onFinish() {
                Intent i = new Intent(LoadActivity.this, MainActivity.class);
                startActivity(i);
            }
        };

        if(!f.exists()) {

            dialog = new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.progress_horizontal)
                    .setTitle("Downloading data")
                    .setMessage("Please Wait...")
                    .setCancelable(false)
                    .show();

            getData();
        } else {
            timer.start();
        }
    }

    private class GetData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... apis) {
            try {
                URL url = new URL(apis[0]);
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream=connection.getInputStream();
                InputStreamReader ips=new InputStreamReader(inputStream);
                StringBuilder res=new StringBuilder();
                int data=ips.read();
                while (data != -1) {
                    res.append((char)data);
                    data=ips.read();
                }
                String result = res.toString();
                jsonArray = new JSONArray(result);
                new JSONParserWriter().parseJSON();
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            Intent i = new Intent(LoadActivity.this, MainActivity.class);
            startActivity(i);
        }
    }

    private void getData() {
        try {
            GetData data = new GetData();
            data.execute("https://api.myjson.com/bins/12f7h0");
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
