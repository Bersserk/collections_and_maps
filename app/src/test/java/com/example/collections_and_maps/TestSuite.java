package com.example.collections_and_maps;

import com.example.collections_and_maps.models.benchmarks.models.benchmarks.CollectionsBenchmarkTest;
import com.example.collections_and_maps.models.benchmarks.models.benchmarks.MapsBenchmarkTest;
import com.example.collections_and_maps.models.benchmarks.models.benchmarks.ResultItemTest;
import com.example.collections_and_maps.ui.benchmark.BenchmarkViewModelTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BenchmarkViewModelTest.class,
        CollectionsBenchmarkTest.class,
        ResultItemTest.class,
        MapsBenchmarkTest.class
})
public class TestSuite {
}
