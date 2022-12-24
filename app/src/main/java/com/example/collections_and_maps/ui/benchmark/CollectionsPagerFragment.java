package com.example.collections_and_maps.ui.benchmark;

import androidx.annotation.NonNull;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.ArrayList;
import java.util.List;

public class CollectionsPagerFragment extends BaseFragment {

    @Override
    protected int getSpanCount() {
        return 3;
    }

    @Override
    protected List<ResultItem> createTemplateList() {

        final List<ResultItem> items = new ArrayList<>();

        items.add(new ResultItem(R.string.ArrayList, R.integer.empty, R.integer.empty));
        items.add(new ResultItem(R.string.LinkedList, R.integer.empty, R.integer.empty));
        items.add(new ResultItem(R.string.CopyOnWrite, R.integer.empty, R.integer.empty));

        final int[] listHeadsId = {R.string.ArrayList, R.string.LinkedList, R.string.CopyOnWrite};
        final int[] listMethodsId = {R.string.add_begin, R.string.add_middle,
                R.string.add_end, R.string.search_value, R.string.remove_begin,
                R.string.remove_middle, R.string.remove_end};

        for (int methodsID : listMethodsId) {
            items.add(new ResultItem(R.integer.empty, methodsID, R.integer.empty));
            for (int headsID : listHeadsId) {
                items.add(new ResultItem(headsID, methodsID, R.integer.emptyResult));
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
            case R.string.add_begin:
                addItemToStart();
                break;
            case R.string.add_middle:
                addItemToMiddle();
                break;
            case R.string.add_end:
                addItemToEnd();
                break;
            case R.string.search_value:
                searchByValue();
                break;
            case R.string.remove_begin:
                removingInBeginning();
                break;
            case R.string.remove_middle:
                removingInMiddle();
                break;
            case R.string.remove_end:
                removingInEnd();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void addItemToStart() {
//        Log.i("Collections", " - addItemToStart()");
        toRandomValue(0, 7000);
//        list.add(0, null);
    }

    private void addItemToMiddle() {
//        Log.i("Collections", " - addItemToMiddle()");
        toRandomValue(0, 7000);
//        list.add(list.size() / 2, null);
    }

    private void addItemToEnd() {
//        Log.i("Collections", " - addItemToEnd()");
        toRandomValue(0, 7000);
//        list.add(list.size(), null);
    }

    private void searchByValue() {
//        Log.i("Collections", " - searchByValue()");
        toRandomValue(0, 7000);

//        int index = 0;
//        for (int i = 0; i < 10; i++) {
//            if (list.size() < 0) {
//                throw new IllegalArgumentException("Array's size must not be negative");
//            }
//            while (index == 0 || index == list.size()) {
//                index = new Random().nextInt(list.size() + 1);
//            }
//        }
//        list.get(index);
    }

    private void removingInBeginning() {
//        Log.i("Collections", " - removingInBeginning()");
        toRandomValue(0, 7000);

//        list.remove(0);
    }

    private void removingInMiddle() {
//        Log.i("Collections", " - removingInMiddle()");
        toRandomValue(0, 7000);

//        list.remove(list.size() / 2);
    }

    private void removingInEnd() {
//        Log.i("Collections", " - removingInEnd()");
        toRandomValue(0, 7000);

//        list.remove(list.size() - 1);
    }


    @Override
    protected ResultItem toRandomValue(@NonNull ResultItem rItem, int value) {
        if (rItem.result == R.integer.empty) {
            return rItem;
        } else {
            return new ResultItem(rItem.headerText, rItem.methodName, toRandomValue(0, 7));
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

