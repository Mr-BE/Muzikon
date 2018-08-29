package com.kimandclak.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

public class SongsFragment extends Fragment{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    SongObject[] myDataset;
    boolean isPlaying;

    public SongsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = rootView.findViewById(R.id.song_recycler);

        myDataset = new SongObject[] {new SongObject("Survival", "Drake","Scorpion",
                R.drawable.album1), new SongObject("God's Plan", "Drake", "Scorpion", R.drawable.drake_gods_plan)};

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(Objects.requireNonNull(getActivity()), LinearLayoutManager.VERTICAL, 16));

        // specify an adapter (see also next example)
        mAdapter = new MyRecyclerViewAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
        // row click listener
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                SongObject song = myDataset[position];

                //Setting Song thumbnail on bottom bar
                AppCompatImageView songImg = getActivity().findViewById(R.id.bottom_bar_thumbnail);
                songImg.setImageResource(song.getmImageId());

                //Setting main info text
                AppCompatTextView title = getActivity().findViewById(R.id.bottom_bar_main_text);
                title.setText(song.getmTitle());
                title.setOnClickListener(e -> {
                    Intent i = new Intent(getActivity(), NowPlaying.class);
                    i.putExtra("Song", song);
                    i.putExtra("isPlaying", isPlaying);
                    startActivity(i);
                });

                //Setting sub info text
                AppCompatTextView otherInfo = getActivity().findViewById(R.id.bottom_bar_sub_text);
                otherInfo.setText(song.getmArtist() + "-" + song.getmAlbum());

                //Show Bottom app bar
                BottomAppBar bottomAppBar = getActivity().findViewById(R.id.bottom_bar);
                bottomAppBar.setVisibility(View.VISIBLE);

                isPlaying = true;

                AppCompatImageView bottomPlay = getActivity().findViewById(R.id.bottom_bar_play);
                bottomPlay.setOnClickListener(e -> {
                    if (isPlaying) {
                        bottomPlay.setImageResource(R.drawable.round_play_arrow_white_48dp);
                        isPlaying = false;
                    } else {
                        bottomPlay.setImageResource(R.drawable.pause_white);
                        isPlaying = true;
                    }
                });
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return rootView;
    }



}
