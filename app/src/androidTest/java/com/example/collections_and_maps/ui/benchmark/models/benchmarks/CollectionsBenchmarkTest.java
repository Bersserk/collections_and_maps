package com.example.collections_and_maps.ui.benchmark.models.benchmarks;

import static com.example.collections_and_maps.models.benchmarks.ResultItem.EMPTY;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.ResultItem;
import com.example.collections_and_maps.ui.benchmark.Rule;

import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(AndroidJUnit4.class)
public class CollectionsBenchmarkTest extends Rule implements BenchmarkTest {
    private final Random random = new Random();
    private final int[] listNamesForHead = new int[]{R.string.ArrayList, R.string.LinkedList, R.string.CopyOnWrite};
    private final int[] listNamesForMethod = new int[]{R.string.add_begin, R.string.add_middle,
            R.string.add_end, R.string.search_value, R.string.remove_begin,
            R.string.remove_middle, R.string.remove_end};

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
        return 1000.0;
    }


    @Override
    public int getSpan() {
        return listNamesForHead.length;
    }
}
