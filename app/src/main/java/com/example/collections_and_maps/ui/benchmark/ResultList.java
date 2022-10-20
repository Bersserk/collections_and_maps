package com.example.collections_and_maps.ui.benchmark;

import java.util.ArrayList;
import java.util.Arrays;

public class ResultList {

    private ArrayList templateList;
    private int sizeArray;

    public ResultList(String[] listNamesMainItem, String[] listNamesItem) {
        createTemplateList(listNamesMainItem, listNamesItem);
    }

    void createTemplateList(String[] listNamesMainItem, String[] listNamesItem) {
        templateList = new ArrayList();
        templateList.addAll(Arrays.asList(listNamesMainItem));

        for (int y = 0; y < listNamesItem.length; y++) {
            templateList.add(listNamesItem[y]);
            for (int i = 0; i < listNamesMainItem.length; i++) {
                templateList.add("...");
            }
        }
    }

    public void setSizeArray(int sizeArray) {
        this.sizeArray = sizeArray;
    }

    public ArrayList getTemplateList() {
        return templateList;
    }

    public int getSizeList() {
        return sizeArray;
    }

}
