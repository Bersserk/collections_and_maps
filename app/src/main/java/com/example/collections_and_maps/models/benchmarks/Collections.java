package com.example.collections_and_maps.models.benchmarks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Collections {
    int tillTime = 7000;
    int sinceTime = 0;

    private List listForCalculate; // this list for next using in calculation of methods
    private String result;
    private double key;

    public double getKey() {
        return key;
    }

    public Collections(Access key) {
        this.key = key.getValue();
    }

    private void toRandomValue (int since, int till){
        try {
            double d = since + Math.random() * (till-since);
            Thread.sleep ((long) (d));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Collections(String methodName) {
        switch (methodName){
            case "ArrayList":
                listForCalculate = new ArrayList();
                break;
            case "LinkedList":
                listForCalculate = new LinkedList();
                break;
            case "CopyOnWriteArrayList":
                listForCalculate = new CopyOnWriteArrayList();
                break;
        }
    }

    public String getResult(String methodName) {
        double start = System.nanoTime();
        getChose(methodName);
        result = String.valueOf((System.nanoTime() - start) / 1000000);

        return result;
    }

    void getChose (String methodName){
//        Log.i("Collections", " - getChose (String methodName) - " + methodName);
        switch (methodName) {
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
//        Log.i("Collections", " - addItemToStart()");
        toRandomValue(sinceTime, tillTime);
//        list.add(0, null);
    }

    private void addItemToMiddle() {
//        Log.i("Collections", " - addItemToMiddle()");
        toRandomValue(sinceTime, tillTime);
//        list.add(list.size() / 2, null);
    }

    private void addItemToEnd() {
//        Log.i("Collections", " - addItemToEnd()");
        toRandomValue(sinceTime, tillTime);
//        list.add(list.size(), null);
    }

    private void searchByValue() {
//        Log.i("Collections", " - searchByValue()");
        toRandomValue(sinceTime, tillTime);

//        int index = 0;
//        for (int i = 0; i < 10; i++) {
//            if (list.size() < 0) {
//                throw new IllegalArgumentException("Array's size must not be negative");
//            }
//            while (index == 0 || index == list.size()) {
//                index = new Random().nextInt(list.size() + 1);
//            }
//        }
//        list.get(index);
    }

    private void removingInBeginning() {
//        Log.i("Collections", " - removingInBeginning()");
        toRandomValue(sinceTime, tillTime);

//        list.remove(0);
    }

    private void removingInMiddle() {
//        Log.i("Collections", " - removingInMiddle()");
        toRandomValue(sinceTime, tillTime);

//        list.remove(list.size() / 2);
    }

    private void removingInEnd() {
//        Log.i("Collections", " - removingInEnd()");
        toRandomValue(sinceTime, tillTime);

//        list.remove(list.size() - 1);
    }


}
