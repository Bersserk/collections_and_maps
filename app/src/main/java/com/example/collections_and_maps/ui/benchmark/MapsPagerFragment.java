package com.example.collections_and_maps.ui.benchmark;

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

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.MyHashMap;
import com.example.collections_and_maps.models.benchmarks.MyTreeMap;

public class MapsPagerFragment extends BaseFragment {

    public static MapsPagerFragment newInstance(String param1) {
        MapsPagerFragment fragment = new MapsPagerFragment();
        Bundle args = new Bundle();
        args.putString(COLLECTIONS, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = this.requireActivity().getResources();
        listNamesMainItem = res.getStringArray(R.array.maps);
        listNamesItem = res.getStringArray(R.array.maps_item);
        spanCount = listNamesMainItem.length;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_benchmark, container, false);
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
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(3, 1, spanCount));
        getRecycler().setLayoutManager(gridLayoutManager);
        createClearGrid();

        adapter.submitList(baseList);
        getRecycler().setAdapter(adapter);
    }

    public void fillRecycler() {
        super.fillRecycler();

        // button was pushed, next we are initialisation all views
        for (int s = spanCount; s < baseList.size(); s++) {
            String nameLine = baseList.get(s).toString();
            baseList.set(++s, new MyTreeMap(k, nameLine).getResult());
            baseList.set(++s, new MyHashMap(k, nameLine).getResult());
        }

        adapter.submitList(baseList);
        getRecycler().setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        fillRecycler();
    }
}
