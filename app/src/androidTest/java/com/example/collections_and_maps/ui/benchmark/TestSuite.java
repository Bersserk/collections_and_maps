package com.example.collections_and_maps.ui.benchmark;

import com.example.collections_and_maps.ui.benchmark.ui.MainActivityTest;
import com.example.collections_and_maps.ui.benchmark.ui.benchmark.CollectionFragmentTest;
import com.example.collections_and_maps.ui.benchmark.ui.benchmark.MapFragmentTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MainActivityTest.class,
        CollectionFragmentTest.class,
        MapFragmentTest.class
})
public class TestSuite {
}
