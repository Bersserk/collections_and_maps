package com.example.collections_and_maps.ui.benchmark;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.MyHashMap;
import com.example.collections_and_maps.models.benchmarks.MyTreeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class MapsPagerFragment extends BaseFragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillDataRecycler(createTemplateList(R.array.maps, R.array.maps_item));
    }

    @Override
    protected GridLayoutManager manageGridLayout() {
        return manageGridLayout(2, new RecyclerSizeLookup(3, 1, 2));
    }

    @Override
    protected void getResults(List <String> templateList, int sizeList) {
        ArrayList <String>  resultList = new ArrayList(templateList);

        MyHashMap hashMap = new MyHashMap(sizeList);
        MyTreeMap treeMap = new MyTreeMap(sizeList);

        for (int i = 0, y = 0; i < templateList.size(); i++) {
            if (templateList.get(i).equals("...")) {
                beginNewThread(i++, hashMap, resultList, y);
                beginNewThread(i++, treeMap, resultList, y);
                y++;
            }
        }
    }

    public void beginNewThread(int i, MyHashMap hashMap, ArrayList resultList, int y) {
        Runnable task = new Runnable() {
            public void run() {
                resultList.set(i, "");
                resultList.set(i, hashMap.myHashMap(y));
                fillDataRecycler(resultList);
            }
        };
        Executors.newCachedThreadPool().execute(task);
    }

    public void beginNewThread(int i, MyTreeMap treeMap, ArrayList resultList, int y) {
        Runnable task = new Runnable() {
            public void run() {
                resultList.set(i, "");
                resultList.set(i, treeMap.myTreeMap(y));
                fillDataRecycler(resultList);
            }
        };
        Executors.newCachedThreadPool().execute(task);
    }

}
