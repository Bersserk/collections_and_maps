package com.example.collections_and_maps.ui.benchmark;


import androidx.annotation.NonNull;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.ArrayList;
import java.util.List;

public class MapsPagerFragment extends BaseFragment {

    @Override
    protected int getSpanCount() {
        return 2;
    }

    @Override
    public List<ResultItem> createTemplateList(int animateStatus) {
        final List<ResultItem> items = new ArrayList<>();

        items.add(new ResultItem(R.string.HashMap, R.integer.empty, R.integer.empty, R.string.clear));
        items.add(new ResultItem(R.string.TreeMap, R.integer.empty, R.integer.empty, R.string.clear));

        final int[] listHeadsId = {R.string.HashMap, R.string.TreeMap};
        final int[] listMethodsId = {R.string.add_new, R.string.search_key, R.string.removing};

        for (int methodsID : listMethodsId) {
            items.add(new ResultItem(R.integer.empty, methodsID, R.integer.empty, R.string.clear));
            for (int headsID : listHeadsId) {
                items.add(new ResultItem(headsID, methodsID, R.integer.emptyResult, animateStatus));
            }
        }
        return items;
    }


    public String getResult(int methodName) {
        String result;
        double start = System.nanoTime();
        getChose(methodName);
        result = String.valueOf((System.nanoTime() - start) / 1000000);

        return result;
    }

    void getChose(int methodName) {

        switch (methodName) {
            case R.string.add_new:
                addingNew();
                break;
            case R.string.search_key:
                searchByKey();
                break;
            case R.string.removing:
                removing();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public void addingNew() {
//        toRandomValue(fromTime, toTime);
//        hashMap.put(i, i);
    }

    protected void searchByKey() {
//        toRandomValue(fromTime, toTime);
//        hashMap.get(i).toString();
    }

    private void removing() {
//        toRandomValue(fromTime, toTime);
//        hashMap.remove(i);
    }


    @Override
    protected ResultItem toMakeResultItem(@NonNull ResultItem rItem, int value) {
        if (rItem.result == R.integer.empty) {
            return rItem;
        } else {
            return new ResultItem(rItem.headerText, rItem.methodName, toRandomValue(0, 7), R.string.result);
        }
    }

    public long toRandomValue(int from, int to) {
        double d = from + Math.random() * (to - from);
        long res = (long) (d * 1000);
        try {
            Thread.sleep(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res;
    }

}
