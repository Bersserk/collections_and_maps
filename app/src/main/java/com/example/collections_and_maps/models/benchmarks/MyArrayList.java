package com.example.collections_and_maps.models.benchmarks;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Random;

public class MyArrayList {

    private ArrayList<Integer> arrayList;
    private String result;

    public MyArrayList(int k, int i) {
        arrayList = createArrayList(k);

        switch (i) {
            case 0:
                addItemToStart();
                break;
            case 1:
                addItemToMiddle();
                break;
            case 2:
                addItemToEnd();
                break;
            case 3:
                searchByValue();
                break;
            case 4:
                removingInBeginning();
                break;
            case 5:
                removingInMiddle();
                break;
            case 6:
                removingInEnd();
                break;
            default:
                result = "нет такого поля";
        }
    }

    public String getResult() {
        return result + " ms";
    }

    private String addItemToStart() {
        double start = System.nanoTime();
        arrayList.add(0, null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String addItemToMiddle() {
        double start = System.nanoTime();
        arrayList.add(arrayList.size() / 2, null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String addItemToEnd() {
        double start = System.nanoTime();
        arrayList.add(arrayList.size(), null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String searchByValue() {
        int index = 0;
        for (int i = 0; i < 10; i++) {
            if (arrayList.size() < 0) {
                throw new IllegalArgumentException("Array's size must not be negative");
            }
            while (index == 0 || index == arrayList.size()) {
                index = new Random().nextInt(arrayList.size() + 1);
            }
        }

        double start = System.nanoTime();
        arrayList.get(index);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String removingInBeginning() {
        double start = System.nanoTime();
        arrayList.remove(0);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String removingInMiddle() {
        double start = System.nanoTime();
        arrayList.remove(arrayList.size() / 2);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String removingInEnd() {
        double start = System.nanoTime();
        arrayList.remove(arrayList.size() - 1);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    // if we are adding more them 1 000 000, app is crashing here...
    @NonNull
    private ArrayList createArrayList(int k) {
        ArrayList <Integer> list = new ArrayList <Integer>(k);
        for (int i = 0; i < k; i++) {
            list.add(i);
        }
        return list;
    }
}

