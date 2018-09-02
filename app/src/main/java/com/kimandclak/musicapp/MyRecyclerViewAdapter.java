package com.kimandclak.musicapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * A RecyclerView adapter for RecyclerView
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<SongObject> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    MyRecyclerViewAdapter(List<SongObject> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                               int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_list_item, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView1.setText(mDataset.get(position).getmTitle());
        holder.mTextView2.setText(mDataset.get(position).getmArtist());
        holder.mImageView.setImageResource(mDataset.get(position).getmImageId());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView mTextView1;
        TextView mTextView2;
        ImageView mImageView;

        ViewHolder(View v) {
            super(v);
            mTextView1 = v.findViewById(R.id.song_title);
            mTextView2 = v.findViewById(R.id.song_album);
            mImageView = v.findViewById(R.id.song_thumbnail);
        }
    }
}
