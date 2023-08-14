package com.example.collections_and_maps.ui.benchmark.models.benchmarks;

import static com.example.collections_and_maps.models.benchmarks.ResultItem.EMPTY;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class MapsBenchmarkTest implements BenchmarkTest {

    private final int[] listNamesForHead = new int[]{R.string.HashMap, R.string.TreeMap};
    private final int[] listNamesForMethod = new int[]{R.string.add_new, R.string.search_key, R.string.removing};

    @Override
    public List<ResultItem> getItemsList(boolean showProgress) {
        final List<ResultItem> itemsList = new ArrayList<>();

        for (int itemOfListHead : listNamesForHead) {
            itemsList.add(new ResultItem(itemOfListHead, R.string.empty, EMPTY, false));
        }

        for (int methodsID : listNamesForMethod) {
            itemsList.add(new ResultItem(R.string.empty, methodsID, EMPTY, false));
            for (int headsID : listNamesForHead) {
                itemsList.add(new ResultItem(headsID, methodsID, EMPTY, showProgress));
            }
        }
        return itemsList;
    }

    @Override
    public double getMeasureTime(ResultItem rItem, int value) {
        if (value < 0) {
            throw new IllegalStateException("Unexpected value: " + value);
        }
        return 1000.0;
    }


    @Override
    public int getSpan() {
        return listNamesForHead.length;
    }
}

