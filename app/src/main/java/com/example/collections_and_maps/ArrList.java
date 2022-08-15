package com.example.collections_and_maps;

import java.util.ArrayList;
import java.util.Collections;

class ArrList{

    private ArrayList <Long> arrayList;

    public ArrList(long initialCapacity) {
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        //StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2],(" -- initialCapacity - " + initialCapacity));
        this.arrayList = new ArrayList(Collections.singleton(initialCapacity));
    }

    public String addItemToStart (){
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        ArrayList arrList = arrayList;
        long start = System.nanoTime();
        arrList.add(0, 0);
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        long finish = System.nanoTime();
        return String.valueOf((finish - start)/1000000);
    }

    public String addItemToMiddle (){
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        ArrayList arrList = arrayList;
        long start = System.nanoTime();
        arrList.add(arrayList.size()/2, null);
        long finish = System.nanoTime();
        return String.valueOf((finish - start)/1000000);
    }

    public String addItemToEnd (){
        StepByStep.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        ArrayList arrList = arrayList;
        long start = System.nanoTime();
        arrList.add(arrayList.size()-1, null);
        long finish = System.nanoTime();
        return String.valueOf((finish - start)/1000000);
    }



}
