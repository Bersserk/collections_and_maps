package com.example.collections_and_maps;

import static android.service.controls.ControlsProviderService.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CollectionsPagerFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;

    int k = 0;
    int y = 0;

    EditText collectionSize;
    EditText numberElements;

    String [] list = {"name1","name2","name3"};


    ArrayList<DataView> dataView = new ArrayList<DataView>();

    public CollectionsPagerFragment() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

         setInitialData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collections_pager, container, false);

        recyclerView = view.findViewById(R.id.list);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 3));

        recyclerView.setAdapter(new DataViewAdapter(this, dataView));

        return view;
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


    private void setInitialData() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        if(!dataView.isEmpty()) {
            dataView.clear();
        }

        for(int i=0; i<list.length; i++){
            dataView.add(new DataView(list[i], k , y));
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        super.onViewCreated(view, savedInstanceState);

        Button calcButton = view.findViewById(R.id.calcButton);
        collectionSize = view.findViewById(R.id.collectionSize);
        numberElements = view.findViewById(R.id.numberElements);

        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

                calc();
            }
        });
    }


    public void calc (){
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        if(collectionSize.length() > 0 && numberElements.length() > 0) {
            this.k = Integer.parseInt(collectionSize.getText().toString());
            this.y = Integer.parseInt(numberElements.getText().toString());
        }

        setInitialData();

        recyclerView.setAdapter(new DataViewAdapter(this, dataView));
    }


}