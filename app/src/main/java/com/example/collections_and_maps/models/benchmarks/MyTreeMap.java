package com.example.collections_and_maps.models.benchmarks;

import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

public class MyTreeMap extends HashMap {

    private TreeMap treeMap;
    private String result;

    public MyTreeMap(int sizeList) {
        this.treeMap = createTreeMap(sizeList);
    }

    public String getResult(int i) {

        switch (i) {
            case 0:
                addingNew();
                break;
            case 1:
                searchByKey();
                break;
            case 2:
                removing();
                break;
            default:
                result = "нет такого поля";
        }

        return result + " ms";
    }

    private void addingNew() {
        int i = random();
        double start = System.nanoTime();
        treeMap.put(i, i);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void searchByKey() {
        int i = random();
        double start = System.nanoTime();

        // test part for sleep
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        treeMap.get(i).toString();
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void removing() {
        int i = random();
        double start = System.nanoTime();
        treeMap.remove(i);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private TreeMap createTreeMap(int k) {
        TreeMap treeMap = new TreeMap();
        for (int i = 0; i < k; i++) {
            treeMap.put(i, String.valueOf(i));
        }
        return treeMap;
    }

    private int random() {
        return new Random().nextInt(treeMap.size() + 1);
    }


}

