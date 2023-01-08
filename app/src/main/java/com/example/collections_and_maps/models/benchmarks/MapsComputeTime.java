package com.example.collections_and_maps.models.benchmarks;

import com.example.collections_and_maps.R;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapsComputeTime {

    public double toGetResult(ResultItem rItem, int value) {
        final Map map;
        switch (rItem.headerText) {
            case R.string.HashMap:
                map = createArray(new HashMap<Integer, Integer>(), value);
                break;
            case R.string.TreeMap:
                map = createArray(new TreeMap<Integer, Integer>(), value);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + rItem.headerText);
        }
        return calculateResult(rItem.methodName, map);
    }

    private double calculateResult(int methodName, Map map) {
        switch (methodName) {
            case R.string.add_new:
                return addingNew(map);
            case R.string.search_key:
                return searchByKey(map);
            case R.string.removing:
                return removing(map);
            default:
                throw new IllegalArgumentException();
        }
    }


    private double addingNew(Map map) {
        double start = System.nanoTime();
        map.put(-1, null);
        return (System.nanoTime() - start);
    }

    private double searchByKey(Map map) {
        double start = System.nanoTime();
        Object b = map.get(map.size() / 2);
        return (System.nanoTime() - start);
    }

    private double removing(Map map) {
        double start = System.nanoTime();
        map.remove(map.size() / 2);
        return (System.nanoTime() - start);
    }


    private Map createArray(Map mMap, int size) {
        final Map map = mMap;
        for (int i = 0; i < size; i++) {
            map.put(i, i);
        }
        return map;
    }
}
