package com.example.collections_and_maps.calculations;

import java.util.ArrayList;
import java.util.Random;

public class MyArrayList extends MyBaseData {

    private ArrayList<Integer> arrayList;
    private String result;

    public String getResult() {
        return result;
    }

    public MyArrayList(int arraySize, String setConstant) {
        super(arraySize, setConstant);
        this.arrayList = getArrayList();

        switch (setConstant) {
            case "addItemToStart":
                addItemToStart();
                break;
            case "addItemToMiddle":
                addItemToMiddle();
                break;
            case "addItemToEnd":
                addItemToEnd();
                break;
            case "searchByValue":
                searchByValue();
                break;
            case "removingInBeginning":
                removingInBeginning();
                break;
            case "removingInMiddle":
                removingInMiddle();
                break;
            case "removingInEnd":
                removingInEnd();
                break;
            default:
                result = "arraySize = " + " arraySize; " + " setConstant = " + setConstant;
        }
    }

    private void addItemToStart() {
        double start = System.nanoTime();
        arrayList.add(0, null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void addItemToMiddle() {
        double start = System.nanoTime();
        arrayList.add(arrayList.size() / 2, null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void addItemToEnd() {
        double start = System.nanoTime();
        arrayList.add(arrayList.size(), null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void searchByValue() {
        int index = 0;
        for (int i = 0; i < 10; i++) {
            if (arrayList.size() < 0) {
                throw new IllegalArgumentException("Array's size must not be negative");
            }
            index = new Random().nextInt(arrayList.size() + 1);
        }

        double start = System.nanoTime();
        arrayList.get(index);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void removingInBeginning() {
        double start = System.nanoTime();
        arrayList.remove(0);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void removingInMiddle() {
        double start = System.nanoTime();
        arrayList.remove(arrayList.size() / 2);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void removingInEnd() {
        double start = System.nanoTime();
        arrayList.remove(arrayList.size() - 1);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }
}

