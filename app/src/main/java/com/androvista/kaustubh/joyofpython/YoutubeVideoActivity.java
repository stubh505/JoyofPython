package com.androvista.kaustubh.joyofpython;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeVideoActivity extends YouTubeBaseActivity {

    private final String API_KEY = "AIzaSyCO7XYI8mEHwdS8cwDk5ZNNeA61I-IgXB4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_video);

        Intent i = getIntent();
        final String code = i.getStringExtra("code");
        String title = i.getStringExtra("title");
        setTitle(title);

        TextView tv = findViewById(R.id.youtube_player_text);
        tv.setText(title);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        YouTubePlayer.OnInitializedListener onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(code);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(YoutubeVideoActivity.this, "Video could not be played", Toast.LENGTH_SHORT).show();
            }
        };

        youTubePlayerView.initialize(API_KEY, onInitializedListener);
    }
}
