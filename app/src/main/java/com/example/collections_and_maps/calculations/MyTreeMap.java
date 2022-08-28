package com.example.collections_and_maps.calculations;

import java.util.Random;
import java.util.TreeMap;

public class MyTreeMap {

    private TreeMap treeMap;
    private String result;

    public String getResult() {
        return result;
    }

    public MyTreeMap(TreeMap treeMap, String nameLine) {

        this.treeMap = treeMap;
//        this.arrayList = getList();

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
        double start = System.nanoTime();
        // расчет метода
//        arrayList.add(0, null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void searchByKey() {
        double start = System.nanoTime();
        // расчет метода
//        arrayList.add(0, null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void removing() {
        double start = System.nanoTime();
        // расчет метода
//        arrayList.add(0, null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }


}

