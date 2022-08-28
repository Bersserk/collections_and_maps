package com.example.collections_and_maps.calculations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class MyLinkedList{

    private LinkedList<Integer> linkedList;
    private String result;

    public String getResult() {
        return result + " ms";
    }

    public MyLinkedList(LinkedList list, String setConstant) {
       linkedList = list;

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
        linkedList.add(0, null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void addItemToMiddle() {
        double start = System.nanoTime();
        linkedList.add(linkedList.size() / 2, null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void addItemToEnd() {
        double start = System.nanoTime();
        linkedList.add(linkedList.size(), null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void searchByValue() {
        int index = 0;
        for (int i = 0; i < 10; i++) {
            if (linkedList.size() < 0) {
                throw new IllegalArgumentException("Array's size must not be negative");
            }
            index = new Random().nextInt(linkedList.size() + 1);
        }

        double start = System.nanoTime();
        linkedList.get(index);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void removingInBeginning() {
        double start = System.nanoTime();
        linkedList.remove(0);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void removingInMiddle() {
        double start = System.nanoTime();
        linkedList.remove(linkedList.size() / 2);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }

    private void removingInEnd() {
        double start = System.nanoTime();
        linkedList.remove(linkedList.size() - 1);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
    }
}
