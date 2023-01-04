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
    protected List<ResultItem> createTemplateList(int animateStatus) {

        final List<ResultItem> items = new ArrayList<>();

        items.add(new ResultItem(R.string.ArrayList, R.integer.empty, R.integer.empty, R.string.head));
        items.add(new ResultItem(R.string.LinkedList, R.integer.empty, R.integer.empty, R.string.head));
        items.add(new ResultItem(R.string.CopyOnWrite, R.integer.empty, R.integer.empty, R.string.head));

        final int[] listHeadsId = {R.string.ArrayList, R.string.LinkedList, R.string.CopyOnWrite};
        final int[] listMethodsId = {R.string.add_begin, R.string.add_middle,
                R.string.add_end, R.string.search_value, R.string.remove_begin,
                R.string.remove_middle, R.string.remove_end};

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
        System.out.println("toMakeResultItem()");
        if (rItem.result == R.integer.empty) {
            return rItem;
        } else {
            ResultItem resIt = new ResultItem(rItem.headerText, rItem.methodName, new ComputeTime().getResult(rItem, value), R.string.result);
            System.out.println("head = " + rItem.headerText + "; met = " + rItem.methodName +
                    "; res = " + resIt.result + "; anim = " + resIt.isAnimate);
            return resIt;
//            return new ResultItem(rItem.headerText, rItem.methodName, new ComputeTime().getResult(rItem, value), true);
        }
    }

}

