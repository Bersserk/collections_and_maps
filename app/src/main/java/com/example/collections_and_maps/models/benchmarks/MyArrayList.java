package com.example.collections_and_maps.models.benchmarks;

import java.util.ArrayList;
import java.util.Random;

public class MyArrayList extends ArrayList {

    private ArrayList list;
    private String result;

    public MyArrayList(int sizeArray) {
        list = new ArrayList();
        for (int i = 0; i < sizeArray; i++) {
            list.add(i);
        }
    }

    public String myArrayList(int i) {

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
        return result + " ms";
    }

    private String addItemToStart() {
        double start = System.nanoTime();

        // test part for sleep
        try {
            Thread.sleep(6000);
            list.add(0, null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //        double finish = System.nanoTime();
        result = String.valueOf((System.nanoTime() - start) / 1000000);
        return result;
    }

    private String addItemToMiddle() {
        double start = System.nanoTime();
        list.add(list.size() / 2, null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String addItemToEnd() {
        double start = System.nanoTime();
        list.add(list.size(), null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String searchByValue() {
        int index = 0;
        for (int i = 0; i < 10; i++) {
            if (list.size() < 0) {
                throw new IllegalArgumentException("Array's size must not be negative");
            }
            while (index == 0 || index == list.size()) {
                index = new Random().nextInt(list.size() + 1);
            }
        }

        double start = System.nanoTime();
        list.get(index);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String removingInBeginning() {
        double start = System.nanoTime();

        // test part for sleep
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list.remove(0);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String removingInMiddle() {
        double start = System.nanoTime();
        list.remove(list.size() / 2);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String removingInEnd() {
        double start = System.nanoTime();
        list.remove(list.size() - 1);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

}

