package com.example.collections_and_maps.ui.benchmark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.MyArrayList;
import com.example.collections_and_maps.models.benchmarks.MyCopyOnWriteArrayList;
import com.example.collections_and_maps.models.benchmarks.MyHashMap;
import com.example.collections_and_maps.models.benchmarks.MyLinkedList;
import com.example.collections_and_maps.models.benchmarks.MyTreeMap;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MapsPagerFragment extends BaseFragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // making list recycler
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),
                2, LinearLayoutManager.VERTICAL, false);
        //set SpanSizeLookup()
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(3, 1, 2));
        getRecycler().setLayoutManager(gridLayoutManager);
        listNamesMainItem = getResources().getStringArray(R.array.maps);
        listNamesItem = getResources().getStringArray(R.array.maps_item);

        createClearGrid();
    }

    @Override
    public void onClick(View view) {
        getResults();
    }

    @Override
    public void getResults() {
        super.getResults();

        MyHashMap hashMap = new MyHashMap(sizeArray);
        MyTreeMap treeMap = new MyTreeMap(sizeArray);

        for (int i=0, y=0; i< list.size(); i++) {
            if (list.get(i).equals(item)){
                beginNewThread(i, hashMap, resultList, y);
                beginNewThread(++i, treeMap, resultList, y);
                y++;
            }
        }
    }

    public void beginNewThread(int i, MyHashMap hashMap, ArrayList resultList, int y) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                resultList.set(i, "");
                resultList.set(i, hashMap.getResult(y));
                refreshResults(resultList);
            }
        });
        t.start();
    }

    public void beginNewThread(int i, MyTreeMap treeMap, ArrayList resultList, int y) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                resultList.set(i, "");
                resultList.set(i, treeMap.getResult(y));
                refreshResults(resultList);
            }
        });
        t.start();
    }

}
