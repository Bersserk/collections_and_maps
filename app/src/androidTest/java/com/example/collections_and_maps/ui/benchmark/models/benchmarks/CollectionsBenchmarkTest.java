package com.example.collections_and_maps.ui.benchmark.models.benchmarks;

import com.example.collections_and_maps.models.benchmarks.CollectionsBenchmark;
import com.example.collections_and_maps.models.benchmarks.ResultItem;
import com.example.collections_and_maps.ui.benchmark.Constant;
import com.example.collections_and_maps.ui.benchmark.Util;

public class CollectionsBenchmarkTest extends CollectionsBenchmark {

    @Override
    public double getMeasureTime(ResultItem rItem, int value) {
        Util.delay(500);
        return Constant.TIME;
    }
}
