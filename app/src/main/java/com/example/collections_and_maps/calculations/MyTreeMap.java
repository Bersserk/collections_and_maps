package com.example.collections_and_maps.calculations;

import java.util.Random;
import java.util.TreeMap;

public class MyTreeMap {

    private TreeMap treeMap;
    private String result;

    public String getResult() {
        return result + " ms";
    }

    public MyTreeMap(long k, String nameLine) {

        this.treeMap = createTreeMap(k);

        switch (nameLine) {
            case "adding new":
                addingNew();
                break;
            case "search by key":
                searchByKey();
                break;
            case "removing":
                removing();
                break;
            default:
                result = "нет такого поля";
        }
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

    private TreeMap createTreeMap(long k) {
        TreeMap<Integer, String> list = new TreeMap<Integer, String>();
        for (int i = 0; i < k; i++) {
            list.put(i, String.valueOf(i));
        }
        return list;
    }

    private int random() {
        return new Random().nextInt(treeMap.size() + 1);
    }


}

