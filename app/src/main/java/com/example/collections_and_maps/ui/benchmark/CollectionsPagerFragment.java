package com.example.collections_and_maps.ui.benchmark;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.MyArrayList;
import com.example.collections_and_maps.models.benchmarks.MyCopyOnWriteArrayList;
import com.example.collections_and_maps.models.benchmarks.MyLinkedList;
import com.example.collections_and_maps.models.logger.Logger;

import java.util.ArrayList;

public class CollectionsPagerFragment extends BaseFragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Logger.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),
                3, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(4, 1, 3));
        listRecycler.setLayoutManager(gridLayoutManager);
        String[] listNamesMainItem = getResources().getStringArray(R.array.collections);
        String[] listNamesItem = getResources().getStringArray(R.array.collections_item);

        resultList = new ResultList(listNamesMainItem, listNamesItem);
        refreshResults(resultList.getTemplateList());
    }

    @Override
    public void onClick(View view) {
        resultList.setSizeArray(getSizeList());
        getResults(resultList.getTemplateList(), resultList.getSizeList());
    }

    public void getResults(ArrayList templateList, int sizeArray) {
        ArrayList resultList = new ArrayList();
        resultList.addAll(templateList);

        MyArrayList arrayList = new MyArrayList(sizeArray);
        MyLinkedList linkedList = new MyLinkedList(sizeArray);
        MyCopyOnWriteArrayList copyOnWriteArrayList = new MyCopyOnWriteArrayList(sizeArray);

        for (int i = 0, y = 0; i < templateList.size(); i++) {
            if (templateList.get(i).equals("...")) {
                beginNewThread(i++, arrayList, resultList, y);
                beginNewThread(i++, linkedList, resultList, y);
                beginNewThread(i++, copyOnWriteArrayList, resultList, y);
                y++;
            }
        }
    }

    public void beginNewThread(int i, MyArrayList arrayList, ArrayList resultList, int y) {
        Runnable task = new Runnable() {
            public void run() {
                resultList.set(i, "");
                resultList.set(i, arrayList.myArrayList(y));
                refreshResults(resultList);
            }
        };
        cachedThreadPool.execute(task);
    }

    public void beginNewThread(int i, MyLinkedList linkedList, ArrayList resultList, int y) {
        Runnable task = new Runnable() {
            public void run() {
                resultList.set(i, "");
                resultList.set(i, linkedList.myLinkedList(y));
                refreshResults(resultList);
            }
        };
        cachedThreadPool.execute(task);
    }

    public void beginNewThread(int i, MyCopyOnWriteArrayList copyOnWriteArrayList, ArrayList resultList, int y) {
        Runnable task = new Runnable() {
            public void run() {
                resultList.set(i, "");
                resultList.set(i, copyOnWriteArrayList.myCopyOnWriteArrayList(y));
                refreshResults(resultList);
            }
        };
        cachedThreadPool.execute(task);
    }
}

