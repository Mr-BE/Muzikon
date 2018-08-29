package com.kimandclak.musicapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;

public class NowPlaying extends AppCompatActivity {
    boolean isPlaying;
    boolean isRepeating;
    boolean isFavourite;
    boolean isShuffling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SongObject songObject = getIntent().getParcelableExtra("Song");
        isPlaying = getIntent().getBooleanExtra("isPlaying", false);

        //Song Art
        AppCompatImageView songImg = findViewById(R.id.song_img);
        songImg.setImageResource(songObject.getmImageId());

        //Main info text
        AppCompatTextView title = findViewById(R.id.now_text1);
        title.setText(songObject.getmTitle());

        //Sub info text
        AppCompatTextView otherInfo = findViewById(R.id.now_text2);
        otherInfo.setText(songObject.getmArtist()+"-"+songObject.getmAlbum());

        //Play Button
        AppCompatImageView play = findViewById(R.id.now_play);
        play.setOnClickListener(e->{
            if (isPlaying) {
                play.setImageResource(R.drawable.ic_play_arrow_24px);
                isPlaying = false;
            } else {
                play.setImageResource(R.drawable.pause_grey);
                isPlaying = true;
            }
        });

        //Repeat Button
        AppCompatImageView repeat = findViewById(R.id.repeat);
        isRepeating = false;
        repeat.setOnClickListener(e -> {
            if (isRepeating) {
                repeat.setImageResource(R.drawable.repeat_white);
                isRepeating = false;
            } else {
                repeat.setImageResource(R.drawable.repeat_pink);
                isRepeating = true;
            }
        });

        //Shuffle Button
        AppCompatImageView shuffle = findViewById(R.id.shuffle);
        isShuffling = false;
        shuffle.setOnClickListener(e -> {
            if (isShuffling) {
                shuffle.setImageResource(R.drawable.shuffle_white);
                isShuffling = false;
            } else {
                shuffle.setImageResource(R.drawable.shuffle_pink);
                isShuffling = true;
            }
        });

        //Favourite Button
        AppCompatImageView favourite = findViewById(R.id.favorite);
        isFavourite = false;
        favourite.setOnClickListener(e -> {
            if (isFavourite) {
                favourite.setImageResource(R.drawable.heart_white);
                isFavourite = false;
            } else {
                favourite.setImageResource(R.drawable.heart_pink);
                isFavourite = true;
            }
        });

    }

}
