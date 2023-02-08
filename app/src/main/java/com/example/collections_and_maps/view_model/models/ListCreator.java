package com.example.collections_and_maps.view_model.models;

import static com.example.collections_and_maps.models.benchmarks.ResultItem.EMPTY;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.ResultItem;
import com.example.collections_and_maps.models.repository.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class ListCreator {
    public final List<ResultItem> itemsList;

    public ListCreator(ItemModel model, boolean itemAnimated) {
        itemsList = new ArrayList<>();

        for (int itemOfListHead : model.listHeadsId) {
            itemsList.add(new ResultItem(itemOfListHead, R.string.empty, EMPTY, false));
        }

        for (int methodsID : model.listMethodsId) {
            itemsList.add(new ResultItem(R.string.empty, methodsID, EMPTY, false));
            for (int headsID : model.listHeadsId) {
                itemsList.add(new ResultItem(headsID, methodsID, EMPTY, itemAnimated));
            }
        }
    }
}
