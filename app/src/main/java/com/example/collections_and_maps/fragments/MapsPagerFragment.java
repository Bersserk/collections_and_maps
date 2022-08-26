package com.example.collections_and_maps.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.collections_and_maps.adapters.DataViewAdapter2;
import com.example.collections_and_maps.adapters.ListViewAdapter;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class MapsPagerFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private int spanCount;
    private RecyclerView headListRecycler;
    private RecyclerView listRecycler;
    private LinearLayout listLinear;
    private FrameLayout listFrame;
    private ConstraintLayout constraint;
    LinearLayout mainLayout;

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
        View view = inflater.inflate(R.layout.maps_pager_fragment, container, false);



//        listLinear = getActivity().findViewById(R.id.listLinear);
        // Добавляем новый ImageView
        ImageView imageView = new ImageView(view.getContext());
        imageView.setImageResource(R.drawable.custombackground);
        LinearLayout.LayoutParams imageViewLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(imageViewLayoutParams);

        listLinear.addView(imageView);

        headListRecycler = view.findViewById(R.id.headListRecycler);
        headListRecycler.setHasFixedSize(true);
        headListRecycler.setLayoutManager(new GridLayoutManager(this.getActivity(), spanCount));
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2], spanCount);
        headListRecycler.setAdapter(new ListViewAdapter(headList));


        listRecycler = view.findViewById(R.id.listRecycler);

        listRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        listRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        listRecycler.setLayoutManager(new GridLayoutManager(this.getActivity(), 1));
        listRecycler.setHasFixedSize(true);
        listRecycler.addView(inflater.inflate(R.layout.temp_layout, listRecycler, false));






//        listLinear = view.findViewById(R.id.listLinear);
//        constraint = view.findViewById(R.id.constraint);
        //ArrayList arrayList;

//        LinearLayout mainLayout = (LinearLayout)view.findViewById(R.id.listLinear);



        View view1 = LayoutInflater.from(view.getContext()).inflate(R.layout.temp_layout, null);



        ArrayList arrayList = new ArrayList();

        arrayList.add(view1);
        arrayList.add(view1);
        arrayList.add(view1);

//        arrayList.add(view3);
        listRecycler.setAdapter(new DataViewAdapter2(arrayList));

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