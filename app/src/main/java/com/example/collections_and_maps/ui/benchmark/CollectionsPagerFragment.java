package com.example.collections_and_maps.ui.benchmark;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.collections_and_maps.R;


import java.util.ArrayList;
import java.util.Random;

public class CollectionsPagerFragment extends BaseFragment {

    BenchmarksAdapter adapter;
    Handler mHandler;

    public static CollectionsPagerFragment newInstance(String param1) {
        CollectionsPagerFragment fragment = new CollectionsPagerFragment();
        Bundle args = new Bundle();
        args.putString(COLLECTIONS, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler = new Handler();
//        Resources res = this.requireActivity().getResources();
//        listNamesMainItem = res.getStringArray(R.array.collections);
//        listNamesItem = res.getStringArray(R.array.collections_item);
//        spanCount = listNamesMainItem.length;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button calcButton = view.findViewById(R.id.calcButton);
        calcButton.setOnClickListener(this);

        // making listNamesItem recycler
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),
                3, LinearLayoutManager.VERTICAL, false);
        //set SpanSizeLookup
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(4, 1, 3));
        getRecycler().setLayoutManager(gridLayoutManager);

        // createClearGrid
        adapter = new BenchmarksAdapter(this);
        getRecycler().setAdapter(adapter);
    }

    @Override
    public void checkInputCorrectly() {
        super.checkInputCorrectly();

        ArrayList<String> resultList = new ArrayList<>();
        // button was pushed, next we are initialisation all views
//        for (int s = 3; s < resultList.size(); s++) {
//            String nameLine = resultList.get(s).toString();
//            resultList.set(++s, new MyArrayList(k, nameLine).getResult());
//            resultList.set(++s, new MyLinkedList(k, nameLine).getResult());
//            resultList.set(++s, new MyCopyOnWriteArrayList(k, nameLine).getResult());
//        }

        for (int s = 0; s < 7; s++) {
            resultList.add(String.valueOf(""));
        }

        for (int s = 0; s < 7; s++) {
            beginNewThread(s, new Random().nextInt(10000), resultList);
        }

    }

    public void beginNewThread(int i, int i1, ArrayList resultList) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(i1);

                    resultList.set(i, "" +i+i+i+i);
                    reWrite(resultList);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public void reWrite (ArrayList resultList) {
        adapter = new BenchmarksAdapter(this, resultList);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                getRecycler().setAdapter(adapter);
            }
        });
    }



    @Override
    public void onClick(View view) {
            checkInputCorrectly();
    }
}

