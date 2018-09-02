package com.kimandclak.musicapp.dummy;

import com.kimandclak.musicapp.Album;
import com.kimandclak.musicapp.R;
import com.kimandclak.musicapp.SongObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample album content
 */
public class DummyContent {

    public DummyContent() {

    }

    public static List<Album> getData() {

        List<Album> albums = new ArrayList<>();
        List<SongObject> songs = new ArrayList<>();
        songs.add(new SongObject("Survival", "Drake", "Scorpion", R.drawable.drake_sp));
        songs.add(new SongObject("God's plan", "Drake", "Scorpion", R.drawable.drake_gods_plan));
        albums.add(new Album("Scorpion", songs, R.drawable.drake_scorpion_cover));
        songs = new ArrayList<>();
        songs.add(new SongObject("Rolling in the deep", "Adele", "Adele21", R.drawable.song91));
        songs.add(new SongObject("Set fire to the rain", "Adele", "Adele21", R.drawable.song92));
        albums.add(new Album("Adele21", songs, R.drawable.album9));
        songs = new ArrayList<>();
        songs.add(new SongObject("Song of freedom", "Bob Marley and the Wailers", "The Best of Bob Marley and the Wailers"
                , R.drawable.song81));
        albums.add(new Album("The Best of Bob Marley and the Wailers", songs, R.drawable.album8));
        songs = new ArrayList<>();
        songs.add(new SongObject("What's my name", "Rihanna", "Loud", R.drawable.song71));
        songs.add(new SongObject("Fade away", "Rihanna", "Loud", R.drawable.song72));
        albums.add(new Album("Loud", songs, R.drawable.album7));
        songs = new ArrayList<>();
        songs.add(new SongObject("A place with no name", "Michael Jackson", "Xscape", R.drawable.song21));
        albums.add(new Album("Xscape", songs, R.drawable.album2));
        songs = new ArrayList<>();
        songs.add(new SongObject("What I like", "Charli XCX", "True Romance", R.drawable.song11));
        albums.add(new Album("True Romance", songs, R.drawable.album1));


        return albums;
    }
}
