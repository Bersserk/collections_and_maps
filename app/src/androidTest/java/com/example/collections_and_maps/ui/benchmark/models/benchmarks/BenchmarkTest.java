package com.example.collections_and_maps.ui.benchmark.models.benchmarks;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.collections_and_maps.models.benchmarks.ResultItem;

import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public interface BenchmarkTest {
    double getMeasureTime(ResultItem rItem, int value);

    List<ResultItem> getItemsList(boolean showProgress);

    int getSpan();
}
