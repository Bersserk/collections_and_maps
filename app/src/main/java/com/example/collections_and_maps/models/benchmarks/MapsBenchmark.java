package com.example.collections_and_maps.models.benchmarks;

import static com.example.collections_and_maps.models.benchmarks.ResultItem.EMPTY;

import com.example.collections_and_maps.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapsBenchmark implements Benchmark {

    private final int[] listNamesForHead = new int[]{R.string.HashMap, R.string.TreeMap};
    private final int[] listNamesForMethod = new int[]{R.string.add_new, R.string.search_key, R.string.removing};

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
        if (value < 0) {
            throw new IllegalStateException("Unexpected value: " + value);
        }

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

    @Override
    public int getSpan() {
        return listNamesForHead.length;
    }
}
