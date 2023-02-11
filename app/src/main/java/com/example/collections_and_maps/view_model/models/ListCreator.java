package com.example.collections_and_maps.view_model.models;

import static com.example.collections_and_maps.models.benchmarks.ResultItem.EMPTY;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.DataFilter;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.ArrayList;
import java.util.List;

public class ListCreator {
    public final List<ResultItem> itemsList;

    public ListCreator(DataFilter dataFilter, boolean itemAnimated) {
        itemsList = new ArrayList<>();

        for (int itemOfListHead : dataFilter.getListHeadsId()) {
            itemsList.add(new ResultItem(itemOfListHead, R.string.empty, EMPTY, false));
        }

        for (int methodsID : dataFilter.getListMethodsId()) {
            itemsList.add(new ResultItem(R.string.empty, methodsID, EMPTY, false));
            for (int headsID : dataFilter.getListHeadsId()) {
                itemsList.add(new ResultItem(headsID, methodsID, EMPTY, itemAnimated));
            }
        }
    }
}
