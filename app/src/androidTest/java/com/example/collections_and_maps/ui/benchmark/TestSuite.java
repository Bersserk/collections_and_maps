package com.example.collections_and_maps.ui.benchmark;

import com.example.collections_and_maps.ui.benchmark.ui.BenchmarkFragmentTestTest;
import com.example.collections_and_maps.ui.benchmark.ui.MainActivityTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MainActivityTest.class,
        BenchmarkFragmentTestTest.class
})
public class TestSuite {
}
