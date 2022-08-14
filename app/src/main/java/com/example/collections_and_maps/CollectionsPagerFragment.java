package com.example.collections_and_maps;

import static android.service.controls.ControlsProviderService.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CollectionsPagerFragment extends Fragment{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    CalculationFragment calculationFragment;



    ArrayList<DataView> dataView = new ArrayList<DataView>();

    public CollectionsPagerFragment() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        // Required empty public constructor
    }

    public static CollectionsPagerFragment newInstance(String param1, String param2) {
        StepByStep.log(CollectionsPagerFragment.class, Thread.currentThread().getStackTrace()[2]);
        CollectionsPagerFragment fragment = new CollectionsPagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

       // setInitialData();

    }

    private void setInitialData(){
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);


        dataView.add(new DataView());
        dataView.add(new DataView("box", "98816"));
        dataView.add(new DataView("name3", "013118"));
        //dataView.add(new DataView("nameView1", true, new CalculationFragment().getUpdateBox()));
        //dataView.add(new DataView("nameView2", true, new CalculationFragment().getUpdateBox()));
        //dataView.add(new DataView("nameView3", true, new CalculationFragment().getUpdateBox()));

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        super.onViewCreated(view, savedInstanceState);

        Button calcButton = view.findViewById(R.id.calcButton);


        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {






            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collections_pager, container, false);

        recyclerView = view.findViewById(R.id.list);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));

        recyclerView.setAdapter(new DataViewAdapter(this, dataView));


        return view;
    }


}