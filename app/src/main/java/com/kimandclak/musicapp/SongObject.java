package com.kimandclak.musicapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A song track sang by an artist
 */
public class SongObject implements Parcelable {

    private String mTitle;
    private String mArtist;
    private String mAlbum;
    private int mImageId;

    public SongObject(String title, String artist, String album, int imageId){
        mTitle = title;
        mArtist = artist;
        mAlbum = album;
        mImageId = imageId;
    }

    protected SongObject(Parcel in) {
        mTitle = in.readString();
        mArtist = in.readString();
        mAlbum = in.readString();
        mImageId = in.readInt();
    }

    public static final Creator<SongObject> CREATOR = new Creator<SongObject>() {
        @Override
        public SongObject createFromParcel(Parcel in) {
            return new SongObject(in);
        }

        @Override
        public SongObject[] newArray(int size) {
            return new SongObject[size];
        }
    };

    public String getmTitle() {
        return mTitle;
    }

    public String getmArtist() {
        return mArtist;
    }

    public String getmAlbum() {
        return mAlbum;
    }

    public int getmImageId() {
        return mImageId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mArtist);
        parcel.writeString(mAlbum);
        parcel.writeInt(mImageId);
    }
}
