package com.example.collections_and_maps.ui.benchmark.models.benchmarks;

import com.example.collections_and_maps.models.benchmarks.MapsBenchmark;
import com.example.collections_and_maps.models.benchmarks.ResultItem;
import com.example.collections_and_maps.ui.benchmark.ui.benchmark.BenchmarksTest;

public class MapsBenchmarkTest extends MapsBenchmark {

    @Override
    public double getMeasureTime(ResultItem rItem, int value) {
        if (value < 0) {
            throw new IllegalStateException("Unexpected value: " + value);
        }
        BenchmarksTest.delay(500);
        return Double.parseDouble(BenchmarksTest.INPUTTED_VALUE);
    }
}
