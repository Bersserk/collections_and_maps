package com.example.collections_and_maps.models.benchmarks;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyCopyOnWriteArrayList {

    private CopyOnWriteArrayList copyOnWriteArrayList;
    private String result;

    public MyCopyOnWriteArrayList(CopyOnWriteArrayList sizeList, int i) {
//        copyOnWriteArrayList = createCopyOnWriteArrayList(k);
        copyOnWriteArrayList = sizeList;

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
        copyOnWriteArrayList.add(0, null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String addItemToMiddle() {
        double start = System.nanoTime();
        copyOnWriteArrayList.add(copyOnWriteArrayList.size() / 2, null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String addItemToEnd() {
        double start = System.nanoTime();
        copyOnWriteArrayList.add(copyOnWriteArrayList.size(), null);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String searchByValue() {
        int index = 0;
        for (int i = 0; i < 10; i++) {
            if (copyOnWriteArrayList.size() < 0) {
                throw new IllegalArgumentException("Array's size must not be negative");
            }
            while (index == 0 || index == copyOnWriteArrayList.size()) {
                index = new Random().nextInt(copyOnWriteArrayList.size() + 1);
            }
        }

        double start = System.nanoTime();
        copyOnWriteArrayList.get(index);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String removingInBeginning() {
        double start = System.nanoTime();
        copyOnWriteArrayList.remove(0);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String removingInMiddle() {
        double start = System.nanoTime();
        copyOnWriteArrayList.remove(copyOnWriteArrayList.size() / 2);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
    }

    private String removingInEnd() {
        double start = System.nanoTime();
        copyOnWriteArrayList.remove(copyOnWriteArrayList.size() - 1);
        double finish = System.nanoTime();
        result = String.valueOf((finish - start) / 1000000);
        return result;
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
