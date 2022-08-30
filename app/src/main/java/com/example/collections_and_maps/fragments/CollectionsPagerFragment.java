package com.example.collections_and_maps.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.MyItemDecoration;
import com.example.collections_and_maps.MySpanSizeLookup;
import com.example.collections_and_maps.R;
import com.example.collections_and_maps.StepByStep;
import com.example.collections_and_maps.adapters.ListViewAdapter;
import com.example.collections_and_maps.calculations.MyArrayList;
import com.example.collections_and_maps.calculations.MyCopyOnWriteArrayList;
import com.example.collections_and_maps.calculations.MyLinkedList;


public class CollectionsPagerFragment extends MyFragment implements View.OnClickListener  {

    public CollectionsPagerFragment() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(this.getClass()!=null) { // your code here}
            Resources res = this.requireActivity().getResources();
            listArr = res.getStringArray(R.array.collections);
            list = res.getStringArray(R.array.collections_item);
            spanCount = listArr.length;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pager_fragment, container, false);
    }

    public static CollectionsPagerFragment newInstance(String param1, String param2) {
        CollectionsPagerFragment fragment = new CollectionsPagerFragment();
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
        gridLayoutManager.setSpanSizeLookup(new MySpanSizeLookup(4, 1, spanCount));
        listRecycler.setLayoutManager(gridLayoutManager);
        createClearGrid();
        listRecycler.setAdapter(new ListViewAdapter(baseList));
    }

    @Override
    public void calc() {
        super.calc();

        // button was pushed, next we are initialisation all views
        for (int s = 0; s < baseList.size(); s++) {
            String nameLine = baseList.get(s).toString();
            baseList.set(++s, new MyArrayList(k, nameLine).getResult());
            baseList.set(++s, new MyLinkedList(k, nameLine).getResult());
            baseList.set(++s, new MyCopyOnWriteArrayList(k, nameLine).getResult());
        }

        listRecycler.setAdapter(new ListViewAdapter(baseList));
    }

    @Override
    public void onClick(View view) {
        calc();
    }

}