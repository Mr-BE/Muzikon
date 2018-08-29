package com.kimandclak.musicapp;

import android.os.Parcel;
import android.os.Parcelable;


public class Album implements Parcelable {
    private String mTitle;
    private String mArtist;
    private SongObject[] mSongs;
    private int mThumbnail;

    public Album(String title, SongObject[] songs, int imageResId){
        mTitle = title;
        mSongs = songs;
        mThumbnail = imageResId;
        for(int i =0; i < songs.length; i++){
            if(!songs[0].getmArtist().equals(songs[i].getmArtist())){
                mArtist = "Various Artist";
            }
            else{
                mArtist = songs[0].getmArtist();
            }
        }
    }


    protected Album(Parcel in) {
        mTitle = in.readString();
        mArtist = in.readString();
        mSongs = in.createTypedArray(SongObject.CREATOR);
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

    public String getmTitle() {
        return mTitle;
    }

    public SongObject[] getmSongs() {
        return mSongs;
    }

    public int getmThumbnail() {
        return mThumbnail;
    }

    public int getNumOfSongs(){
        return mSongs.length;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mArtist);
        parcel.writeTypedArray(mSongs, i);
        parcel.writeInt(mThumbnail);
    }
}
