package com.example.collections_and_maps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class CollectionsPagerFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    ArrayList<DataView> dataView = new ArrayList<DataView>();

    public CollectionsPagerFragment() {
        // Required empty public constructor
    }

    public static CollectionsPagerFragment newInstance(String param1, String param2) {
        CollectionsPagerFragment fragment = new CollectionsPagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    private void setInitialData(){

        dataView.add(new DataView("nameView1", true, new CalculationFragment().getUpdateBox()));
        dataView.add(new DataView ("Аргентина", "Буэнос-Айрес", R.drawable.argentina));
        dataView.add(new DataView ("Колумбия", "Богота", R.drawable.columbia));
        dataView.add(new DataView ("Уругвай", "Монтевидео", R.drawable.uruguai));
        dataView.add(new DataView ("Чили", "Сантьяго", R.drawable.chile));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collections_pager, container, false);
        TextView nameView = view.findViewById(R.id.nameView);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        TextView resultView = view.findViewById(R.id.resultView);

        return view;
    }
}