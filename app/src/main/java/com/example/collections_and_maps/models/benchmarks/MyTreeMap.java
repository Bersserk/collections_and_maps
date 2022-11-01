package com.example.collections_and_maps.models.benchmarks;

import java.util.Random;
import java.util.TreeMap;

public class MyTreeMap extends TreeMap {

    private TreeMap treeMap;
    private String result;

    public MyTreeMap(int sizeList) {
        treeMap = new TreeMap();
        for (int i = 0; i < sizeList; i++) {
            treeMap.put(i, "");
        }
    }

    public String myTreeMap(int i) {

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

    private String addingNew() {
        int i = random();
        double start = System.nanoTime();
        treeMap.put(i, i);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String searchByKey() {
        int i = random();
        double start = System.nanoTime();

        // test part for sleep
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        treeMap.get(i).toString();
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String removing() {
        int i = random();
        double start = System.nanoTime();
        treeMap.remove(i);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }


    private int random() {
        return new Random().nextInt(treeMap.size() + 1);
    }
}

