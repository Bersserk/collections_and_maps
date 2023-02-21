package com.example.collections_and_maps.models.benchmarks;

import java.util.List;

public interface Benchmark {
    double getMeasureTime(ResultItem rItem, int value);
    List<ResultItem> getItemsList(boolean showProgress);
    int getSpan();
}
