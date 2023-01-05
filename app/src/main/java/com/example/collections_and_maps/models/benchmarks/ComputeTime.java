package com.example.collections_and_maps.models.benchmarks;

import com.example.collections_and_maps.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ComputeTime {
    private List list;
    private Map map;

    private double getChose(int methodName) {
        switch (methodName) {
            case R.string.add_begin:
                return addItemToStart();
            case R.string.add_middle:
                return addItemToMiddle();
            case R.string.add_end:
                return addItemToEnd();
            case R.string.search_value:
                return searchByValue();
            case R.string.remove_begin:
                return removingInBeginning();
            case R.string.remove_middle:
                return removingInMiddle();
            case R.string.remove_end:
                return removingInEnd();
            case R.string.add_new:
                return addingNew();
            case R.string.search_key:
                return searchByKey();
            case R.string.removing:
                return removing();
            default:
                throw new IllegalArgumentException();
        }
    }


    private double addItemToStart() {
        double start = System.nanoTime();
        list.add(null);
        return (System.nanoTime() - start) / 1000000;
    }

    private double addItemToMiddle() {
        double start = System.nanoTime();
        list.add(list.size() / 2, null);
        return (System.nanoTime() - start) / 1000000;
    }

    private double addItemToEnd() {
        double start = System.nanoTime();
        list.add(list.size(), null);
        return (System.nanoTime() - start) / 1000000;
    }

    private double searchByValue() {
        double start = System.nanoTime();
        Object valueByIndex = list.get((int) (list.size() / 3 * 2));
        return (System.nanoTime() - start) / 1000000;
    }

    private double removingInBeginning() {
        double start = System.nanoTime();
        list.remove(0);
        return (System.nanoTime() - start) / 1000000;
    }

    private double removingInMiddle() {
        double start = System.nanoTime();
        list.remove(list.size() / 2);
        return (System.nanoTime() - start) / 1000000;
    }

    private double removingInEnd() {
        double start = System.nanoTime();
        list.remove(list.size() - 1);
        return (System.nanoTime() - start) / 1000000;
    }

    private double addingNew() {
        double start = System.nanoTime();
        map.put(-1, null);
        return (System.nanoTime() - start) / 1000000;
    }

    private double searchByKey() {
        double start = System.nanoTime();
        Object b = map.get(map.size()/2).toString();
        return (System.nanoTime() - start) / 1000000;
    }

    private double removing() {
        double start = System.nanoTime();
        map.remove(map.size()/2);
        return (System.nanoTime() - start) / 1000000;
    }


    public double getResult(ResultItem rItem, int value) {
        switch (rItem.headerText){
            case R.string.ArrayList:
                this.list = toCreateArray(new ArrayList(value), value);
                break;
            case R.string.LinkedList:
                this.list = toCreateArray(new LinkedList(), value);
                break;
            case R.string.CopyOnWrite:
                this.list = toCreateArray(new CopyOnWriteArrayList(), value);
                break;
            case R.string.HashMap:
                this.map = toCreateArray(new HashMap<Integer, Object>(), value);
                break;
            case R.string.TreeMap:
                this.map = toCreateArray(new TreeMap<Integer, Object>(), value);
                break;
        }
        return getChose(rItem.methodName);
    }

    public List toCreateArray (List list, int size){

        List mList = list;
        for (int i = 0; i < size; i++) {
            mList.add(null);
        }
        return mList;
    }

    public Map toCreateArray (Map mMap, int size){

        Map map = mMap;
        for (int i = 0; i < size; i++) {
            map.put(i, null);
        }
        return map;
    }

}
