package com.example.collections_and_maps.domain.models;

import static com.example.collections_and_maps.domain.benchmarks.ResultItem.EMPTY;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.domain.benchmarks.ResultItem;
import com.example.collections_and_maps.view_model.models.FragmentData;

import java.util.ArrayList;
import java.util.List;

public class ListCreator {
    public final List<ResultItem> itemsList;

    public ListCreator(FragmentData data, boolean itemAnimated) {
        itemsList = new ArrayList<>();

        for (int itemOfListHead : data.listHeadsId) {
            itemsList.add(new ResultItem(itemOfListHead, R.string.empty, EMPTY, false));
        }

        for (int methodsID : data.listMethodsId) {
            itemsList.add(new ResultItem(R.string.empty, methodsID, EMPTY, false));
            for (int headsID : data.listHeadsId) {
                itemsList.add(new ResultItem(headsID, methodsID, EMPTY, itemAnimated));
            }
        }
    }
}
