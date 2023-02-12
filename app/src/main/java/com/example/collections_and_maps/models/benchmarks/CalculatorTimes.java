package com.example.collections_and_maps.models.benchmarks;

import com.example.collections_and_maps.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class CalculatorTimes<T> {

    private final Random random = new Random();

    private T specifiedArray;

    public double getCalculatedTime(ResultItem rItem, int value) {

        switch (rItem.headerText) {
            case R.string.ArrayList:
                specifiedArray = (T) new ArrayList<>(Collections.nCopies(value, 0));
                break;
            case R.string.LinkedList:
                specifiedArray = (T) new LinkedList<>(Collections.nCopies(value, 0));
                break;
            case R.string.CopyOnWrite:
                specifiedArray = (T) new CopyOnWriteArrayList<>(Collections.nCopies(value, 0));
                break;
            case R.string.HashMap:
                specifiedArray = (T) createMap(new HashMap<>(), value);
                break;
            case R.string.TreeMap:
                specifiedArray = (T) createMap(new TreeMap<>(), value);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + rItem.headerText);
        }
        return calculateResult(rItem.methodName, specifiedArray);
    }

    private double calculateResult(int methodName, T specifiedArray) {
        switch (methodName) {
            case R.string.add_begin:
                return addItemToStart((List<Integer>) specifiedArray);
            case R.string.add_middle:
                return addItemToMiddle((List<Integer>) specifiedArray);
            case R.string.add_end:
                return addItemToEnd((List<Integer>) specifiedArray);
            case R.string.search_value:
                return searchByValue((List<Integer>) specifiedArray);
            case R.string.remove_begin:
                return removingInBeginning((List<Integer>) specifiedArray);
            case R.string.remove_middle:
                return removingInMiddle((List<Integer>) specifiedArray);
            case R.string.remove_end:
                return removingInEnd((List<Integer>) specifiedArray);

            case R.string.add_new:
                return addingNew((Map<Integer, Integer>) specifiedArray);
            case R.string.search_key:
                return searchByKey((Map<Integer, Integer>) specifiedArray);
            case R.string.removing:
                return removing((Map<Integer, Integer>) specifiedArray);

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


    private double addingNew(Map<Integer, Integer> map) {
        double start = System.nanoTime();
        map.put(-1, null);
        return (System.nanoTime() - start);
    }

    private double searchByKey(Map<Integer, Integer> map) {
        double start = System.nanoTime();
        Object b = map.get(map.size() / 2);
        return (System.nanoTime() - start);
    }

    private double removing(Map<Integer, Integer> map) {
        double start = System.nanoTime();
        map.remove(map.size() / 2);
        return (System.nanoTime() - start);
    }

    private Map<Integer, Integer> createMap(Map<Integer, Integer> map, int size) {
        for (int i = 0; i < size; i++) {
            map.put(i, i);
        }
        return map;
    }
}
