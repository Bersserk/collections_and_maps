package com.example.collections_and_maps.calculations;

import androidx.annotation.NonNull;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyCopyOnWriteArrayList{

    private CopyOnWriteArrayList copyOnWriteArrayList;
    private String result;

    public String getResult() {
        return result + " ms";
    }

    public MyCopyOnWriteArrayList(long k, String setConstant) {
       copyOnWriteArrayList = createCopyOnWriteArrayList(k);

        switch (setConstant) {
            case "adding in the beginning":
                addItemToStart();
                break;
            case "adding in the middle":
                addItemToMiddle();
                break;
            case "adding in the end":
                addItemToEnd();
                break;
            case "search by value":
                searchByValue();
                break;
            case "removing in the beginning":
                removingInBeginning();
                break;
            case "removing in the middle":
                removingInMiddle();
                break;
            case "removing in the end":
                removingInEnd();
                break;
            default:
                result = "нет такого поля";
        }
    }

    private void addItemToStart() {
        double start = System.nanoTime();
        copyOnWriteArrayList.add(0, null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void addItemToMiddle() {
        double start = System.nanoTime();
        copyOnWriteArrayList.add(copyOnWriteArrayList.size() / 2, null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void addItemToEnd() {
        double start = System.nanoTime();
        copyOnWriteArrayList.add(copyOnWriteArrayList.size(), null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void searchByValue() {
        int index = 0;
        for (int i = 0; i < 10; i++) {
            if (copyOnWriteArrayList.size() < 0) {
                throw new IllegalArgumentException("Array's size must not be negative");
            }
            index = new Random().nextInt(copyOnWriteArrayList.size() + 1);
        }

        double start = System.nanoTime();
        copyOnWriteArrayList.get(index);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void removingInBeginning() {
        double start = System.nanoTime();
        copyOnWriteArrayList.remove(0);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void removingInMiddle() {
        double start = System.nanoTime();
        copyOnWriteArrayList.remove(copyOnWriteArrayList.size() / 2);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void removingInEnd() {
        double start = System.nanoTime();
        copyOnWriteArrayList.remove(copyOnWriteArrayList.size() - 1);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    @NonNull
    private CopyOnWriteArrayList createCopyOnWriteArrayList(long k) {
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        for (int i = 0; i < k; i++) {
            list.add(0);
        }
        return list;
    }
}
