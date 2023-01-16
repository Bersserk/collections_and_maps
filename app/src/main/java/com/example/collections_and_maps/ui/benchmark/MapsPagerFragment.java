package com.example.collections_and_maps.ui.benchmark;


import androidx.annotation.NonNull;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.MapsComputeTime;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.ArrayList;
import java.util.List;

public class MapsPagerFragment extends BaseFragment {

    @Override
    protected int getSpanCount() {
        return 2;
    }

    @Override
    public List<ResultItem> createTemplateList(boolean itemAnimated) {
        final List<ResultItem> items = new ArrayList<>();

        items.add(new ResultItem(R.string.HashMap, R.string.empty, R.string.empty, false));
        items.add(new ResultItem(R.string.TreeMap, R.string.empty, R.string.empty, false));

        final int[] listHeadsId = {R.string.HashMap, R.string.TreeMap};
        final int[] listMethodsId = {R.string.add_new, R.string.search_key, R.string.removing};

        for (int methodsID : listMethodsId) {
            items.add(new ResultItem(R.string.empty, methodsID, R.string.empty, false));
            for (int headsID : listHeadsId) {
                items.add(new ResultItem(headsID, methodsID, R.string.empty, itemAnimated));
            }
        }
        return items;
    }

    @Override
    protected ResultItem createNewResultItem(@NonNull ResultItem rItem, int value) {
        if (rItem.headerText == R.string.empty || rItem.methodName == R.string.empty) {
            return rItem;
        } else {
            return new ResultItem(rItem.headerText, rItem.methodName,
                    new MapsComputeTime().measureTime(rItem, value), false);
        }
    }
}
