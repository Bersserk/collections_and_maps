package com.example.collections_and_maps.domain.interfases;

import com.example.collections_and_maps.domain.benchmarks.ResultItem;

public interface MeasuredTime {
    double getResult(ResultItem rItem, int value);
}
