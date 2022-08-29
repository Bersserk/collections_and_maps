package com.example.collections_and_maps.calculations;

import java.util.HashMap;
import java.util.Random;

public class MyHashMap {

    private HashMap hashMap;
    private String result;

    public String getResult() {
        return result + " ms";
    }

    public MyHashMap(long k, String nameLine) {
        this.hashMap = createHashMap(k);

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
        hashMap.put(i, i);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void searchByKey() {
        int i = random();
        double start = System.nanoTime();
        hashMap.get(i).toString();
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void removing() {
        int i = random();
        double start = System.nanoTime();
        hashMap.remove(i);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private HashMap createHashMap(long k) {
        HashMap<Integer, Object> list = new HashMap<Integer, Object>();
        for (int i = 0; i < k; i++) {
            list.put(i, new Object());
        }
        return list;
    }

    private int random() {
        return new Random().nextInt(hashMap.size() + 1);
    }
}
