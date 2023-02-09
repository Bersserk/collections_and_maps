package com.example.collections_and_maps.domain.benchmarks;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.domain.interfases.MeasuredTime;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapsMeasuredTime implements MeasuredTime {

    public double getResult(ResultItem rItem, int value) {


        final Map<Integer, Integer> map;
        if (rItem.headerText == R.string.HashMap) {
            map = createMap(new HashMap<>(), value);
        } else if (rItem.headerText == R.string.TreeMap) {
            map = createMap(new TreeMap<>(), value);
        } else {
            throw new IllegalStateException("Unexpected value: " + rItem.headerText);
        }

        return calculateResult(rItem.methodName, map);
    }

    private double calculateResult(int methodName, Map<Integer, Integer> map) {
        if (methodName == R.string.add_new) {
            return addingNew(map);
        } else if (methodName == R.string.search_key) {
            return searchByKey(map);
        } else if (methodName == R.string.removing) {
            return removing(map);
        } else {
            throw new IllegalArgumentException();
        }
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
