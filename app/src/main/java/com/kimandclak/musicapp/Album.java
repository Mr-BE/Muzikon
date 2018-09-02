package com.kimandclak.musicapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * An Album is a collection of songs by one or more artists
 */
public class Album implements Parcelable {
    private String mTitle;
    private String mArtist;
    private List<SongObject> mSongs;
    private int mThumbnail;

    public Album(String title, List<SongObject> songs, int imageResId) {
        mTitle = title;
        mSongs = songs;
        mThumbnail = imageResId;
        for (int i = 0; i < songs.size(); i++) {
            if (!songs.get(0).getmArtist().equals(songs.get(i).getmArtist())) {
                mArtist = "Various Artist";
            } else {
                mArtist = songs.get(0).getmArtist();
            }
        }
    }


    protected Album(Parcel in) {
        mTitle = in.readString();
        mArtist = in.readString();
        mSongs = in.createTypedArrayList(SongObject.CREATOR);
        mThumbnail = in.readInt();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    public String getTitle() {
        return mTitle;
    }

    public String getArtist() {
        return mArtist;
    }

    public List<SongObject> getSongs() {
        return mSongs;
    }

    public int getThumbnail() {
        return mThumbnail;
    }

    public int getNumOfSongs(){
        return mSongs.size();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mArtist);
        parcel.writeTypedList(mSongs);
        parcel.writeInt(mThumbnail);
    }
}
