package com.example.collections_and_maps.models.benchmarks;

import java.util.HashMap;
import java.util.Random;

public class MyHashMap extends HashMap{

    private HashMap hashMap;
    private String result;

    public MyHashMap(int sizeList) {
        hashMap = new HashMap();
        for (int i = 0; i < sizeList; i++) {
            hashMap.put(i,"");
        }
    }

    public MyHashMap(String methodName) {
        //запускаем метод согласно входящему имени
    }

    public String myHashMap(int i) {

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

    public String addingNew() {
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
        return result;
    }

    protected String searchByKey() {
        int i = random();
        double start = System.nanoTime();
        hashMap.get(i).toString();
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String removing() {
        int i = random();
        double start = System.nanoTime();
        hashMap.remove(i);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private int random() {
        return new Random().nextInt(hashMap.size() + 1);
    }
}
