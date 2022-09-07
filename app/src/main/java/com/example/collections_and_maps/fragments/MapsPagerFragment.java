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

import com.example.collections_and_maps.MySpanSizeLookup;
import com.example.collections_and_maps.R;
import com.example.collections_and_maps.ComfortableLogsTV;
//import com.example.collections_and_maps.adapters.RecyclerViewAdapter;
import com.example.collections_and_maps.adapters.ListViewAdapter;
import com.example.collections_and_maps.calculations.MyHashMap;
import com.example.collections_and_maps.calculations.MyTreeMap;

public class MapsPagerFragment extends MyFragment {

    public MapsPagerFragment() {
//        ComfortableLogsTV.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getClass() != null) { // your code here}
            Resources res = this.requireActivity().getResources();
            listArr = res.getStringArray(R.array.maps);
            list = res.getStringArray(R.array.maps_item);
            spanCount = listArr.length;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.pager_fragment, container, false);
    }

    public static MapsPagerFragment newInstance(String param1, String param2) {
        MapsPagerFragment fragment = new MapsPagerFragment();
        Bundle args = new Bundle();
        args.putString(COLLECTIONS, param1);
        args.putString(MAPS, param2);
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
        gridLayoutManager.setSpanSizeLookup(new MySpanSizeLookup(3, 1, spanCount));
        listRecycler.setLayoutManager(gridLayoutManager);
        createClearGrid();

        adapter = new ListViewAdapter();
        adapter.submitList(baseList);
        listRecycler.setAdapter(adapter);
    }

    public void calc() {
        super.calc();

        // button was pushed, next we are initialisation all views
        for (int s = 0; s < baseList.size(); s++) {
            String nameLine = baseList.get(s).toString();
            baseList.set(++s, new MyTreeMap(k, nameLine).getResult());
            baseList.set(++s, new MyHashMap(k, nameLine).getResult());
        }

        adapter.submitList(baseList);
        listRecycler.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        calc();
    }
}