package com.kimandclak.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    private static boolean showBottomBar = false;
    private static BottomAppBar mBottomAppBar;
    private static LinearLayoutCompat mOpenNowPlay;
    private static AppCompatImageView mSongImg;
    private static AppCompatTextView mTitle;
    private static AppCompatTextView mOtherInfo;
    private static AppCompatImageView mBottomPlay;
    private static SongObject mSong;

    static boolean isPlaying;

    public static void setShowBottomBar(boolean value) {
        showBottomBar = value;
    }

    public static void setSongDetails(SongObject song) {
        //Setting Song thumbnail on bottom bar
        mSongImg.setImageResource(song.getmImageId());

        //Setting main info text
        mTitle.setText(song.getmTitle());

        //Setting sub info text
        mOtherInfo.setText(song.getmArtist() + "-" + song.getmAlbum());
    }

    public static LinearLayoutCompat getOpenNowPlay() {
        return mOpenNowPlay;
    }

    public static BottomAppBar getBottomAppBar() {
        return mBottomAppBar;
    }

    public static SongObject getSong() {
        return mSong;
    }

    public static void setSong(SongObject song) {
        mSong = song;
    }

    public static AppCompatImageView getBottomPlay() {
        return mBottomPlay;
    }

    public static boolean isPlaying() {
        return isPlaying;
    }


    public static void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Hide bottom app bar
        if (mBottomAppBar == null) {
            mBottomAppBar = findViewById(R.id.bottom_bar);
            mSongImg = findViewById(R.id.bottom_bar_thumbnail);
            mTitle = findViewById(R.id.bottom_bar_main_text);
            mOpenNowPlay = findViewById(R.id.open_now_play);
            mOpenNowPlay.setOnClickListener(e -> {
                Intent i = new Intent(this, NowPlaying.class);
                i.putExtra("Song", MainActivity.getSong());
                i.putExtra("isPlaying", isPlaying);
                startActivity(i);
            });

            mOtherInfo = findViewById(R.id.bottom_bar_sub_text);
            mBottomPlay = findViewById(R.id.bottom_bar_play);
            mBottomPlay.setOnClickListener(e -> {
                if (isPlaying) {
                    mBottomPlay.setImageResource(R.drawable.play_white);
                    isPlaying = false;
                } else {
                    mBottomPlay.setImageResource(R.drawable.pause_white);
                    isPlaying = true;
                }
            });

        }
        if (showBottomBar) {
            if (mSong != null)
                setSongDetails(mSong);

        } else
            mBottomAppBar.setVisibility(View.INVISIBLE);

        // Create the adapter that will return a fragment for each of the two
        // primary sections of the activity.
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setTabTextColors(0xFFA7A6A7, 0xFFFFFFFF);

        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new SongsFragment();
                case  1:
                    return new AlbumFragment();
                default:
                    return null;
            }
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0:
                    return getString(R.string.tab_text_1);
                case 1:
                    return getString(R.string.tab_text_2);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }
}
