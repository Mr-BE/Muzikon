package com.kimandclak.musicapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class NowPlaying extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SongObject songObject = getIntent().getParcelableExtra("Song");

        AppCompatImageView songImg = findViewById(R.id.song_img);
        songImg.setImageResource(songObject.getmImageId());

        AppCompatTextView title = findViewById(R.id.now_text1);
        title.setText(songObject.getmTitle());

        AppCompatTextView otherInfo = findViewById(R.id.now_text2);
        otherInfo.setText(songObject.getmArtist()+"-"+songObject.getmAlbum());

        AppCompatImageView play = findViewById(R.id.now_play);
        play.setOnClickListener(e->{
            play.setImageResource(R.drawable.pause_grey);
        });

    }

}
