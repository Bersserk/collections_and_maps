package com.example.collections_and_maps.models.benchmarks;

import com.example.collections_and_maps.R;

import java.util.List;

public class ComputeTime {
    private final List<Byte> list;
    private final double resultValue;


    public ComputeTime(List<Byte> list, ResultItem rItem) {
        this.list = list;
        resultValue = getChose(rItem);
    }

    private double getChose(ResultItem item) {
        switch (item.methodName) {
            case R.string.add_begin:
                return addItemToStart();
            case R.string.add_middle:
                return addItemToMiddle();
            case R.string.add_end:
                return addItemToEnd();
            case R.string.search_value:
                return searchByValue();
            case R.string.remove_begin:
                return removingInBeginning();
            case R.string.remove_middle:
                return removingInMiddle();
            case R.string.remove_end:
                return removingInEnd();
            default:
                throw new IllegalArgumentException();
        }
    }


    private double addItemToStart() {
        double start = System.nanoTime();
        list.add(null);
        return (System.nanoTime() - start) / 1000000;
    }

    private double addItemToMiddle() {
        double start = System.nanoTime();
        list.add(list.size() / 2, null);
        return (System.nanoTime() - start) / 1000000;
    }

    private double addItemToEnd() {
        double start = System.nanoTime();
        list.add(list.size(), null);
        return (System.nanoTime() - start) / 1000000;
    }

    private double searchByValue() {
        double start = System.nanoTime();
        Object valueByIndex = list.get((int) (list.size() / 3 * 2));
        return (System.nanoTime() - start) / 1000000;
    }

    private double removingInBeginning() {
        double start = System.nanoTime();
        list.remove(0);
        return (System.nanoTime() - start) / 1000000;
    }

    private double removingInMiddle() {
        double start = System.nanoTime();
        list.remove(list.size() / 2);
        return (System.nanoTime() - start) / 1000000;
    }

    private double removingInEnd() {
        double start = System.nanoTime();
        list.remove(list.size() - 1);
        return (System.nanoTime() - start) / 1000000;
    }


    public double getResult() {
        return resultValue;
    }
}
