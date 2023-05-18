package com.example.collections_and_maps.models.benchmarks.models.benchmarks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.MapsBenchmark;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MapsBenchmarkTest {
    private MapsBenchmark mapsBenchmark;

    private final int[] listNamesForHead = new int[]{R.string.HashMap, R.string.TreeMap};
    private final int[] listNamesForMethod = new int[]{R.string.add_new, R.string.search_key, R.string.removing};

    @Before
    public void setup() {
        mapsBenchmark = new MapsBenchmark();
    }

    @Test
    public void getItemsList_ReturnsListWithExpectedSize() {
        List<ResultItem> itemsList = mapsBenchmark.getItemsList(false);
        assertNotNull(itemsList);
        assertEquals(11, itemsList.size());
    }

    @Test
    public void getMeasureTime_ForEachListAndMethod_ReturnsElapsedTime() {
        for (int nameList: listNamesForHead) {
            for (int nameMethod: listNamesForMethod) {
                ResultItem resultItem = new ResultItem(nameList, nameMethod, ResultItem.EMPTY, false);
                double elapsedTime = mapsBenchmark.getMeasureTime(resultItem, 1000);
                assertTrue(elapsedTime >= 0);
            }
        }
    }

    @Test
    public void getSpan_ReturnsExpectedSpan() {
        int span = mapsBenchmark.getSpan();
        assertEquals(2, span);
    }
}


