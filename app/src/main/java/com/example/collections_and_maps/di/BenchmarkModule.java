package com.example.collections_and_maps.di;

import com.example.collections_and_maps.models.benchmarks.CollectionsBenchmark;
import com.example.collections_and_maps.models.benchmarks.MapsBenchmark;

import dagger.Module;
import dagger.Provides;

@Module
public class BenchmarkModule {

    @Provides
    public static CollectionsBenchmark collections() {
        return new CollectionsBenchmark();
    }

    @Provides
    public static MapsBenchmark maps() {
        return new MapsBenchmark();
    }

}
