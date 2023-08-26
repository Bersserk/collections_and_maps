package com.example.collections_and_maps.ui.benchmark.models.benchmarks;

import com.example.collections_and_maps.models.benchmarks.CollectionsBenchmark;
import com.example.collections_and_maps.models.benchmarks.ResultItem;
import com.example.collections_and_maps.ui.benchmark.ui.benchmark.BenchmarksTest;

public class CollectionsBenchmarkTest extends CollectionsBenchmark {

    @Override
    public double getMeasureTime(ResultItem rItem, int value) {
        BenchmarksTest.delay(500);
        return Double.parseDouble(BenchmarksTest.INPUTTED_VALUE);
    }
}
