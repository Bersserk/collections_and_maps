package com.example.collections_and_maps.view_model.models;

import androidx.annotation.NonNull;

import com.example.collections_and_maps.models.benchmarks.CalculatorTimes;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

public class ItemInspector {

    public ResultItem getInspected(@NonNull ResultItem rItem, int value) {
        return rItem.isHeader() ? rItem : new ResultItem(rItem.headerText, rItem.methodName,
                new CalculatorTimes<>().getCalculatedTime(rItem, value), false);
    }
}
