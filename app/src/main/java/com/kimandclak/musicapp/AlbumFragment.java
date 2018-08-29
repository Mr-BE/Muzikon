package com.kimandclak.musicapp;

import android.content.Context;
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
import android.widget.Toast;

import com.kimandclak.musicapp.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class AlbumFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private OnListFragmentInteractionListener mListener;
    List<Album> l;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AlbumFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AlbumFragment newInstance(int columnCount) {
        AlbumFragment fragment = new AlbumFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.fragment_album_list, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.album_grid);

        // Set the adapter
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), mColumnCount));

        l = new ArrayList<>();
        SongObject[] myDataset = new SongObject[] {new SongObject("Survival", "Drake","Scorpion",
                R.drawable.drake_sp ), new SongObject("God's Plan", "Drake","Scorpion", R.drawable.drake_gods_plan)} ;
        l.add(new Album("Scorpion", myDataset, R.drawable.drake_scorpion_cover));

        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(new AlbumsAdapter(l));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Album movie = l.get(position);
                Toast.makeText(getContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), InfoActivity.class);
                i.putExtra("Album", movie);
                startActivity(i);



            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        return rootView;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }

    public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

        private Context mContext;
        private List<Album> albumList;

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            Album album = albumList.get(position);
            holder.title.setText(album.getTitle());
            holder.count.setText(album.getNumOfSongs() + getString(R.string.songs));
            holder.thumbnail.setImageResource(album.getThumbnail());
        }


        public AlbumsAdapter(List<Album> albumList) {
            this.albumList = albumList;
        }

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

            public MyViewHolder(View view) {
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

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
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
