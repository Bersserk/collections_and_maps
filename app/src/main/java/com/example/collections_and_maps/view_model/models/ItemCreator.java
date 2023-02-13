package com.example.collections_and_maps.view_model.models;

import androidx.annotation.NonNull;

import com.example.collections_and_maps.models.benchmarks.ComputeTime;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

public class ItemCreator {

    public ResultItem create(@NonNull ResultItem rItem, int value, ComputeTime fragmentData) {
        return rItem.isHeader() ? rItem : new ResultItem(rItem.headerText, rItem.methodName,
                fragmentData.getMeasureTime(rItem, value), false);
    }
}
