package com.example.collections_and_maps.models.benchmarks;

import java.util.LinkedList;
import java.util.Random;

public class MyLinkedList extends LinkedList {

    private LinkedList list;
    private String result;

//    public MyLinkedList(int sizeArray) {
//        list = new LinkedList();
//        for (int i = 0; i < sizeArray; i++) {
//            list.add(i);
//        }
//    }

    public MyLinkedList(String methodName) {
        //запускаем метод согласно входящему имени
        myLinkedList(methodName);
    }

    public void myLinkedList(String methodName) {

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
//        return result + " ms";
    }

    private String addItemToStart() {
        double start = System.nanoTime();
//        list.add(0, null);
        try {
            Thread.sleep(1000 + Math.round(Math.random()*3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String addItemToMiddle() {
        double start = System.nanoTime();
//        list.add(list.size() / 2, null);
        try {
            Thread.sleep(1000 + Math.round(Math.random()*3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String addItemToEnd() {
        double start = System.nanoTime();

        // test part for sleep
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        list.add(list.size(), null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String searchByValue() {
//        int index = 0;
//        for (int i = 0; i < 10; i++) {
//            if (list.size() < 0) {
//                throw new IllegalArgumentException("Array's size must not be negative");
//            }
//            while (index == 0 || index == list.size()) {
//                index = new Random().nextInt(list.size() + 1);
//            }
//        }

        double start = System.nanoTime();
//        list.get(index);
        try {
            Thread.sleep(1000 + Math.round(Math.random()*3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String removingInBeginning() {
        double start = System.nanoTime();
//        list.remove(0);
        try {
            Thread.sleep(1000 + Math.round(Math.random()*3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String removingInMiddle() {
        double start = System.nanoTime();
//        list.remove(list.size() / 2);
        try {
            Thread.sleep(1000 + Math.round(Math.random()*3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String removingInEnd() {
        double start = System.nanoTime();
//        list.remove(list.size() - 1);
        try {
            Thread.sleep(1000 + Math.round(Math.random()*3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    public String getResult() {
        return result;
    }
}
