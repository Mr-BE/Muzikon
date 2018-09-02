package com.kimandclak.musicapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kimandclak.musicapp.dummy.DummyContent;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class AlbumFragment extends Fragment {


    int mColumnCount = 2;
    List<Album> albums;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AlbumFragment() {
    }

    public static AlbumFragment makeAlbumFragment() {
        AlbumFragment albumFragment = new AlbumFragment();
        albumFragment.albums = DummyContent.getData();

        return albumFragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.fragment_album_list, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.album_grid);

        // Set the adapter
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), mColumnCount));


        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(new AlbumsAdapter(albums));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Album album = albums.get(position);
                Intent i = new Intent(getActivity(), InfoActivity.class);
                i.putExtra("Album", album);
                startActivity(i);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        return rootView;
    }


    public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

        private List<Album> albumList;

        AlbumsAdapter(List<Album> albumList) {
            this.albumList = albumList;
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
            Album album = albumList.get(position);
            holder.title.setText(album.getTitle());
            holder.count.setText(album.getNumOfSongs() + getString(R.string.songs));
            holder.thumbnail.setImageResource(album.getThumbnail());
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.album_list_item, parent, false);

            return new MyViewHolder(itemView);
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title;
            public TextView count;
            public ImageView thumbnail;

            MyViewHolder(View view) {
                super(view);
                title = view.findViewById(R.id.title);
                count = view.findViewById(R.id.count);
                thumbnail = view.findViewById(R.id.thumbnail);
            }
        }


        @Override
        public int getItemCount() {
            return albumList.size();
        }
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
