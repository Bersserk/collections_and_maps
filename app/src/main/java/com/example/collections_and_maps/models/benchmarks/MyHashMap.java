package com.example.collections_and_maps.models.benchmarks;

import java.util.HashMap;
import java.util.Random;

public class MyHashMap extends HashMap{

    private HashMap hashMap;
    private String result;

    public MyHashMap(int sizeList) {
        this.hashMap = createHashMap(sizeList);
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

    public void addingNew() {
        int i = random();
        double start = System.nanoTime();

        // test part for sleep
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hashMap.put(i, i);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    protected void searchByKey() {
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


    private HashMap createHashMap(int k) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < k; i++) {
            hashMap.put(i,"");
        }
        return hashMap;
    }

    private int random() {
        return new Random().nextInt(hashMap.size() + 1);
    }
}
