package com.example.collections_and_maps.ui.benchmark;


import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;



public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected static final String COLLECTIONS = "collections";
    protected String mCollections;

    protected BenchmarksAdapter adapter;
    protected final Handler mHandler;

    protected EditText collectionSize;
    protected int sizeArray;

    protected BaseFragment () {
        mHandler = new Handler();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mCollections = getArguments().getString(COLLECTIONS);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_benchmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        collectionSize = view.findViewById(R.id.collectionSize);
    }

    protected RecyclerView getRecycler() {
        RecyclerView listRecycler = getView().findViewById(R.id.recyclerLayoutItems);
        if (listRecycler.getItemDecorationCount() < 1) {
            listRecycler.addItemDecoration(new BenchmarksItemDecoration());
        }
        listRecycler.setHasFixedSize(true);
        return listRecycler;
    }

    public void getResults() {

        // get data from EditText
        if (collectionSize.length() > 0 && TextUtils.isDigitsOnly(collectionSize.getText())) {
            sizeArray = Integer.parseInt(collectionSize.getText().toString());
        } else {
            Toast.makeText(getContext(), R.string.ToastsText, Toast.LENGTH_LONG).show();
        }
    }
}
