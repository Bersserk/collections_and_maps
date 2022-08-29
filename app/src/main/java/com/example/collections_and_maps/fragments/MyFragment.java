package com.example.collections_and_maps.fragments;

import android.os.Bundle;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class MyFragment extends Fragment {
    protected static final String COLLECTIONS = "collections";
    protected static final String MAPS = "maps";

    protected String mCollections;
    protected String mMaps;

    protected RecyclerView headListRecycler;
    protected RecyclerView listRecycler;

    protected EditText collectionSize;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCollections = getArguments().getString(COLLECTIONS);
            mMaps = getArguments().getString(MAPS);
        }
    }
}
