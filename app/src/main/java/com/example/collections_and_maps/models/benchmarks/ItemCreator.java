package com.example.collections_and_maps.models.benchmarks;

import androidx.annotation.NonNull;

public class ItemCreator {

    public ResultItem create(@NonNull ResultItem rItem, int value, Benchmark fragmentData) {
        return rItem.isHeader() ? rItem : new ResultItem(rItem.headerText, rItem.methodName,
                fragmentData.getMeasureTime(rItem, value), false);
    }
}
