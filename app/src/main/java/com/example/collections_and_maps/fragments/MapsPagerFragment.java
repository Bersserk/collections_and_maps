package com.example.collections_and_maps.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.StepByStep;
import com.example.collections_and_maps.adapters.DataViewAdapter;
import com.example.collections_and_maps.adapters.ListViewAdapter;

import java.util.ArrayList;

public class MapsPagerFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private int spanCount;
    private RecyclerView headListRecycler;
    private RecyclerView listRecycler;

    private String[] headList = {"TreeMap", "HashMap"};
    private String[] list = {"adding in the beginning", "adding in the middle",
            "adding in the end", "search by value", "removing in the beginning",
            "removing in the middle", "removing in the end"};

    public MapsPagerFragment() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        spanCount = headList.length;
        // Required empty public constructor
    }

    public static MapsPagerFragment newInstance(String param1, String param2) {
        StepByStep.log(MapsPagerFragment.class, Thread.currentThread().getStackTrace()[2]);
        MapsPagerFragment fragment = new MapsPagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.collections_pager_fragment, container, false);

        // находим recycler по id
        headListRecycler = view.findViewById(R.id.headListRecycler);
        headListRecycler.setHasFixedSize(true);
       // headListRecycler.setLayoutParams(ConstraintLayout.LayoutParams.HORIZONTAL);
        headListRecycler.setLayoutManager(new GridLayoutManager(this.getActivity(), spanCount));
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2], spanCount);

        listRecycler = view.findViewById(R.id.listRecycler);
        listRecycler.setHasFixedSize(true);
        listRecycler.setAdapter(new ListViewAdapter(headList));


        //ArrayList arrayList;
        // находим recycler по id
        listRecycler = view.findViewById(R.id.listRecycler);
        listRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        listRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        // задаем LayoutManager который будет формировать вид нашего recycler
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(), 3);
        // задаем recycler наш LayoutManager
        listRecycler.setLayoutManager(new GridLayoutManager(this.getActivity(), 3));
        listRecycler.setHasFixedSize(true);

        ArrayList arrayList = new ArrayList();
        int i = 0;
        int y = 0;
        while (y < list.length) {
            System.out.println("i = " + i + "; y = " + y);
            if (i == 3) {
                i = 0;
                y++;
            } else {
                arrayList.add(list[y]);
                i++;
            }
        }

        //dataViewAdapter = new DataViewAdapter(arrayList);
        listRecycler.setAdapter(new DataViewAdapter(arrayList));






        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        super.onViewCreated(view, savedInstanceState);

        Button calcButton = view.findViewById(R.id.calcButton);
        //collectionSize = view.findViewById(R.id.collectionSize);
        TextView textView = view.findViewById(R.id.askTextView);
        textView.setText("Введите длину массива!");

        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

               // calc();
            }
        });
    }
}