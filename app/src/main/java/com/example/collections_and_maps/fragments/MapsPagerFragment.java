package com.example.collections_and_maps.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.MyItemDecoration;
import com.example.collections_and_maps.MySpanSizeLookup;
import com.example.collections_and_maps.R;
import com.example.collections_and_maps.StepByStep;
import com.example.collections_and_maps.adapters.ListViewAdapter;
import com.example.collections_and_maps.calculations.MyHashMap;
import com.example.collections_and_maps.calculations.MyTreeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class MapsPagerFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private RecyclerView headListRecycler;
    private RecyclerView listRecycler;

    private TreeMap treeMap;
    private HashMap hashMap;

    private ArrayList baseList;
    private EditText collectionSize;

    static final String[] listArr = {"TreeMap", "HashMap"};
    private final int spanCount = listArr.length;

    static final String[] list = {"adding new", "search by key",
            "removing"};

    public MapsPagerFragment() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pager_fragment, container, false);

        // making title recycler
        headListRecycler = (RecyclerView) view.findViewById(R.id.headListRecycler);
        headListRecycler.setLayoutManager(new GridLayoutManager(this.getActivity(), spanCount));
        headListRecycler.setAdapter(new ListViewAdapter(listArr));

        // making list recycler
        listRecycler = view.findViewById(R.id.listRecycler);
        listRecycler.addItemDecoration(new MyItemDecoration());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),
                spanCount, LinearLayoutManager.VERTICAL, false);
        //set SpanSizeLookup()
        gridLayoutManager.setSpanSizeLookup(new MySpanSizeLookup(3, 1, spanCount));
        listRecycler.setLayoutManager(gridLayoutManager);
        listRecycler.setHasFixedSize(true);

        baseList = new ArrayList<String>();
        for (int y = 0; y < list.length; y++) {
            baseList.add(list[y]);
            for (int i = 0; i < spanCount; i++) {
                baseList.add("...");
            }
        }

        listRecycler.setAdapter(new ListViewAdapter(baseList));
        return view;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button calcButton = view.findViewById(R.id.calcButton);
        collectionSize = view.findViewById(R.id.collectionSize);

        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc();
            }
        });
    }


    public void calc() {
        // get data from EditText
        if (collectionSize.length() > 0 && TextUtils.isDigitsOnly(collectionSize.getText())) {
            long k = Long.parseLong(collectionSize.getText().toString());
            treeMap = createTreeMap(k);
            hashMap = createHashMap(k);
        } else {
            Toast.makeText(getContext(), "Размер необходимо задавать только числами", Toast.LENGTH_LONG).show();
        }

        // button was pushed, next we are initialisation all views
        for (int s = 0; s < baseList.size(); s++) {
            String nameLine = baseList.get(s).toString();
            baseList.set(++s, new MyTreeMap(treeMap, nameLine).getResult());
            baseList.set(++s, new MyHashMap(hashMap, nameLine).getResult());
        }

        listRecycler.setAdapter(new ListViewAdapter(baseList));
    }

    private TreeMap createTreeMap(long k) {
        TreeMap<Integer, String> list = new TreeMap<Integer, String>();
        for (int i = 0; i < k; i++) {
            list.put(i, String.valueOf(i));
        }
        return list;
    }

    private HashMap createHashMap(long k) {
        HashMap<Integer, Object> list = new HashMap<Integer, Object>();
        for (int i = 0; i < k; i++) {
            list.put(i, new Object());
        }
        return list;
    }
}