package com.example.collections_and_maps.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.collections_and_maps.RecyclerSizeLookup;
import com.example.collections_and_maps.R;
import com.example.collections_and_maps.adapters.ItemsAdapter;
import com.example.collections_and_maps.calculations.MyArrayList;
import com.example.collections_and_maps.calculations.MyCopyOnWriteArrayList;
import com.example.collections_and_maps.calculations.MyLinkedList;


public class CollectionsPagerFragment extends MyFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            Resources res = this.requireActivity().getResources();
            listArr = res.getStringArray(R.array.collections);
            list = res.getStringArray(R.array.collections_item);
            spanCount = listArr.length;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pager_fragment, container, false);
    }

    public static CollectionsPagerFragment newInstance(String param1) {
        CollectionsPagerFragment fragment = new CollectionsPagerFragment();
        Bundle args = new Bundle();
        args.putString(COLLECTIONS, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button calcButton = view.findViewById(R.id.calcButton);
        calcButton.setOnClickListener(this);

        // making list recycler
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),
                spanCount, LinearLayoutManager.VERTICAL, false);
        //set SpanSizeLookup()
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(4, 1, spanCount));
        getRecycler().setLayoutManager(gridLayoutManager);
        createClearGrid();

        adapter = new ItemsAdapter();
        adapter.submitList(baseList);
        getRecycler().setAdapter(adapter);
    }

    @Override
    public void fillRecycler() {
        super.fillRecycler();
        // button was pushed, next we are initialisation all views
        for (int s = 0; s < baseList.size(); s++) {
            String nameLine = baseList.get(s).toString();
            baseList.set(++s, new MyArrayList(k, nameLine).getResult());
            baseList.set(++s, new MyLinkedList(k, nameLine).getResult());
            baseList.set(++s, new MyCopyOnWriteArrayList(k, nameLine).getResult());
        }

        adapter = new ItemsAdapter();
        adapter.submitList(baseList);
        getRecycler().setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        fillRecycler();
    }
}