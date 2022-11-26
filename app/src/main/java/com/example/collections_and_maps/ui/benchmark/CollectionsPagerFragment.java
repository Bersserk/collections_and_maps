package com.example.collections_and_maps.ui.benchmark;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.List;

public class CollectionsPagerFragment extends BaseFragment {

    @Override
    protected int getSpanCount() {
        return 3;
    }

    @Override
    public List<ResultItem> createTemplateList() {
        return super.createTemplateList(R.array.collections, R.array.collections_item);
    }

    public String getResult(int methodName) {
        String result;
        double start = System.nanoTime();
        getChose(methodName);
        result = String.valueOf((System.nanoTime() - start) / 1000000);
        return result;
    }

    void getChose (int methodName){
//        Log.i("Collections", " - getChose (String methodName) - " + methodName);
        switch (methodName) {
            case 1:
                addItemToStart();
                break;
            case 2:
                addItemToMiddle();
                break;
            case 3:
                addItemToEnd();
                break;
            case 4:
                searchByValue();
                break;
            case 5:
                removingInBeginning();
                break;
            case 6:
                removingInMiddle();
                break;
            case 7:
                removingInEnd();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void addItemToStart() {
//        Log.i("Collections", " - addItemToStart()");
        toRandomValue(0, 7000);
//        list.add(0, null);
    }

    private void addItemToMiddle() {
//        Log.i("Collections", " - addItemToMiddle()");
        toRandomValue(0, 7000);
//        list.add(list.size() / 2, null);
    }

    private void addItemToEnd() {
//        Log.i("Collections", " - addItemToEnd()");
        toRandomValue(0, 7000);
//        list.add(list.size(), null);
    }

    private void searchByValue() {
//        Log.i("Collections", " - searchByValue()");
        toRandomValue(0, 7000);

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
        toRandomValue(0, 7000);

//        list.remove(0);
    }

    private void removingInMiddle() {
//        Log.i("Collections", " - removingInMiddle()");
        toRandomValue(0, 7000);

//        list.remove(list.size() / 2);
    }

    private void removingInEnd() {
//        Log.i("Collections", " - removingInEnd()");
        toRandomValue(0, 7000);

//        list.remove(list.size() - 1);
    }

}

