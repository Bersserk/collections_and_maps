package com.example.collections_and_maps;

import java.util.ArrayList;
import java.util.Collections;

class ArrList {

    private ArrayList<Long> arrayList;
    private ArrayList<String> resultFromArrayList;

    public ArrayList<String> getResultFromArrayList() {



        return resultFromArrayList;
    }

    public ArrList() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        this.resultFromArrayList = new ArrayList();
        addItemToStart();
        addItemToMiddle();
        addItemToEnd();
        searchByValue();
        removingInBeginning();
        removingInMiddle();
        removingInEnd();
    }


    public ArrList(long initialCapacity) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        //StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2],(" -- initialCapacity - " + initialCapacity));
        this.arrayList = new ArrayList(Collections.singleton(initialCapacity));
        this.resultFromArrayList = new ArrayList();

        addItemToStart();
        addItemToMiddle();
        addItemToEnd();
        searchByValue();
        removingInBeginning();
        removingInMiddle();
        removingInEnd();
    }

    public void addItemToStart() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        ArrayList arrList = arrayList;
        double start = System.nanoTime();
        arrList.add(0, 0);
        double finish = System.nanoTime();
        resultFromArrayList.add(String.valueOf((finish - start) / 1000000));
    }

    public void addItemToMiddle() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        ArrayList arrList = arrayList;
        long start = System.nanoTime();
        arrList.add(arrayList.size() / 2, null);
        long finish = System.nanoTime();
        resultFromArrayList.add(String.valueOf((finish - start) / 1000000));
    }

    public void addItemToEnd() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        ArrayList arrList = arrayList;
        long start = System.nanoTime();
        arrList.add(arrayList.size() - 1, null);
        long finish = System.nanoTime();
        resultFromArrayList.add(String.valueOf((finish - start) / 1000000));
    }

    public void searchByValue() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        ArrayList arrList = arrayList;
        long start = System.nanoTime();
        // реализация
        long finish = System.nanoTime();
        resultFromArrayList.add(String.valueOf((finish - start) / 1000000));
    }

    public void removingInBeginning() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        ArrayList arrList = arrayList;
        long start = System.nanoTime();
        // реализация
        long finish = System.nanoTime();
        resultFromArrayList.add(String.valueOf((finish - start) / 1000000));
    }

    public void removingInMiddle() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        ArrayList arrList = arrayList;
        long start = System.nanoTime();
        // реализация
        long finish = System.nanoTime();
        resultFromArrayList.add(String.valueOf((finish - start) / 1000000));
    }

    public void removingInEnd() {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        ArrayList arrList = arrayList;
        long start = System.nanoTime();
        // реализация
        long finish = System.nanoTime();
        resultFromArrayList.add(String.valueOf((finish - start) / 1000000));
    }


}
