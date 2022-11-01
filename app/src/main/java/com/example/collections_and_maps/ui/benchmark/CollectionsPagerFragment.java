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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CollectionsPagerFragment extends BaseFragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Logger.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        fillDataRecycler(createTemplateList(R.array.collections, R.array.collections_item));
    }

    @Override
    protected GridLayoutManager manageGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),
                3, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(4, 1, 3));
        return gridLayoutManager;
    }


    @Override
    protected void getResults(List <String> templateList, int sizeList) {
        ArrayList <String> resultList = new ArrayList(templateList);

        MyArrayList arrayList = new MyArrayList(sizeList);
        MyLinkedList linkedList = new MyLinkedList(sizeList);
        MyCopyOnWriteArrayList copyOnWriteArrayList = new MyCopyOnWriteArrayList(sizeList);

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
                fillDataRecycler2(resultList);
            }
        };
        Executors.newCachedThreadPool().execute(task);
    }

    public void beginNewThread(int i, MyLinkedList linkedList, ArrayList resultList, int y) {
        Runnable task = new Runnable() {
            public void run() {
                resultList.set(i, "");
                resultList.set(i, linkedList.myLinkedList(y));
                listRecycler.postInvalidate();
                fillDataRecycler2(resultList);
            }
        };
        Executors.newCachedThreadPool().execute(task);
    }

    public void beginNewThread(int i, MyCopyOnWriteArrayList copyOnWriteArrayList, ArrayList resultList, int y) {
        Runnable task = new Runnable() {
            public void run() {
                resultList.set(i, "");
                resultList.set(i, copyOnWriteArrayList.myCopyOnWriteArrayList(y));
                fillDataRecycler2(resultList);
            }
        };
        Executors.newCachedThreadPool().execute(task);
    }


}

