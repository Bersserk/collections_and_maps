package com.example.collections_and_maps.calculations;

import java.util.ArrayList;

public abstract class MyBaseData {
    public static final String ADD_ITEM_TO_START = "addItemToStart";
    public static final String ADD_ITEM_TO_MIDDLE = "addItemToMiddle";
    public static final String ADD_ITEM_TO_END = "addItemToEnd";
    public static final String SEARCH_BY_VALUE = "searchByValue";
    public static final String REMOVING_IN_BEGINNING = "removingInBeginning";
    public static final String REMOVING_IN_MIDDLE = "removingInMiddle";
    public static final String REMOVING_IN_END = "removingInEnd";

    private ArrayList<Integer> arrayList;

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }

    public MyBaseData(int arraySize, String setConstant) {
        this.arrayList = new ArrayList();
        for (int i = 0; i <= arraySize; i++) {
            arrayList.add(0);
        }
    }
}
