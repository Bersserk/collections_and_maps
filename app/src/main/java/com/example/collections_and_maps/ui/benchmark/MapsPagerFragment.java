package com.example.collections_and_maps.ui.benchmark;


import androidx.annotation.NonNull;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.ComputeTime;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.ArrayList;
import java.util.List;

public class MapsPagerFragment extends BaseFragment {

    @Override
    protected int getSpanCount() {
        return 2;
    }

    @Override
    public List<ResultItem> createTemplateList(int animateStatus) {
        final List<ResultItem> items = new ArrayList<>();

        items.add(new ResultItem(R.string.HashMap, R.integer.empty, R.integer.empty, R.string.head));
        items.add(new ResultItem(R.string.TreeMap, R.integer.empty, R.integer.empty, R.string.head));

        final int[] listHeadsId = {R.string.HashMap, R.string.TreeMap};
        final int[] listMethodsId = {R.string.add_new, R.string.search_key, R.string.removing};

        for (int methodsID : listMethodsId) {
            items.add(new ResultItem(R.integer.empty, methodsID, R.integer.empty, R.string.method));
            for (int headsID : listHeadsId) {
                items.add(new ResultItem(headsID, methodsID, R.integer.emptyResult, animateStatus));
            }
        }
        return items;
    }

    @Override
    protected ResultItem toMakeResultItem(@NonNull ResultItem rItem, int value) {
        if (rItem.result == R.integer.empty) {
            return rItem;
        } else {
            return new ResultItem(rItem.headerText, rItem.methodName,
                    new ComputeTime().getResult(rItem, value), R.string.result);
        }
    }
}
