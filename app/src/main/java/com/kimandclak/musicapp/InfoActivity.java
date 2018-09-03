package com.kimandclak.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;
import java.util.Objects;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //get Album object from intent
        Album currentAlbum = getIntent().getParcelableExtra("Album");

//

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.info_recycler);

        AppCompatImageView imageView = findViewById(R.id.info_img);
        imageView.setImageResource(currentAlbum.getThumbnail());

        AppCompatTextView title = findViewById(R.id.info_album_title);
        title.setText(currentAlbum.getTitle());

        AppCompatTextView artist = findViewById(R.id.info_artist);
        artist.setText(currentAlbum.getArtist());

        AppCompatTextView year = findViewById(R.id.info_year);
        year.setText("2018");


        List<SongObject> myDataset = currentAlbum.getSongs();

        // Improve performance because changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));

        // specify an adapter
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(myDataset);
        recyclerView.setAdapter(adapter);
        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(InfoActivity.this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                SongObject song = myDataset.get(position);

                MainActivity.setSong(song);

                //Set details on main activity bottom bar;
                MainActivity.setSongDetails(song);

                if (MainActivity.getBottomAppBar().getVisibility() != View.VISIBLE)
                    MainActivity.getBottomAppBar().setVisibility(View.VISIBLE);

                //Open Now playing activity
                Intent i = new Intent(InfoActivity.this, NowPlaying.class);
                i.putExtra("Song", song);
                i.putExtra("isPlaying", true);
                startActivity(i);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


}
