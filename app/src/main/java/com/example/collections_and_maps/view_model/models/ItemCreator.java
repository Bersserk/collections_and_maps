package com.example.collections_and_maps.view_model.models;

import androidx.annotation.NonNull;

import com.example.collections_and_maps.models.benchmarks.DataFilter;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

public class ItemCreator {

    public ResultItem create (@NonNull ResultItem rItem, int value, DataFilter dataFilter) {
        return rItem.isHeader() ? rItem : new ResultItem(rItem.headerText, rItem.methodName,
                dataFilter.getComputeTime().getMeasureTime(rItem, value), false);
    }
}
