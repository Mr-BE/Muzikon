package com.kimandclak.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kimandclak.musicapp.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SongsFragment extends Fragment{
    List<SongObject> myDataset;
    private RecyclerView.Adapter mAdapter;
    SongObject song;
    /**
     * The fragment argument representing a list of songs.
     */
    private RecyclerView mRecyclerView;
    boolean isPlaying;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment.
     */
    public SongsFragment() {
    }

    public static SongsFragment makeSongsFragment() {
        SongsFragment songsFragment = new SongsFragment();
        songsFragment.myDataset = new ArrayList<>();
        List<Album> albums = DummyContent.getData();
        for (Album album : albums) {
            for (int i = 0; i < (album.getNumOfSongs()); i++) {
                songsFragment.myDataset.add(album.getSongs().get(i));
            }
        }
        return songsFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = rootView.findViewById(R.id.song_recycler);

        // improves performance since changes in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(Objects.requireNonNull(getActivity()), LinearLayoutManager.VERTICAL, 16));

        // specify an adapter
        mAdapter = new MyRecyclerViewAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
        // row click listener
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                song = myDataset.get(position);
                isPlaying = true;
                setupBottomAppBar(song);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return rootView;
    }

    /**
     * A helper function used for setting up the bottom app bar with the correct info.
     */
    private void setupBottomAppBar(SongObject song) {

        MainActivity.setSongDetails(song);
        MainActivity.setSong(song);

        //Setup Now playing opening from bottom app bar
        LinearLayoutCompat openNowPlay = MainActivity.getOpenNowPlay();
        openNowPlay.setOnClickListener(e -> {
            Intent i = new Intent(getActivity(), NowPlaying.class);
            i.putExtra("Song", MainActivity.getSong());
            i.putExtra("isPlaying", isPlaying);
            startActivity(i);
        });

        //Show Bottom app bar
        BottomAppBar bottomAppBar = MainActivity.getBottomAppBar();
        bottomAppBar.setVisibility(View.VISIBLE);
        MainActivity.setShowBottomBar(true);


        //Setting up play/pause button.
        AppCompatImageView bottomPlay = MainActivity.getBottomPlay();
        if (isPlaying) {
            bottomPlay.setImageResource(R.drawable.pause_white);
        } else {
            bottomPlay.setImageResource(R.drawable.play_white);
        }
        bottomPlay.setOnClickListener(e -> {
            if (isPlaying) {
                bottomPlay.setImageResource(R.drawable.play_white);
                isPlaying = false;
            } else {
                bottomPlay.setImageResource(R.drawable.pause_white);
                isPlaying = true;
            }
        });
    }

}
