package com.example.collections_and_maps.ui.benchmark.models.benchmarks;

import com.example.collections_and_maps.models.benchmarks.MapsBenchmark;
import com.example.collections_and_maps.models.benchmarks.ResultItem;
import com.example.collections_and_maps.ui.benchmark.Util;

public class MapsBenchmarkTest extends MapsBenchmark {

    @Override
    public double getMeasureTime(ResultItem rItem, int value) {
        Util.delay(500);
        return Util.TIME;
    }
}
