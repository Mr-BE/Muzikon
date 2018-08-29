package com.kimandclak.musicapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //get Album object from intent
        Album currentAlbum = getIntent().getParcelableExtra("Album");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.info_recycler);

        AppCompatImageView imageView = findViewById(R.id.info_img);
        imageView.setImageResource(currentAlbum.getThumbnail());

        AppCompatTextView title = findViewById(R.id.info_album_title);
        title.setText(currentAlbum.getTitle());

        AppCompatTextView artist = findViewById(R.id.info_artist);
        artist.setText(currentAlbum.getArtist());

        AppCompatTextView year = findViewById(R.id.info_year);
        year.setText("2018");


        SongObject[] myDataset = currentAlbum.getSongs();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));

        // specify an adapter (see also next example)
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(myDataset);
        recyclerView.setAdapter(adapter);
    }

}
