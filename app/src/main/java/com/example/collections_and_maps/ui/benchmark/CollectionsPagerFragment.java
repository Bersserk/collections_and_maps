package com.example.collections_and_maps.ui.benchmark;

import androidx.annotation.NonNull;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.ComputeTime;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionsPagerFragment extends BaseFragment {

    @Override
    protected int getSpanCount() {
        return 3;
    }

    @Override
    protected List<ResultItem> createTemplateList(boolean setAnimateItem) {

        final List<ResultItem> items = new ArrayList<>();

        items.add(new ResultItem(R.string.ArrayList, R.integer.empty, R.integer.empty, false));
        items.add(new ResultItem(R.string.LinkedList, R.integer.empty, R.integer.empty, false));
        items.add(new ResultItem(R.string.CopyOnWrite, R.integer.empty, R.integer.empty, false));

        final int[] listHeadsId = {R.string.ArrayList, R.string.LinkedList, R.string.CopyOnWrite};
        final int[] listMethodsId = {R.string.add_begin, R.string.add_middle,
                R.string.add_end, R.string.search_value, R.string.remove_begin,
                R.string.remove_middle, R.string.remove_end};

        for (int methodsID : listMethodsId) {
            items.add(new ResultItem(R.integer.empty, methodsID, R.integer.empty, false));
            for (int headsID : listHeadsId) {
                items.add(new ResultItem(headsID, methodsID, R.integer.emptyResult, setAnimateItem));
            }
        }
        return items;
    }


    @Override
    protected ResultItem toMakeResultItem(@NonNull ResultItem rItem, int value) {


        if (rItem.result == R.integer.empty) {
            return rItem;
        } else {
            final List<Byte> list = getArray(rItem.headerText, value);
            return new ResultItem(rItem.headerText, rItem.methodName, new ComputeTime(list, rItem).getResult(), true);
        }
    }

    private List<Byte> getArray(int headerText, int value) {

        final List<Byte> arrayList = new ArrayList<>(value);
        for (int i = 0; i < value; i++) {
            arrayList.add(null);
        }

        if (headerText == R.string.LinkedList) {
            return new LinkedList<>(arrayList);
        } else if (headerText == R.string.CopyOnWrite) {
            return new CopyOnWriteArrayList<>(arrayList);
        } else {
            return arrayList;
        }
    }
}

