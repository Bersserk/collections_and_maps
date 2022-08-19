package com.example.collections_and_maps.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.DataView;
import com.example.collections_and_maps.R;
import com.example.collections_and_maps.StepByStep;
import com.example.collections_and_maps.adapters.DataViewAdapter;
import com.example.collections_and_maps.adapters.ListViewAdapter;


import java.util.ArrayList;


public class CollectionsPagerFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView listView;
    private RecyclerView recyclerView;
    private DataViewAdapter dataViewAdapter;
    ArrayList arrayList;


    EditText collectionSize;
    ArrayList<DataView> viewArrayList = new ArrayList<DataView>();

    String[] listArr = {"ArrayList", "LinkedList",
            "CopyOnWriteArrayList"};

    String[] countries = {"Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай"};

    String[] list = {"adding in the beginning", "adding in the middle",
            "adding in the end", "search by value", "removing in the beginning",
            "removing in the middle", "removing in the end"};


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


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.collections_pager_fragment, container, false);


        // находим recycler по id
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        // задаем LayoutManager который будет формировать вид нашего recycler
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(), 3);
        // задаем recycler наш LayoutManager
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        ListViewAdapter listAdapter = new ListViewAdapter(listArr);
        //listView.setRotation(90);

        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this.getActivity(), 3);
        listView = (RecyclerView) view.findViewById(R.id.listView);
        listView.setLayoutManager(gridLayoutManager2);
        listView.setAdapter(listAdapter);

        arrayList = new ArrayList();
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


        dataViewAdapter = new DataViewAdapter(arrayList);
        recyclerView.setAdapter(dataViewAdapter);

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


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        super.onViewCreated(view, savedInstanceState);

        Button calcButton = view.findViewById(R.id.calcButton);
        collectionSize = view.findViewById(R.id.collectionSize);
        TextView textView = view.findViewById(R.id.askTextView);
        textView.setText("Введите длину массива!");

        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

                calc();
            }
        });
    }


    public void calc() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        // get data from EditText
        long k = 0L;
        if (collectionSize.length() > 0) {
            k = Long.parseLong(collectionSize.getText().toString());
        }
        // нажата кнопка, далее инициализируем вьюхи которыми заполним грид


        dataViewAdapter = new DataViewAdapter(arrayList);
        recyclerView.setAdapter(dataViewAdapter);
        //setInitialData();


    }

    private void setInitialData() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        // если список для вьюх не пуст, то очищаем
        if (!viewArrayList.isEmpty()) {
            viewArrayList.clear();
        }

        // разбирамеся для чего 139 строка и далее в цикле нам нужно правильно наполнить вьюшки

        //ArrayList listForArray = new ArrList(k).getResultFromArrayList();

//        for (int y=0; y<list.length; y++) {
//            for (int i = 0; i < listArr.length; i++) {
//                viewArrayList.add(new DataView(listArr[i], list[y], k, (String) listForArray.get(i)));
//            }
//        }

    }


}