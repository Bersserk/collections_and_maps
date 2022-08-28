package com.example.collections_and_maps.calculations;

import java.util.HashMap;
import java.util.Random;

public class MyHashMap {

    private HashMap hashMap;
    private String result;

    public String getResult() {
        return result + " ms";
    }

    public MyHashMap(HashMap hashMap, String nameLine) {
        this.hashMap = hashMap;

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

    private int random() {
        return new Random().nextInt(hashMap.size() + 1);
    }
}
