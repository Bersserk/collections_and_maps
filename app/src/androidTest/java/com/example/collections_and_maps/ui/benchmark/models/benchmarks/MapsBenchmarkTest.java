package com.example.collections_and_maps.ui.benchmark.models.benchmarks;

import com.example.collections_and_maps.models.benchmarks.MapsBenchmark;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

public class MapsBenchmarkTest extends MapsBenchmark {

    @Override
    public double getMeasureTime(ResultItem rItem, int value) {
        if (value < 0) {
            throw new IllegalStateException("Unexpected value: " + value);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1000.0;
    }
}
