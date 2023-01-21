package com.example.collections_and_maps.ui.benchmark;

import androidx.annotation.NonNull;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.CollectionsComputeTime;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.ArrayList;
import java.util.List;

public class CollectionsPagerFragment extends BaseFragment {

    @Override
    protected int getSpanCount() {
        return 3;
    }

    @Override
    protected List<ResultItem> createTemplateList(boolean itemAnimated) {

        final List<ResultItem> items = new ArrayList<>();

        items.add(new ResultItem(R.string.ArrayList, R.string.empty, R.integer.no_result, false));
        items.add(new ResultItem(R.string.LinkedList, R.string.empty, R.integer.no_result, false));
        items.add(new ResultItem(R.string.CopyOnWrite, R.string.empty, R.integer.no_result, false));

        final int[] listHeadsId = {R.string.ArrayList, R.string.LinkedList, R.string.CopyOnWrite};
        final int[] listMethodsId = {R.string.add_begin, R.string.add_middle,
                R.string.add_end, R.string.search_value, R.string.remove_begin,
                R.string.remove_middle, R.string.remove_end};

        for (int methodsID : listMethodsId) {
            items.add(new ResultItem(R.string.empty, methodsID, R.integer.no_result, false));
            for (int headsID : listHeadsId) {
                items.add(new ResultItem(headsID, methodsID, R.integer.no_result, itemAnimated));
            }
        }
        return items;
    }

    @Override
    protected ResultItem createNewResultItem(@NonNull ResultItem rItem, int value) {
        if (rItem.isHeader()) {
            return rItem;
        } else {
            return new ResultItem(rItem.headerText, rItem.methodName,
                    new CollectionsComputeTime().getMeasureTime(rItem, value), false);
        }
    }
}

