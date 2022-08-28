package com.example.collections_and_maps.calculations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class MyBaseData {
    public static final String ADD_ITEM_TO_START = "addItemToStart";
    public static final String ADD_ITEM_TO_MIDDLE = "addItemToMiddle";
    public static final String ADD_ITEM_TO_END = "addItemToEnd";
    public static final String SEARCH_BY_VALUE = "searchByValue";
    public static final String REMOVING_IN_BEGINNING = "removingInBeginning";
    public static final String REMOVING_IN_MIDDLE = "removingInMiddle";
    public static final String REMOVING_IN_END = "removingInEnd";

    private List<Integer> list;

    public List<Integer> getList() {
        return list;
    }



    public MyBaseData(long arraySize) {
        this.list = new ArrayList();
        for (int i = 0; i <= arraySize; i++) {
            list.add(0);
        }
    }
}
