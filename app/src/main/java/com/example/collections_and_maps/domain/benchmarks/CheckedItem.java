package com.example.collections_and_maps.domain.benchmarks;

import androidx.annotation.NonNull;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.domain.interfases.MeasuredTime;
import com.example.collections_and_maps.view_model.models.FragmentData;

public class CheckedItem {

    public final ResultItem newResultItem;

    public CheckedItem(@NonNull ResultItem rItem, int value, FragmentData data) {
        newResultItem = rItem.isHeader() ? rItem : getNewResultItem(rItem, value, data);
    }

    private ResultItem getNewResultItem(@NonNull ResultItem rItem, int value, FragmentData data) {

        final MeasuredTime time = data.namePage == R.string.Collections
                ? new CollectionsMeasuredTime()
                : new MapsMeasuredTime();

        return new ResultItem(rItem.headerText, rItem.methodName
                , time.getResult(rItem, value)  // getting result time
                , false);

    }
}
