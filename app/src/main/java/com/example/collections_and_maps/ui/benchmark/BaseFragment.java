package com.example.collections_and_maps.ui.benchmark;


import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.logger.Logger;


public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected static final String COLLECTIONS = "collections";
    protected String mCollections;

    public final BenchmarksAdapter adapter = new BenchmarksAdapter();
    public final Handler mHandler = new Handler();

    protected EditText collectionSize;
    protected int sizeArray;




//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if (getArguments() != null) {
//            mCollections = getArguments().getString(COLLECTIONS);
//        }
//    }


    public BaseFragment() {
        Logger.log("Super", this.getClass(), Thread.currentThread().getStackTrace()[2]);
//        adapter = new BenchmarksAdapter().setList();/
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        return inflater.inflate(R.layout.fragment_benchmark, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Logger.log("Super", this.getClass(), Thread.currentThread().getStackTrace()[2]);

        collectionSize = view.findViewById(R.id.collectionSize);
        Button calcButton = view.findViewById(R.id.calcButton);
        calcButton.setOnClickListener(this);


    }

    protected RecyclerView getRecycler() {
        Logger.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        RecyclerView listRecycler = getView().findViewById(R.id.recyclerLayoutItems);
        if (listRecycler.getItemDecorationCount() < 1) {
            listRecycler.addItemDecoration(new BenchmarksItemDecoration());
        }
        listRecycler.setHasFixedSize(true);
        return listRecycler;
    }

    public void getResults() {
        Logger.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);


        // get data from EditText
        if (collectionSize.length() > 0 && TextUtils.isDigitsOnly(collectionSize.getText())) {
            sizeArray = Integer.parseInt(collectionSize.getText().toString());
        } else {
            Toast.makeText(getContext(), R.string.ToastsText, Toast.LENGTH_LONG).show();
        }
    }




//    public static CollectionsPagerFragment newInstance(String param1) {
//        CollectionsPagerFragment fragment = new CollectionsPagerFragment();
//        Bundle args = new Bundle();
//        args.putString(COLLECTIONS, param1);
//        fragment.setArguments(args);
//        return fragment;
//    }
}
