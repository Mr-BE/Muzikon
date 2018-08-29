package com.kimandclak.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

    public SongsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = rootView.findViewById(R.id.song_recycler);

        myDataset = new SongObject[] {new SongObject("Survival", "Drake","Scorpion",
                R.drawable.drake_sp ), new SongObject("God's Plan", "Drake","Scorpion", R.drawable.drake_gods_plan)} ;

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
                SongObject movie = myDataset[position];
                Toast.makeText(getContext(), movie.getmTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), NowPlaying.class);
                i.putExtra("Song", movie);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return rootView;
    }



}
