package com.example.collections_and_maps.ui.benchmark.models.benchmarks;

import com.example.collections_and_maps.models.benchmarks.CollectionsBenchmark;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

public class CollectionsBenchmarkTest extends CollectionsBenchmark {

    @Override
    public double getMeasureTime(ResultItem rItem, int value) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1000.0;
    }
}
