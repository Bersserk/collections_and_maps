package com.example.collections_and_maps.fragments;

import android.os.Bundle;
import android.text.TextUtils;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.DataView;
import com.example.collections_and_maps.MyItemDecoration;
import com.example.collections_and_maps.MySpanSizeLookup;
import com.example.collections_and_maps.R;
import com.example.collections_and_maps.StepByStep;
import com.example.collections_and_maps.adapters.ListViewAdapter;
import com.example.collections_and_maps.calculations.MyArrayList;
import com.example.collections_and_maps.calculations.MyCopyOnWriteArrayList;
import com.example.collections_and_maps.calculations.MyLinkedList;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class CollectionsPagerFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView headListRecycler;
    private RecyclerView listRecycler;

    private ArrayList baseList;
    private ArrayList arrayList;
    private LinkedList linkedList;
    private CopyOnWriteArrayList copyOnWriteArrayList;


    EditText collectionSize;
    ArrayList<DataView> viewArrayList = new ArrayList<DataView>();

    String[] listArr = {"ArrayList", "LinkedList",
            "CopyOnWriteArrayList"};


    static final String[] list = {"adding in the beginning", "adding in the middle",
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

        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this.getActivity(), 3);
        headListRecycler = (RecyclerView) view.findViewById(R.id.headListRecycler);
        headListRecycler.setLayoutManager(gridLayoutManager2);
        headListRecycler.setAdapter(new ListViewAdapter(listArr));

        // находим recycler по id
        listRecycler = view.findViewById(R.id.listRecycler);
        listRecycler.addItemDecoration(new MyItemDecoration());

        // задаем LayoutManager который будет формировать вид нашего recycler
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(), 3, //The number of rows in the grid
                LinearLayoutManager.VERTICAL,
                false);
        //set SpanSizeLookup()
        gridLayoutManager.setSpanSizeLookup(new MySpanSizeLookup(4, 1, 3));

        // задаем recycler наш LayoutManager
        listRecycler.setLayoutManager(gridLayoutManager);
        listRecycler.setHasFixedSize(true);

        baseList = new ArrayList<String>();
        int y = 0;
        while (y < list.length) {

            baseList.add(list[y]);
            baseList.add("...");
            baseList.add("...");
            baseList.add("...");
            y++;
        }

        listRecycler.setAdapter(new ListViewAdapter(baseList));
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
        if (collectionSize.length() > 0 && TextUtils.isDigitsOnly(collectionSize.getText())) {
            long k = Long.parseLong(collectionSize.getText().toString());
            arrayList = createArrayList(k);
            linkedList = createLinkedList(k);
            copyOnWriteArrayList = createCopyOnWriteArrayList(k);
        } else {
            Toast.makeText(getContext(), "Размер необходимо задавать только числами", Toast.LENGTH_LONG).show();
        }
        // нажата кнопка, далее инициализируем вьюхи которыми заполним грид

        for (int s = 0; s < baseList.size(); s++) {
            String nameLine = baseList.get(s).toString();
            baseList.set(++s, new MyArrayList(arrayList, nameLine).getResult());
            baseList.set(++s, new MyLinkedList(linkedList, nameLine).getResult());
            baseList.set(++s, new MyCopyOnWriteArrayList(copyOnWriteArrayList, nameLine).getResult());
        }

        listRecycler.setAdapter(new ListViewAdapter(baseList));
    }

    private CopyOnWriteArrayList createCopyOnWriteArrayList(long k) {
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        for (int i = 0; i < k; i++) {
            list.add(0);
        }
        return list;
    }

    private LinkedList createLinkedList(long k) {
        LinkedList list = new LinkedList();
        for (int i = 0; i < k; i++) {
            list.add(0);
        }
        return list;
    }

    private ArrayList createArrayList(long k) {
        ArrayList list = new ArrayList();
        for (int i = 0; i < k; i++) {
            list.add(0);
        }
        return list;
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