package com.example.collections_and_maps.ui.benchmark;

import android.util.Log;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.MyArrayList;
import com.example.collections_and_maps.models.benchmarks.MyCopyOnWriteArrayList;
import com.example.collections_and_maps.models.benchmarks.MyLinkedList;

import java.util.ArrayList;
import java.util.List;

public class CollectionsPagerFragment extends BaseFragment {

    @Override
    protected int getSpanCount() {
        return 3;
    }

    @Override
    protected List<String> createTemplateList() {
        return super.createTemplateList(R.array.collections, R.array.collections_item);
    }

    @Override
    protected List<String> getResults(List<String> templateList, int sizeList) {

        final List<String> resultList = new ArrayList<>(templateList);

        MyArrayList arrayList = new MyArrayList(sizeList);
        MyLinkedList linkedList = new MyLinkedList(sizeList);
        MyCopyOnWriteArrayList copyOnWriteArrayList = new MyCopyOnWriteArrayList(sizeList);

        for (int i = 0, y = 0; i < templateList.size(); i++) {
            if (templateList.get(i).isEmpty()) {
                beginNewThread(i++, arrayList, resultList, y);
                beginNewThread(i++, linkedList, resultList, y);
                beginNewThread(i++, copyOnWriteArrayList, resultList, y);
                y++;
            }
        }
        return resultList;
    }

    public void beginNewThread(int i, MyArrayList arrayList, List<String> resultList, int y) {
        resultList.set(i, "");
        resultList.set(i, arrayList.myArrayList(y));
        Log.i("exe", "run");
    }

    public void beginNewThread(int i, MyLinkedList linkedList, List<String> resultList, int y) {
        resultList.set(i, "");
        resultList.set(i, linkedList.myLinkedList(y));
    }

    public void beginNewThread(int i, MyCopyOnWriteArrayList copyOnWriteArrayList, List<String> resultList, int y) {
        resultList.set(i, "");
        resultList.set(i, copyOnWriteArrayList.myCopyOnWriteArrayList(y));
    }
}
