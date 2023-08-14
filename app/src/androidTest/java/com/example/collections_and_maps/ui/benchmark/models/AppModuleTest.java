package com.example.collections_and_maps.ui.benchmark.models;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.collections_and_maps.ui.benchmark.models.benchmarks.BenchmarkTest;
import com.example.collections_and_maps.ui.benchmark.models.benchmarks.CollectionsBenchmarkTest;
import com.example.collections_and_maps.ui.benchmark.models.benchmarks.MapsBenchmarkTest;

import org.junit.runner.RunWith;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
@RunWith(AndroidJUnit4.class)
public class AppModuleTest {

    @Provides
    @Named("collection")
    public BenchmarkTest provideCollections() {
        return new CollectionsBenchmarkTest();
    }

    @Provides
    @Named("maps")
    public BenchmarkTest provideMaps() {
        return new MapsBenchmarkTest();
    }
}
