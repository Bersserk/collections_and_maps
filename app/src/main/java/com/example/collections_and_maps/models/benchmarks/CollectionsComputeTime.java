package com.example.collections_and_maps.models.benchmarks;

import com.example.collections_and_maps.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionsComputeTime {

    public double getResult(ResultItem rItem, int value) {
        final List list;
        switch (rItem.headerText) {
            case R.string.ArrayList:
                list = new ArrayList(Collections.nCopies(value, 0));
                break;
            case R.string.LinkedList:
                list = new LinkedList(Collections.nCopies(value, 0));
                break;
            case R.string.CopyOnWrite:
                list = new CopyOnWriteArrayList(Collections.nCopies(value, 0));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + rItem.headerText);
        }
        return calculateResult(rItem.methodName, list);
    }

    private double calculateResult(int methodName, List list) {
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


    private double addItemToStart(List list) {
        double start = System.nanoTime();
        list.add(0);
        return (System.nanoTime() - start);
    }

    private double addItemToMiddle(List list) {
        double start = System.nanoTime();
        list.add(list.size() / 2, 0);
        return (System.nanoTime() - start);
    }

    private double addItemToEnd(List list) {
        double start = System.nanoTime();
        list.add(list.size(), 0);
        return (System.nanoTime() - start);
    }

    private double searchByValue(List list) {
        double start = System.nanoTime();
        list.get((list.size() / 3 * 2));
        return (System.nanoTime() - start);
    }

    private double removingInBeginning(List list) {
        double start = System.nanoTime();
        list.remove(0);
        return (System.nanoTime() - start);
    }

    private double removingInMiddle(List list) {
        double start = System.nanoTime();
        list.remove(list.size() / 2);
        return (System.nanoTime() - start);
    }

    private double removingInEnd(List list) {
        double start = System.nanoTime();
        list.remove(list.size() - 1);
        return (System.nanoTime() - start);
    }
}
