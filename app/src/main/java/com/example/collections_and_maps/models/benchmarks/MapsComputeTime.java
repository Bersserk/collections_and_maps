package com.example.collections_and_maps.models.benchmarks;

import com.example.collections_and_maps.R;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapsComputeTime {

    public double getMeasureTime(ResultItem rItem, int value) {
        final Map map = rItem.headerText == R.string.HashMap
                ? createArray(new HashMap<>(value), value)
                : createArray(new TreeMap<>(), value);

        return calculateResult(rItem.methodName, map);
    }

    private double calculateResult(int methodName, Map<Integer, Integer> map) {
        if (methodName == R.string.add_new) {
            return addingNew(map);
        } else if (methodName == R.string.search_key) {
            return searchByKey(map);
        } else {
            return removing(map);
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

    private Map createArray(Map<Integer, Integer> map, int size) {
        for (int i = 0; i < size; i++) {
            map.put(i, i);
        }
        return map;
    }
}
