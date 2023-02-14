package com.example.collections_and_maps.models.benchmarks;

import static com.example.collections_and_maps.models.benchmarks.ResultItem.EMPTY;

import com.example.collections_and_maps.R;

import java.util.ArrayList;
import java.util.List;

public class ListCreator {
    public final List<ResultItem> itemsList;

    public ListCreator(Benchmark fragmentData, boolean itemAnimated) {
        itemsList = new ArrayList<>();

        for (int itemOfListHead : fragmentData.getListHeadsId()) {
            itemsList.add(new ResultItem(itemOfListHead, R.string.empty, EMPTY, false));
        }

        for (int methodsID : fragmentData.getListMethodsId()) {
            itemsList.add(new ResultItem(R.string.empty, methodsID, EMPTY, false));
            for (int headsID : fragmentData.getListHeadsId()) {
                itemsList.add(new ResultItem(headsID, methodsID, EMPTY, itemAnimated));
            }
        }
    }
}
