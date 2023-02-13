package com.example.collections_and_maps.models.benchmarks;

import com.example.collections_and_maps.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionsComputeTime implements ComputeTime {
    private final Random random = new Random();

    public final ComputeTime computeTime;
    private final int[] listHeadsId;
    private final int[] listMethodsId;

    public CollectionsComputeTime() {
        listHeadsId = collectionsHeads();
        listMethodsId = collectionsMethods();
        this.computeTime = this;
    }


    @Override
    public int[] getListHeadsId() {
        return listHeadsId;
    }

    @Override
    public int[] getListMethodsId() {
        return listMethodsId;
    }

    private int[] collectionsHeads() {
        return new int[]{R.string.ArrayList, R.string.LinkedList, R.string.CopyOnWrite};
    }

    private int[] collectionsMethods() {
        return new int[]{R.string.add_begin, R.string.add_middle,
                R.string.add_end, R.string.search_value, R.string.remove_begin,
                R.string.remove_middle, R.string.remove_end};
    }


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
        return (System.nanoTime() - start);
    }

    private double addItemToMiddle(List<Integer> list) {
        double start = System.nanoTime();
        list.add(list.size() / 2, 0);
        return (System.nanoTime() - start);
    }

    private double addItemToEnd(List<Integer> list) {
        double start = System.nanoTime();
        list.add(list.size(), 0);
        return (System.nanoTime() - start);
    }

    private double searchByValue(List<Integer> list) {
        int index = random.nextInt(list.size());
        list.add(index, index);
        double start = System.nanoTime();
        boolean has = list.contains(index);
        return (System.nanoTime() - start);
    }

    private double removingInBeginning(List<Integer> list) {
        double start = System.nanoTime();
        list.remove(0);
        return (System.nanoTime() - start);
    }

    private double removingInMiddle(List<Integer> list) {
        double start = System.nanoTime();
        list.remove(list.size() / 2);
        return (System.nanoTime() - start);
    }

    private double removingInEnd(List<Integer> list) {
        double start = System.nanoTime();
        list.remove(list.size() - 1);
        return (System.nanoTime() - start);
    }
}
