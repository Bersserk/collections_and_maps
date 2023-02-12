package com.example.collections_and_maps.view_model.models;

import static com.example.collections_and_maps.models.benchmarks.ResultItem.EMPTY;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.DataInflater;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.ArrayList;
import java.util.List;

public class ListCreator {
    public final List<ResultItem> itemsList;

    public ListCreator(DataInflater dataInflater, boolean itemAnimated) {
        itemsList = new ArrayList<>();

        for (int itemOfListHead : dataInflater.getListHeadsId()) {
            itemsList.add(new ResultItem(itemOfListHead, R.string.empty, EMPTY, false));
        }

        for (int methodsID : dataInflater.getListMethodsId()) {
            itemsList.add(new ResultItem(R.string.empty, methodsID, EMPTY, false));
            for (int headsID : dataInflater.getListHeadsId()) {
                itemsList.add(new ResultItem(headsID, methodsID, EMPTY, itemAnimated));
            }
        }
    }
}
