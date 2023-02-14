package com.example.collections_and_maps.models.benchmarks;

public interface Benchmark {
    double getMeasureTime(ResultItem rItem, int value);

    int[] getListHeadsId();

    int[] getListMethodsId();
}
