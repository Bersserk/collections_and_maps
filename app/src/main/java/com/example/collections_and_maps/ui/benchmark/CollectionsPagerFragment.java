package com.example.collections_and_maps.ui.benchmark;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.RecyclerSizeLookup;
import com.example.collections_and_maps.models.benchmarks.MyArrayList;
import com.example.collections_and_maps.models.benchmarks.MyCopyOnWriteArrayList;
import com.example.collections_and_maps.models.benchmarks.MyLinkedList;

public class CollectionsPagerFragment extends BaseFragment {

    public static CollectionsPagerFragment newInstance(String param1) {
        CollectionsPagerFragment fragment = new CollectionsPagerFragment();
        Bundle args = new Bundle();
        args.putString(COLLECTIONS, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = this.requireActivity().getResources();
        listNamesMainItem = res.getStringArray(R.array.collections);
        listNamesItem = res.getStringArray(R.array.collections_item);
        spanCount = listNamesMainItem.length;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button calcButton = view.findViewById(R.id.calcButton);
        calcButton.setOnClickListener(this);

        // making listNamesItem recycler
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),
                spanCount, LinearLayoutManager.VERTICAL, false);
        //set SpanSizeLookup()
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(4, 1, spanCount));
        getRecycler().setLayoutManager(gridLayoutManager);
        createClearGrid();

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

        adapter.submitList(baseList);
        getRecycler().setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        fillRecycler();
    }
}
