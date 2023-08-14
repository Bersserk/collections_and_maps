package com.example.collections_and_maps.ui.benchmark;

import com.example.collections_and_maps.ui.benchmark.models.AppComponentTest;
import com.example.collections_and_maps.ui.benchmark.models.AppModuleTest;
import com.example.collections_and_maps.ui.benchmark.models.benchmarks.BenchmarkTest;
import com.example.collections_and_maps.ui.benchmark.models.benchmarks.CollectionsBenchmarkTest;
import com.example.collections_and_maps.ui.benchmark.models.benchmarks.MapsBenchmarkTest;
import com.example.collections_and_maps.ui.benchmark.ui.BenchmarkFragmentTest;
import com.example.collections_and_maps.ui.benchmark.ui.MainActivityTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MainActivityTest.class,
        BenchmarkFragmentTest.class,
        CollectionsBenchmarkTest.class,
        MapsBenchmarkTest.class,
        BenchmarkTest.class,
        AppComponentTest.class,
        AppModuleTest.class
})
public class TestSuite {
}
