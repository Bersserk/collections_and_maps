package com.example.collections_and_maps.ui.benchmark;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.collections_and_maps.ui.MainActivity;
import com.example.collections_and_maps.ui.benchmark.ui.BenchmarkFragmentTest;
import com.example.collections_and_maps.ui.benchmark.ui.MainActivityTest;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MainActivityTest.class,
        BenchmarkFragmentTest.class
})
public class TestSuite {
}
