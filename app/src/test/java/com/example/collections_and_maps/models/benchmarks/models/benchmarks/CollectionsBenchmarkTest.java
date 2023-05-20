package com.example.collections_and_maps.models.benchmarks.models.benchmarks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.CollectionsBenchmark;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CollectionsBenchmarkTest {
    private CollectionsBenchmark collectionsBenchmark;

    private final int[] listNamesForHead = new int[]{R.string.ArrayList, R.string.LinkedList, R.string.CopyOnWrite};
    private final int[] listNamesForMethod = new int[]{R.string.add_begin, R.string.add_middle,
            R.string.add_end, R.string.search_value, R.string.remove_begin,
            R.string.remove_middle, R.string.remove_end};

    @Before
    public void setup() {
        collectionsBenchmark = new CollectionsBenchmark();
    }

    @Test
    public void getItemsList_ReturnsListWithExpectedSize() {
        List<ResultItem> itemsList = collectionsBenchmark.getItemsList(false);
        assertNotNull(itemsList);
        assertEquals(31, itemsList.size());
    }

    @Test
    public void getResultItem_ForEachFiledOfClass (){
        ResultItem resultItem = new ResultItem(R.string.ArrayList, R.string.add_begin, 1000.0, true);

        assertEquals(resultItem.headerText, R.string.ArrayList, 0);
        assertEquals(resultItem.methodName, R.string.add_begin, 0);
        assertEquals(resultItem.timing, 1000.0, 0);
        assertTrue(resultItem.progressVisible);
    }

    @Test
    public void getMeasureTime_ForEachListAndMethod_ReturnsElapsedTime() {
        for (int nameList: listNamesForHead) {
            for (int nameMethod: listNamesForMethod) {
                ResultItem resultItem = new ResultItem(nameList, nameMethod, ResultItem.EMPTY, false);
                double elapsedTime = collectionsBenchmark.getMeasureTime(resultItem, 1000);
                assertTrue(elapsedTime >= 0);
            }
        }
    }

    @Test
    public void getSpan_ReturnsExpectedSpan() {
        int span = collectionsBenchmark.getSpan();
        assertEquals(3, span);
    }
}

