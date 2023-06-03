package com.example.collections_and_maps.models.benchmarks;

import static com.example.collections_and_maps.models.benchmarks.ResultItem.EMPTY;

import android.util.Log;

import com.example.collections_and_maps.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionsBenchmark implements Benchmark {

    private final Random random = new Random();

    private final int[] listNamesForHead = new int[]{R.string.ArrayList, R.string.LinkedList, R.string.CopyOnWrite};
    private final int[] listNamesForMethod = new int[]{R.string.add_begin, R.string.add_middle,
            R.string.add_end, R.string.search_value, R.string.remove_begin,
            R.string.remove_middle, R.string.remove_end};

    @Override
    public List<ResultItem> getItemsList(boolean showProgress) {
        List<ResultItem> itemsList = new ArrayList<>();

        for (int itemOfListHead : listNamesForHead) {
            itemsList.add(new ResultItem(itemOfListHead, R.string.empty, EMPTY, false));
        }

        for (int methodsID : listNamesForMethod) {
            itemsList.add(new ResultItem(R.string.empty, methodsID, EMPTY, false));
            for (int headsID : listNamesForHead) {
                itemsList.add(new ResultItem(headsID, methodsID, EMPTY, showProgress));
            }
        }
        return itemsList;
    }


    @Override
    public double getMeasureTime(ResultItem rItem, int value) {
        final List<Integer> list;
        switch (rItem.headerText) {
            case R.string.ArrayList:
                list = new ArrayList<>(Collections.nCopies(value, 0));
                break;
            case R.string.LinkedList:
                list = new LinkedList<>(Collections.nCopies(value, 0));
                break;
            case R.string.CopyOnWrite:
                list = new CopyOnWriteArrayList<>(Collections.nCopies(value, 0));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + rItem.headerText);
        }


        return calculateResult(rItem.methodName, list);
    }


    private double calculateResult(int methodName, List<Integer> list) {
        switch (methodName) {
            case R.string.add_begin:
                return addItemToStart(list);
            case R.string.add_middle:
                return addItemToMiddle(list);
            case R.string.add_end:
                return addItemToEnd(list);
            case R.string.search_value:
                return searchByValue(list);
            case R.string.remove_begin:
                return removingInBeginning(list);
            case R.string.remove_middle:
                return removingInMiddle(list);
            case R.string.remove_end:
                return removingInEnd(list);
            default:
                throw new IllegalArgumentException();
        }
    }


    private double addItemToStart(List<Integer> list) {
        double start = System.nanoTime();
        list.add(0);
        double resultTime = System.nanoTime() - start;
        return resultTime;
    }

    private double addItemToMiddle(List<Integer> list) {
        double start = System.nanoTime();
        list.add(list.size() / 2, 0);
        double resultTime = System.nanoTime() - start;
//        Log.i(TAG, "addItemToMiddle = " + resultTime);
        return resultTime;
    }

    private double addItemToEnd(List<Integer> list) {
        double start = System.nanoTime();
        list.add(list.size(), 0);
        double resultTime = System.nanoTime() - start;
//        Log.i(TAG, "addItemToEnd = " + resultTime);
        return resultTime;
    }

    private double searchByValue(List<Integer> list) {
        int index = random.nextInt(list.size());
        list.add(index, index);
        double start = System.nanoTime();
        boolean has = list.contains(index);
        double resultTime = System.nanoTime() - start;
//        Log.i(TAG, "searchByValue = " + resultTime);
        return resultTime;
    }

    private double removingInBeginning(List<Integer> list) {
        double start = System.nanoTime();
        list.remove(0);
        double resultTime = System.nanoTime() - start;
//        Log.i(TAG, "removingInBeginning = " + resultTime);
        return resultTime;
    }

    private double removingInMiddle(List<Integer> list) {
        double start = System.nanoTime();
        list.remove(list.size() / 2);
        double resultTime = System.nanoTime() - start;
//        Log.i(TAG, "removingInMiddle = " + resultTime);
        return resultTime;
    }

    private double removingInEnd(List<Integer> list) {
        double start = System.nanoTime();
        list.remove(list.size() - 1);
        double resultTime = System.nanoTime() - start;
//        Log.i(TAG, "removingInEnd = " + resultTime);
        return resultTime;
    }

    @Override
    public int getSpan() {
        return listNamesForHead.length;
    }
}
