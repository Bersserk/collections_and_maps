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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CalculationFragment extends Fragment {



    public String getUpdateBox() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        return String.valueOf(updateBox);
    }

    String k = "k";
    String y  = "y";

    TextView updateBox = null;
    EditText collectionSize;
    EditText numberElements;
    DataView dataView;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;




    public CalculationFragment() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        // Required empty public constructor
    }


    public static CalculationFragment newInstance(String param1, String param2) {
        StepByStep.log(CalculationFragment.class, Thread.currentThread().getStackTrace()[2]);
        CalculationFragment fragment = new CalculationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        super.onViewCreated(view, savedInstanceState);
        updateBox = view.findViewById(R.id.askTextView);
        collectionSize = view.findViewById(R.id.collectionSize);
        numberElements = view.findViewById(R.id.numberElements);
        //Button calcButton = view.findViewById(R.id.calcButton);
        k = updateBox.getText().toString();

        /*
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Log.i(TAG, "CalculationFragment.onClick()");

                if(collectionSize.length() > 0 && numberElements.length() > 0) {
                    k = collectionSize.getText().toString();
                    y = numberElements.getText().toString();


                    updateBox.setText(String.valueOf((k+y)));
                    new DataView();
                }
               // calc();
            }
        });

         */
    }

    public void calc(){
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        //updateBox.setText("Введите значения");
        if(collectionSize.length() > 0 && numberElements.length() > 0) {
            //k = Integer.parseInt(collectionSize.getText().toString());
            //y = Integer.parseInt(numberElements.getText().toString());


            //updateBox.setText(String.valueOf((k+y)));

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        super.onCreate(savedInstanceState);
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
        return inflater.inflate(R.layout.fragment_calculation, container, false);
    }

}