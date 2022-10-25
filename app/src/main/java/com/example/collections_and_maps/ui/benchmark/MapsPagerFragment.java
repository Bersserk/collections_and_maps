package com.example.collections_and_maps.ui.benchmark;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.MyHashMap;
import com.example.collections_and_maps.models.benchmarks.MyTreeMap;

import java.util.ArrayList;
import java.util.List;

public class MapsPagerFragment extends BaseFragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fillDataRecycler(createTemplateList(R.array.maps, R.array.maps_item));
    }

    @Override
    protected void getResults(List<String> templateList, int sizeList) {
        ArrayList resultList = new ArrayList(templateList);

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


    @Override
    protected GridLayoutManager manageGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),
                2, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(3, 1, 2));
        return gridLayoutManager;
    }


    public void beginNewThread(int i, MyHashMap hashMap, ArrayList resultList, int y) {
        Runnable task = new Runnable() {
            public void run() {
                resultList.set(i, "");
                resultList.set(i, hashMap.myHashMap(y));
                fillDataRecycler(resultList);
            }
        };
        cachedThreadPool.execute(task);
    }

    public void beginNewThread(int i, MyTreeMap treeMap, ArrayList resultList, int y) {
        Runnable task = new Runnable() {
            public void run() {
                resultList.set(i, "");
                resultList.set(i, treeMap.myTreeMap(y));
                fillDataRecycler(resultList);
            }
        };
        cachedThreadPool.execute(task);
    }

}
